package com.guet.navigator.web.controller.device;

import com.guet.navigator.web.constant.DeviceConstant;
import com.guet.navigator.web.pojo.DeviceRecord;
import com.guet.navigator.web.pojo.User;
import com.guet.navigator.web.service.DeviceRecordService;
import com.guet.navigator.web.service.UserService;
import com.guet.navigator.web.vo.DeviceLoginMessageVo;
import com.guet.navigator.web.vo.QRCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/device/user")
public class DeviceController {

    @Autowired
    private DeviceRecordService deviceRecordService;

    @Autowired
    private UserService userService;

    /**
     * 安卓设备请求获取用于生产二维码的字符串
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login/{deviceId}",method = RequestMethod.GET)
    @ResponseBody
    public QRCodeVo requestQRCode(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "deviceId")String deviceId){

        //生成qrCodeStr唯一标识
        String qrCodeStr = UUID.randomUUID().toString();

        //获取当前会话的session
        HttpSession httpSession = request.getSession();

        //设置session的最大的生命周期为2分钟
        httpSession.setMaxInactiveInterval(60);

        //将当前的qrCodeStr存储到session中
        httpSession.setAttribute(DeviceConstant.QRCODE_STR,qrCodeStr);

        //将当前的hdId存储到session中
        httpSession.setAttribute(DeviceConstant.DEVICE_ID,deviceId);

        //设置二维码状态为用户未扫码
        httpSession.setAttribute(DeviceConstant.QRCODE_STATUS,false);

        //获取ServerContext
        ServletContext servletContext = request.getServletContext();

        //将当前的qrCodeStr和session存入ServletContext中
        servletContext.setAttribute(qrCodeStr,httpSession);

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
    @RequestMapping(value = "/login/{deviceId}",method = RequestMethod.PUT)
    @ResponseBody
    public DeviceLoginMessageVo login(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "deviceId")String deviceId){

        //获取Session,若session失效则不创建
        HttpSession httpSession = request.getSession(false);

        //若session未失效
        if(!StringUtils.isEmpty(httpSession)){

            long recordTime = httpSession.getCreationTime();
            long currentTime = System.currentTimeMillis();

            //session的时间超过60秒
            if((currentTime-recordTime)>=60000*60){

                //设置Session失效
                httpSession.invalidate();

                //若session失效
                return new DeviceLoginMessageVo(3);

            }else{
                //查询数据库
                DeviceRecord deviceRecord = deviceRecordService.findByDeviceId(deviceId);

                //判断数据库中是否存在登录记录
                if(!StringUtils.isEmpty(deviceRecord)){
                    return new DeviceLoginMessageVo(2);
                }

                Boolean qrCodeFlag = (Boolean) httpSession.getAttribute(DeviceConstant.QRCODE_STATUS);

                if(qrCodeFlag){
                    return new DeviceLoginMessageVo(1);
                }else{
                    return new DeviceLoginMessageVo(0);
                }

            }

        }

        //若session失效
        return new DeviceLoginMessageVo(3);
    }

    /**
     *  设备获取用户的详细信息
     *
     * @param request
     * @param response
     * @param deviceId
     * @return
     */
    @RequestMapping("/detail/{deviceId}")
    @ResponseBody
    public User getDetailInfo(HttpServletRequest request,HttpServletResponse response,@PathVariable(value = "deviceId")String deviceId){

        DeviceRecord deviceRecord = deviceRecordService.findByDeviceId(deviceId);

        User u = deviceRecord.getUser();

        User user = userService.findByUserId(u.getUserId());

        return user;

    }

}
