package cn.guet.navigator.web.controller.mobile;

import cn.guet.navigator.web.constant.user.DeviceConstant;
import cn.guet.navigator.web.constant.user.MobileConstant;
import cn.guet.navigator.web.constant.user.UserConstant;
import cn.guet.navigator.web.pojo.Position;
import cn.guet.navigator.web.pojo.User;
import cn.guet.navigator.web.service.LoginRecordService;
import cn.guet.navigator.web.service.PositionService;
import cn.guet.navigator.web.service.UserService;
import cn.guet.navigator.web.utils.GetDefaultHeadPicUtil;
import cn.guet.navigator.web.constant.Messages;
import cn.guet.navigator.web.constant.common.CommonConstant;
import cn.guet.navigator.web.utils.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/mobile/user")
public class MobileUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginRecordService loginRecordService;
    @Autowired
    private PositionService positionService;

    /**
     * 微信小程序通过账号密码登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
                String birthday = user.getIdCardNumber();
                StringBuilder sb = new StringBuilder();
                sb.append(birthday.substring(6,10)).append("-").append(birthday.substring(10,12)).append("-").append(birthday.substring(12,14));
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    user.setBirthday(sf.parse(sb.toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //获取当前时间
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                //设置账户的创建时间和更新时间
                user.setCreateTime(timestamp);
                String sex = Integer.valueOf(user.getIdCardNumber().charAt(16)) % 2 == 0 ? "女" : "男";
                user.setSex(sex);
                user.setHeadPic(GetDefaultHeadPicUtil.getDefaultHeadPicBySex(sex));
                user.setCdKey(user.getCdKey());
                user.setIdCardFrontPic(CommonConstant.LOCAL_NET_WEB + CommonConstant.ID_CARD_PIC_DEFAULT + CommonConstant.ID_CARD_FRONT_DEFAULT_PIC);
                user.setIdCardReversePic(CommonConstant.LOCAL_NET_WEB + CommonConstant.ID_CARD_PIC_DEFAULT + CommonConstant.ID_CARD_REVERSE_DEFAULT_PIC);
                user.setDriverLicensePic(CommonConstant.LOCAL_NET_WEB + CommonConstant.DRIVER_LICENSE_PIC_DEFAULT + CommonConstant.DRIVER_LICENSE_DEFAULT_PIC);
                user.setDriverPermitPic(CommonConstant.LOCAL_NET_WEB + CommonConstant.DRIVER_PERMIT_PIC_DEFAULT + CommonConstant.DRIVER_PERMIT_DEFAULT_PIC);
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
     * 上传用户信息图片
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/upload-picture", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadDetailPic(HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam(value = "img", required = true) MultipartFile img,
                                               @RequestParam(value = "type", required = false) Integer type, HttpSession session) {

        //消息集合
        Map<String, Object> msg = new HashMap<String, Object>();
        //从数据库中查询User
        User user = userService.findByUserId(((User) session.getAttribute(UserConstant.USER)).getUserId());
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
                    String imgAddress = CommonConstant.LOCAL_NET_WEB + CommonConstant.USER_PROFILE_PIC + newImgName;
                    user.setHeadPic(imgAddress);
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
                    String imgAddress = CommonConstant.LOCAL_NET_WEB + CommonConstant.USER_ID_CARD_PIC + newImgName;
                    user.setIdCardFrontPic(imgAddress);
                    user.setHeadPic(imgAddress);
                    File dest = new File(session.getServletContext().getRealPath(CommonConstant.USER_PROFILE_PIC) + "\\" + newImgName);
                    if (!dest.exists()) {
                        dest.mkdirs();
                    }
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
                    String imgAddress = CommonConstant.LOCAL_NET_WEB + CommonConstant.USER_ID_CARD_PIC + newImgName;
                    user.setIdCardReversePic(imgAddress);
                    user.setHeadPic(imgAddress);
                    File dest = new File(session.getServletContext().getRealPath(CommonConstant.USER_PROFILE_PIC) + "\\" + newImgName);
                    if (!dest.exists()) {
                        dest.mkdirs();
                    }
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
                    String imgAddress = CommonConstant.LOCAL_NET_WEB + CommonConstant.USER_LICENSE_PIC + newImgName;
                    user.setDriverLicensePic(imgAddress);
                    user.setHeadPic(imgAddress);
                    File dest = new File(session.getServletContext().getRealPath(CommonConstant.USER_PROFILE_PIC) + "\\" + newImgName);
                    if (!dest.exists()) {
                        dest.mkdirs();
                    }
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
                    String imgAddress = CommonConstant.LOCAL_NET_WEB + CommonConstant.USER_DRIVER_PERMIT_PIC + newImgName;
                    user.setDriverPermitPic(imgAddress);
                    user.setHeadPic(imgAddress);
                    File dest = new File(session.getServletContext().getRealPath(CommonConstant.USER_PROFILE_PIC) + "\\" + newImgName);
                    if (!dest.exists()) {
                        dest.mkdirs();
                    }
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
        userService.updateUser(user);

        return msg;
    }

    /**
     * 获取用户详细信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getDetailInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Map<String, Object> msg = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从当前会话中获取User对象
        User tmp = (User) session.getAttribute(UserConstant.USER);
        User user = userService.findByUserId(tmp.getUserId());
        //更新session中的user对象
        session.setAttribute(UserConstant.USER, user);
        msg.put("account", user.getAccount());
        msg.put("idCardNumber", user.getIdCardNumber());
        msg.put("plateNumber", user.getPlateNumber());
        msg.put("cdKey", user.getCdKey());
//        msg.put("birthday", new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday()));
        msg.put("sex", user.getSex());
        msg.put("mobile", user.getMobile());
        msg.put("headPic", user.getHeadPic());
        msg.put("idCardFrontPic", user.getIdCardFrontPic());
        msg.put("idCardReversePic", user.getIdCardReversePic());
        msg.put("driverLicensePic", user.getDriverLicensePic());
        msg.put("driverPermitPic", user.getDriverPermitPic());
        msg.put("username", user.getUsername());
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
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateDetailInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> msg = new HashMap<String, Object>();
        return msg;
    }

    static double count = 0.01;

    /**
     * 微信小程序获取小车当前的位置
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/current-device-location", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getDeviceCurrentLocation(HttpServletRequest request, HttpServletResponse response) {
//        Map<String, Object> msg = new HashMap<String, Object>();
//        HttpSession httpSession = request.getSession();
//        User u = (User) httpSession.getAttribute(UserConstant.USER);
//        LoginRecord loginRecord = loginRecordService.getByUserId(u.getUserId());
//        Device device = loginRecord.getDevice();
        Position position = positionService.getLatestPositionByDeviceId("ac9cfc4e-d28b-3f18-a9e1-4e62b8cf97d3");
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("longitude", position.getLongitude() + (count += 0.01));
        msg.put("latitude", position.getLatitude() + (count += 0.01));
        msg.put("statusCode", 200);
        msg.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(position.getCreateTime()));
        return msg;
    }

    /**
     * 选择导航路线
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/multipoint-plan", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> chooseNavigatorPlan(HttpServletRequest request, HttpServletResponse response, @RequestBody Path path) {
        Map<String, Object> msg = new HashMap<String, Object>();
        return msg;
    }

}
