package com.guet.navigator.web.controller.mobile;

import com.guet.navigator.web.constant.Messages;
import com.guet.navigator.web.constant.user.DeviceConstant;
import com.guet.navigator.web.constant.user.MobileConstant;
import com.guet.navigator.web.constant.user.UserConstant;
import com.guet.navigator.web.pojo.Device;
import com.guet.navigator.web.pojo.LoginRecord;
import com.guet.navigator.web.pojo.User;
import com.guet.navigator.web.service.DeviceLoginRecordService;
import com.guet.navigator.web.service.DeviceService;
import com.guet.navigator.web.service.UserService;
import com.guet.navigator.web.vo.DeviceConfirmVo;
import com.guet.navigator.web.vo.ScanQRCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/mobile/device")
public class MobileDeviceController {

    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceLoginRecordService deviceRecordService;

    /**
     * 用户手机扫码后确认登陆登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/confirm-device-login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deviceLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody DeviceConfirmVo deviceConfirmVo) {

        String qrCodeStr = deviceConfirmVo.getQrCodeStr();
        Boolean loginFlag = deviceConfirmVo.getLoginFlag();

        Map<String, Object> msg = new HashMap<String, Object>();

        if (!(StringUtils.isEmpty(qrCodeStr) || StringUtils.isEmpty(loginFlag))) {

            ServletContext servletContext = request.getServletContext();
            HttpSession deviceSession = (HttpSession) servletContext.getAttribute(qrCodeStr);

            //判断二维码是否过期
            if (StringUtils.isEmpty(deviceSession)) {
                //二维码过期
                msg.put(MobileConstant.MESSAGES, Messages.QRCODE_TIME_OUT);
                //状态码404
                msg.put(MobileConstant.STATUS_CODE, 404);
            } else {
                //获取设备的硬件id
                String deviceId = (String) deviceSession.getAttribute(DeviceConstant.DEVICE_ID);
                //获取userId
                User user = (User) request.getSession().getAttribute(UserConstant.USER);
                //currentTime
                Timestamp crruentTime = new Timestamp(System.currentTimeMillis());
                //从数据库中获取device
                Device device = deviceService.getByDeviceId(deviceId);
                //从数据库中获取user
                User u = userService.findByUserId(user.getUserId());
                //创建登录记录
                LoginRecord deviceRecord = new LoginRecord(u, device, crruentTime, crruentTime);
                //存入设备登录状态表
                deviceRecordService.createDeviceRecord(deviceRecord);
                //二维码过期
                msg.put(MobileConstant.MESSAGES, Messages.SCAN_QRCODE_LOGIN_SUCEESS);
                //状态码200
                msg.put(MobileConstant.STATUS_CODE, 200);
            }
            return msg;
        } else {
            //服务器内部错误
            msg.put(MobileConstant.MESSAGES, Messages.SERVER_INNER_ERROR);
            //状态码500
            msg.put(MobileConstant.STATUS_CODE, 500);
            return msg;
        }

    }

    /**
     * 用户手机已经扫码但未确认
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/confirm-sweep-code",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> scanQRCode(HttpServletRequest request, HttpServletResponse response,
                                          @RequestBody ScanQRCodeVo scanQRCodeVo) {

        String qrCodeStr = scanQRCodeVo.getQrCodeStr();
        Boolean scanFlag = scanQRCodeVo.getScanFlag();

        Map<String, Object> msg = new HashMap<String, Object>();

        if (!(StringUtils.isEmpty(qrCodeStr) || StringUtils.isEmpty(scanFlag))) {

            ServletContext servletContext = request.getServletContext();

            //从ServletContext中获取之前存入的Session
            HttpSession httpSession = (HttpSession) servletContext.getAttribute(qrCodeStr);

            //若Session已经未过期
            if (!StringUtils.isEmpty(httpSession)) {
                //更改设备的session扫码标志位为true
                httpSession.setAttribute(DeviceConstant.QRCODE_STATUS, true);
                //返回扫码成功
                msg.put(MobileConstant.STATUS_CODE, 200);
            } else {
                //返回二维码已经过期
                msg.put(MobileConstant.STATUS_CODE, 404);
            }
        } else {
            //服务器内部错误
            msg.put(MobileConstant.STATUS_CODE, 500);
        }
        return msg;
    }

}
