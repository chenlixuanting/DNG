package com.guet.navigator.web.controller.mobile;

import com.guet.navigator.web.constant.common.CommonConstant;
import com.guet.navigator.web.constant.user.DeviceConstant;
import com.guet.navigator.web.constant.Messages;
import com.guet.navigator.web.constant.user.MobileConstant;
import com.guet.navigator.web.constant.user.UserConstant;
import com.guet.navigator.web.pojo.Device;
import com.guet.navigator.web.pojo.LoginRecord;
import com.guet.navigator.web.pojo.User;
import com.guet.navigator.web.service.DeviceLoginRecordService;
import com.guet.navigator.web.service.DeviceService;
import com.guet.navigator.web.service.UserService;
import com.guet.navigator.web.utils.GetDefaultHeadPicUtil;
import com.guet.navigator.web.vo.DeviceConfirmVo;
import com.guet.navigator.web.vo.ScanQRCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/mobile/user")
public class MobileController {

    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceLoginRecordService deviceRecordService;

    /**
     * 微信小程序通过账号密码登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {

        Map<String, Object> msg = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();

        //判断接收的user是否为null
        if (!StringUtils.isEmpty(user)) {

            //判断账户或密码是否为空
            if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())) {
                //状态码404
                msg.put(MobileConstant.STATUS_CODE, 404);
                //账户或密码为空
                msg.put(MobileConstant.MESSAGES, Messages.USERNAME_OR_PASSWORD_EMPTY);
                return msg;
            } else {
                //根据accoutn从数据库中获取user
                User u = userService.findByUserAccount(user.getAccount());
                //判断账户是否存在
                if (StringUtils.isEmpty(u)) {
                    //账户不存在
                    msg.put(MobileConstant.MESSAGES, Messages.ACCOUNT_NOT_EXIST);
                    //状态码301
                    msg.put(MobileConstant.STATUS_CODE, 301);
                    return msg;
                } else {
                    //判断密码是否正确
                    if (u.getPassword().equals(user.getPassword())) {
                        //密码正确
                        msg.put(MobileConstant.MESSAGES, Messages.LOGIN_SUCCESS);
                        //状态码
                        msg.put(MobileConstant.STATUS_CODE, 200);
                        //存入sessionId
                        msg.put(MobileConstant.SESSION_ID, httpSession.getId());
                        //账户登录
                        httpSession.setAttribute(UserConstant.USER, u);
                        return msg;
                    } else {
                        //密码不正确
                        msg.put(MobileConstant.MESSAGES, Messages.PASSWORD_ERROR);
                        //状态码
                        msg.put(MobileConstant.STATUS_CODE, 405);
                        return msg;
                    }

                }

            }
        } else {
            //服务器内部错误
            msg.put(MobileConstant.MESSAGES, Messages.SERVER_INNER_ERROR);
            //状态码500
            msg.put(DeviceConstant.QRCODE_STATUS, 500);
            return msg;
        }
    }

    /**
     * 微信小程序注册
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {

        Map<String, Object> msg = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();

        if (!StringUtils.isEmpty(user)) {
            //判断信息是否存在空项
            if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getCdKey()) || StringUtils.isEmpty(user.getPassword()) ||
                    StringUtils.isEmpty(user.getIdCardNumber()) || StringUtils.isEmpty(user.getPlateNumber()) || StringUtils.isEmpty(user.getUsername())) {
                msg.put(MobileConstant.MESSAGES, Messages.EXIST_EMPTY_ITEN);
                msg.put(MobileConstant.STATUS_CODE, 404);
                return msg;
            }
            //判断账户是否重复注册
            if (userService.accountRegistered(user.getAccount())) {
                //账户已经被注册
                msg.put(MobileConstant.MESSAGES, Messages.USER_IS＿REGIATERED);
                //状态码为300
                msg.put(MobileConstant.STATUS_CODE, 300);
                return msg;
            } else {
                //获取当前时间
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                //设置账户的创建时间和更新时间
                user.setCreateTime(timestamp);

                String sex = Integer.valueOf(user.getIdCardNumber().charAt(16)) % 2 == 0 ? "女" : "男";

                user.setSex(sex);
                user.setHeadPic(GetDefaultHeadPicUtil.getDefaultHeadPicBySex(sex));

                user.setIdCardFrontPic(CommonConstant.LOCAL_NET_WEB + CommonConstant.ID_CARD_PIC_DEFAULT + CommonConstant.ID_CARD_FRONT_DEFAULT_PIC);
                user.setIdCardReversePic(CommonConstant.LOCAL_NET_WEB + CommonConstant.ID_CARD_PIC_DEFAULT + CommonConstant.ID_CARD_REVERSE_DEFAULT_PIC);
                user.setDriverLicenserPic(CommonConstant.LOCAL_NET_WEB + CommonConstant.DRIVER_LICENSE_PIC_DEFAULT + CommonConstant.DRIVER_LICENSE_DEFAULT_PIC);
                user.setDriverPermistPic(CommonConstant.LOCAL_NET_WEB + CommonConstant.DRIVER_PERMIT_PIC_DEFAULT + CommonConstant.DRIVER_PERMIT_DEFAULT_PIC);

                //存入数据库
                userService.createUser(user);
                //从数据库中获取刚存入的user
                user = userService.findByUserAccount(user.getAccount());
                //用户注册成功
                msg.put(MobileConstant.MESSAGES, Messages.REGISTER_SUCCESS);
                //状态码200
                msg.put(MobileConstant.STATUS_CODE, 200);
                //存入sessionId
                msg.put(MobileConstant.SESSION_ID, request.getSession().getId());
                //注册成功后自动进行登录
                httpSession.setAttribute(UserConstant.USER, user);
                return msg;
            }
        } else {
            //服务器内部错误
            msg.put(MobileConstant.MESSAGES, Messages.SERVER_INNER_ERROR);
            //状态码500
            msg.put(MobileConstant.STATUS_CODE, 500);
            return msg;
        }
    }

    /**
     * 用户手机扫码后确认登陆登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/devicelogin")
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
                Device device = deviceService.findByDeviceId(deviceId);
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
    @RequestMapping("/scanqrcode")
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

    /**
     * 上传用户信息图片
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/detailpic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadDetailPic(HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam(value = "img", required = true) MultipartFile img,
                                               @RequestParam(value = "type", required = false) Integer type, HttpSession session) {

        //消息集合
        Map<String, Object> msg = new HashMap<String, Object>();

        //从数据库中查询User
//        User user = userService.findByUserId(((User)session.getAttribute(MobileConstant.USER)).getUserId());

        //获取原文件名
        String originalFilename = img.getOriginalFilename();

        //获取分隔符下标
        Integer splitMark = originalFilename.indexOf(".");

        //获取文件后缀
        String stuffix = (splitMark == -1 ? null : originalFilename.substring(splitMark + 1, originalFilename.length()));

        //获取新文件名
        String newImgName = UUID.randomUUID().toString() + "." + stuffix;

        switch (type) {
            //上传的是用户头像
            case MobileConstant.USER_PIC_HEAD: {
                try {
                    String imgAddress = CommonConstant.OUTER_NET_WEB + CommonConstant.USER_PROFILE_PIC + newImgName;
//                    user.setHeadPic(imgAddress);

                    File dest = new File(session.getServletContext().getRealPath(CommonConstant.USER_PROFILE_PIC) + "\\" + newImgName);

                    if (!dest.exists()) {
                        dest.mkdirs();
                    }

                    img.transferTo(dest);
                    msg.put("statusCode", 200);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            //上传的时候身份证正面
            case MobileConstant.USER_ID_CARD_FRONT_PIC: {
                try {
                    String imgAddress = CommonConstant.OUTER_NET_WEB + CommonConstant.USER_ID_CARD_PIC + newImgName;
//                    user.setIdCardFrontPic(imgAddress);
                    img.transferTo(new File(session.getServletContext().getRealPath(CommonConstant.USER_PROFILE_PIC) + "\\" + newImgName));
                    msg.put("statusCode", 200);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            //上传的是身份证反面
            case MobileConstant.USER_IC_CARD_REVERSE_PIC: {
                try {
                    String imgAddress = CommonConstant.OUTER_NET_WEB + CommonConstant.USER_ID_CARD_PIC + newImgName;
//                    user.setIdCardReversePic(imgAddress);
                    img.transferTo(new File(session.getServletContext().getRealPath(CommonConstant.USER_PROFILE_PIC) + "\\" + newImgName));
                    msg.put("statusCode", 200);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            //上传的是驾驶证
            case MobileConstant.USER_DRIVER_LICENSER_PIC: {
                try {
                    String imgAddress = CommonConstant.OUTER_NET_WEB + CommonConstant.USER_LICENSER_PIC + newImgName;
//                    user.setDriverLicenserPic(imgAddress);
                    img.transferTo(new File(session.getServletContext().getRealPath(CommonConstant.USER_PROFILE_PIC) + "\\" + newImgName));
                    msg.put("statusCode", 200);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            //上传的是行车证
            case MobileConstant.USER_DRIVER_PERMIST_PIC: {
                try {
                    String imgAddress = CommonConstant.OUTER_NET_WEB + CommonConstant.USER_DRIVER_PERMIST_PIC + newImgName;
//                    user.setDriverPermistPic(imgAddress);
                    img.transferTo(new File(session.getServletContext().getRealPath(CommonConstant.USER_PROFILE_PIC) + "\\" + newImgName));
                    msg.put("statusCode", 200);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            default: {

            }
        }

        //更新到用户数据
//        userService.updateUser(user);

        return msg;
    }

    /**
     * 获取用户详细信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getDetailInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Map<String, Object> msg = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从当前会话中获取User对象
        User user = (User) session.getAttribute(UserConstant.USER);
        msg.put("account", user.getAccount());
        msg.put("icCardNumber", user.getIdCardNumber());
        msg.put("plateNumber", user.getUsername());
        msg.put("cdKey", user.getCdKey());
        msg.put("birthday", new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday()));
        msg.put("sex", user.getSex());
        msg.put("mobile", user.getMobile());
        msg.put("headPic", user.getHeadPic());
        msg.put("idCardFrontPic", user.getIdCardFrontPic());
        msg.put("idCardReversePic", user.getIdCardReversePic());
        msg.put("driverLicensePic", user.getDriverLicenserPic());
        msg.put("driverPermitPic", user.getDriverPermistPic());
        msg.put("createTime", sdf.format(user.getCreateTime()));
        return msg;
    }

    /**
     * 更新用户的详细信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateDetailInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> msg = new HashMap<String, Object>();
        return msg;
    }

}
