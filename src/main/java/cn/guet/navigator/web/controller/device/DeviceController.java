package cn.guet.navigator.web.controller.device;

import cn.guet.navigator.web.constant.user.DeviceConstant;
import cn.guet.navigator.web.pojo.Device;
import cn.guet.navigator.web.pojo.LoginRecord;
import cn.guet.navigator.web.pojo.Position;
import cn.guet.navigator.web.service.DeviceService;
import cn.guet.navigator.web.service.LoginRecordService;
import cn.guet.navigator.web.service.PositionService;
import cn.guet.navigator.web.vo.DeviceLoginMessageVo;
import cn.guet.navigator.web.vo.QRCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private LoginRecordService deviceRecordService;

    /**
     * 安卓设备请求获取用于生产二维码的字符串
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login/{deviceId}", method = RequestMethod.GET)
    @ResponseBody
    public QRCodeVo requestLogin(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "deviceId") String deviceId) {
        //生成qrCodeStr唯一标识
        String qrCodeStr = UUID.randomUUID().toString();
        //获取当前会话的session
        HttpSession httpSession = request.getSession();
        //设置session的最大的生命周期为70秒，不设置默认为30分钟
        httpSession.setMaxInactiveInterval(70);
        //将当前的qrCodeStr存储到session中
        httpSession.setAttribute(DeviceConstant.QRCODE_STR, qrCodeStr);
        //将当前的hdId存储到session中
        httpSession.setAttribute(DeviceConstant.DEVICE_ID, deviceId);
        //设置二维码状态为用户未扫码
        httpSession.setAttribute(DeviceConstant.QRCODE_STATUS, false);
        //获取ServerContext
        ServletContext servletContext = request.getServletContext();
        //将当前的qrCodeStr和session存入ServletContext中
        servletContext.setAttribute(qrCodeStr, httpSession);
        //封装qrCodeStr以json格式返回
        return new QRCodeVo(qrCodeStr);
    }

    /**
     * 安卓设备轮训用户是否扫码登录成功
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login/{deviceId}", method = RequestMethod.POST)
    @ResponseBody
    public DeviceLoginMessageVo login(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "deviceId") String deviceId) {

        //获取Session,若session失效则不创建
        HttpSession httpSession = request.getSession(false);

        //若session未失效
        if (!StringUtils.isEmpty(httpSession)) {

            long recordTime = httpSession.getCreationTime();
            long currentTime = System.currentTimeMillis();

            //session的时间超过60秒
            if ((currentTime - recordTime) >= 60) {
                //设置Session失效
                httpSession.invalidate();
                //若session失效
                return new DeviceLoginMessageVo(3);
            } else {
                //查询数据库
                LoginRecord deviceRecord = deviceRecordService.getByDeviceId(deviceId);

                //判断数据库中是否存在登录记录
                if (!StringUtils.isEmpty(deviceRecord)) {
                    return new DeviceLoginMessageVo(2);
                }

                Boolean qrCodeFlag = (Boolean) httpSession.getAttribute(DeviceConstant.QRCODE_STATUS);

                if (qrCodeFlag) {
                    return new DeviceLoginMessageVo(1);
                } else {
                    return new DeviceLoginMessageVo(0);
                }
            }
        }
        //若session失效
        return new DeviceLoginMessageVo(3);
    }

    /**
     * 存储小车实时(间隔为1秒)的定位数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/collect-location", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> collectCarLocation(HttpServletRequest request, HttpServletResponse response,
                                                  @RequestParam("deviceId") String deviceId,
                                                  @RequestParam("latitude") Double latitude,
                                                  @RequestParam("longitude") Double longitude,
                                                  @RequestParam("speed") Double speed,
                                                  @RequestParam("currentTime") Long currentTime) throws InterruptedException, IOException {

        Map<String, Object> msg = new HashMap<String, Object>();

        if (StringUtils.isEmpty(deviceId) || StringUtils.isEmpty(latitude) || StringUtils.isEmpty(longitude) || StringUtils.isEmpty(speed) || StringUtils.isEmpty(currentTime)) {
            //数据存在空项
            msg.put("statusCode", 300);
        } else {
            Device device = deviceService.getByDeviceId(deviceId);
            if (!StringUtils.isEmpty(device)) {
                Position position = new Position();
                position.setSpeed(Double.valueOf(speed));
                position.setLatitude(Double.valueOf(latitude));
                position.setLongitude(Double.valueOf(longitude));
                position.setCreateTime(new Timestamp(currentTime));
                position.setDevice(device);
                if (positionService.save(position)) {
                    //存储成功
                    msg.put("statusCode", 200);
                } else {
                    //服务器内部错误
                    msg.put("statusCode", 500);
                }
            } else {
                //非法设备
                msg.put("statusCode", 600);
            }
        }
        //判断是否发送车祸
        return msg;
    }

}
