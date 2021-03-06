package cn.guet.navigator.web.controller.mobile;

import cn.guet.navigator.web.constant.Messages;
import cn.guet.navigator.web.constant.common.CommonConstant;
import cn.guet.navigator.web.constant.common.StatusCode;
import cn.guet.navigator.web.constant.user.DeviceConstant;
import cn.guet.navigator.web.constant.user.MobileConstant;
import cn.guet.navigator.web.constant.user.UserConstant;
import cn.guet.navigator.web.dto.*;
import cn.guet.navigator.web.pojo.*;
import cn.guet.navigator.web.service.UserService;
import cn.guet.navigator.web.utils.*;
import com.alibaba.fastjson.JSONObject;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/mobile/user")
public class MobileUserController {

    @Autowired
    private UserService userService;

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
                //从身份证号码中获取出生年月日
                user.setBirthday(GetBirthdayFromID.pick(user.getIdCardNumber()));
                //获取当前时间
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                //设置账户的创建时间和更新时间
                user.setCreateTime(currentTime);
                //从身份号码中提取性别
                user.setSex(GetSexFromID.pick(user.getIdCardNumber()));
                //生成头像实体
                Photo headPic = new Photo();

                //设置头像的url
                headPic.setUrl(GetDefaultHeadPicUtil.getDefaultHeadPicBySex(user.getSex()));
                headPic.setPrefix(CommonConstant.MAN_DEFAULT_PROFILE_PIC);
                headPic.setSuffix(CommonConstant.DEFAULT_SUFFIX);
                headPic.setCreateTime(currentTime);
                headPic.setUpdateTime(currentTime);
                user.setHeadPic(headPic);

                //身份证正面
                Photo idCardFrontPic = new Photo();
                idCardFrontPic.setUrl(CommonConstant.ID_CARD_FRONT_URL);
                idCardFrontPic.setSuffix(CommonConstant.ID_CARD_FRONT_DEFAULT_PIC);
                idCardFrontPic.setPrefix(CommonConstant.DEFAULT_SUFFIX);
                idCardFrontPic.setUpdateTime(currentTime);
                idCardFrontPic.setCreateTime(currentTime);
                user.setIdCardFrontPic(idCardFrontPic);

                //身份证反面
                Photo idCardReversePic = new Photo();
                idCardReversePic.setUrl(CommonConstant.ID_CARD_REVERSE_URL);
                idCardReversePic.setPrefix(CommonConstant.ID_CARD_REVERSE_DEFAULT_PIC);
                idCardReversePic.setSuffix(CommonConstant.DEFAULT_SUFFIX);
                idCardReversePic.setCreateTime(currentTime);
                idCardReversePic.setUpdateTime(currentTime);
                user.setIdCardReversePic(idCardReversePic);

                //驾驶证
                Photo driverLicensePic = new Photo();
                driverLicensePic.setUrl(CommonConstant.DRIVER_LICENSE_URL);
                driverLicensePic.setPrefix(CommonConstant.DRIVER_LICENSE_DEFAULT_PIC);
                driverLicensePic.setSuffix(CommonConstant.DEFAULT_SUFFIX);
                driverLicensePic.setCreateTime(currentTime);
                driverLicensePic.setUpdateTime(currentTime);
                user.setDriverLicensePic(driverLicensePic);

                //行车证
                Photo driverPermitPic = new Photo();
                driverPermitPic.setUrl(CommonConstant.DRIVER_PERMIT_URL);
                driverPermitPic.setPrefix(CommonConstant.DRIVER_PERMIT_DEFAULT_PIC);
                driverPermitPic.setSuffix(CommonConstant.DEFAULT_SUFFIX);
                driverPermitPic.setCreateTime(currentTime);
                driverPermitPic.setUpdateTime(currentTime);
                user.setDriverPermitPic(driverPermitPic);

                //设置产品代码
                user.setCdKey(user.getCdKey());
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
    public Map<String, Object> uploadUserPicture(HttpServletRequest request, HttpServletResponse response,
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
        //随机生成文件前缀
        String prefix = UUID.randomUUID().toString();
        //获取新文件名
        String newImgName = prefix + "." + stuffix;
        //当前时间
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        switch (type) {
            //上传的是用户头像
            case MobileConstant.USER_PIC_HEAD: {
                try {
                    File dest = new File(session.getServletContext().getRealPath(CommonConstant.USER_PROFILE_PIC) + "\\" + newImgName);
                    if (!dest.exists()) {
                        dest.mkdirs();
                    }
                    img.transferTo(dest);
                    String url = CommonConstant.LOCAL_NET_WEB + CommonConstant.USER_PROFILE_PIC + newImgName;
                    Photo photo = new Photo();
                    photo.setUrl(url);
                    photo.setPrefix(prefix);
                    photo.setSuffix(stuffix);
                    photo.setCreateTime(currentTime);
                    photo.setUpdateTime(currentTime);
                    user.setHeadPic(photo);
                    msg.put(CommonConstant.STATUS_CODE, StatusCode.SUCCESS);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            //上传的时候身份证正面
            case MobileConstant.USER_ID_CARD_FRONT_PIC: {
                try {
                    File dest = new File(session.getServletContext().getRealPath(CommonConstant.USER_ID_CARD_PIC) + "\\" + newImgName);
                    if (!dest.exists()) {
                        dest.mkdirs();
                    }
                    img.transferTo(dest);
                    String url = CommonConstant.LOCAL_NET_WEB + CommonConstant.USER_ID_CARD_PIC + newImgName;
                    Photo photo = new Photo();
                    photo.setUrl(url);
                    photo.setPrefix(prefix);
                    photo.setSuffix(stuffix);
                    photo.setCreateTime(currentTime);
                    photo.setUpdateTime(currentTime);
                    user.setIdCardFrontPic(photo);
                    msg.put(CommonConstant.STATUS_CODE, StatusCode.SUCCESS);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            //上传的是身份证反面
            case MobileConstant.USER_IC_CARD_REVERSE_PIC: {
                try {
                    File dest = new File(session.getServletContext().getRealPath(CommonConstant.USER_ID_CARD_PIC) + "\\" + newImgName);
                    if (!dest.exists()) {
                        dest.mkdirs();
                    }
                    img.transferTo(dest);
                    String url = CommonConstant.LOCAL_NET_WEB + CommonConstant.USER_ID_CARD_PIC + newImgName;
                    Photo photo = new Photo();
                    photo.setUrl(url);
                    photo.setPrefix(prefix);
                    photo.setSuffix(stuffix);
                    photo.setCreateTime(currentTime);
                    photo.setUpdateTime(currentTime);
                    user.setIdCardReversePic(photo);
                    msg.put(CommonConstant.STATUS_CODE, StatusCode.SUCCESS);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            //上传的是驾驶证
            case MobileConstant.USER_DRIVER_LICENSE_PIC: {
                try {
                    File dest = new File(session.getServletContext().getRealPath(CommonConstant.USER_LICENSE_PIC) + "\\" + newImgName);
                    if (!dest.exists()) {
                        dest.mkdirs();
                    }
                    img.transferTo(dest);
                    String url = CommonConstant.LOCAL_NET_WEB + CommonConstant.USER_LICENSE_PIC + newImgName;
                    Photo photo = new Photo();
                    photo.setUrl(url);
                    photo.setPrefix(prefix);
                    photo.setSuffix(stuffix);
                    photo.setCreateTime(currentTime);
                    photo.setUpdateTime(currentTime);
                    user.setDriverLicensePic(photo);
                    msg.put(CommonConstant.STATUS_CODE, StatusCode.SUCCESS);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            //上传的是行车证
            case MobileConstant.USER_DRIVER_PERMIT_PIC: {
                try {
                    File dest = new File(session.getServletContext().getRealPath(CommonConstant.USER_DRIVER_PERMIT_PIC) + "\\" + newImgName);
                    if (!dest.exists()) {
                        dest.mkdirs();
                    }
                    img.transferTo(dest);
                    String url = CommonConstant.LOCAL_NET_WEB + CommonConstant.USER_DRIVER_PERMIT_PIC + newImgName;
                    Photo photo = new Photo();
                    photo.setUrl(url);
                    photo.setPrefix(prefix);
                    photo.setSuffix(stuffix);
                    photo.setCreateTime(currentTime);
                    photo.setUpdateTime(currentTime);
                    user.setDriverPermitPic(photo);
                    msg.put(CommonConstant.STATUS_CODE, StatusCode.SUCCESS);
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
        msg.put(UserConstant.ACCOUNT, user.getAccount());
        msg.put(UserConstant.ID_CARD_NUMBER, user.getIdCardNumber());
        msg.put(UserConstant.PLATE_NUMBER, user.getPlateNumber());
        msg.put(UserConstant.CD_KEY, user.getCdKey());
        msg.put(UserConstant.BIRTHDAY, new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday()));
        msg.put(UserConstant.SEX, user.getSex());
        msg.put(UserConstant.MOBILE, user.getMobile());
        msg.put(UserConstant.HEAD_PIC, user.getHeadPic().getUrl());
        msg.put(UserConstant.ID_CARD_FRONT_PIC, user.getIdCardFrontPic().getUrl());
        msg.put(UserConstant.ID_CARD_REVERSE_PIC, user.getIdCardReversePic().getUrl());
        msg.put(UserConstant.DRIVER_LICENSE_PIC, user.getDriverLicensePic().getUrl());
        msg.put(UserConstant.DRIVER_PERMIT_PIC, user.getDriverPermitPic().getUrl());
        msg.put(UserConstant.USERNAME, user.getUsername());
        msg.put(UserConstant.CREATE_TIME, sdf.format(user.getCreateTime()));
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
//        Position position = positionService.getLatestPositionByDeviceId("ac9cfc4e-d28b-3f18-a9e1-4e62b8cf97d3");
        Position position = new Position();
        position.setLongitude(110.419122);
        position.setLatitude(25.313339);
        position.setCreateTime(new Timestamp(System.currentTimeMillis()));
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("longitude", position.getLongitude());
        msg.put("latitude", position.getLatitude());
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
    public Map<String, Object> chooseNavigatorPlan(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Location> destinations) {

        Map<String, Object> msg = new HashMap<String, Object>();

        //起始点
        Location current = new Location(110.336514, 25.283836);

        List<List<Navigation>> navList = new ArrayList<List<Navigation>>();

        List<Navigation> speed = new ArrayList<Navigation>();
        List<Navigation> distance = new ArrayList<Navigation>();
        List<Navigation> congestion = new ArrayList<Navigation>();

        Iterator<Location> locationIterator = destinations.iterator();

        while (locationIterator.hasNext()) {
            Location destination = locationIterator.next();
            speed.add(JSONObject.parseObject(PathPlanUtil.path(current,
                    destination, PathPlanUtil.STRATEGY_SPEED_FIRST), Navigation.class));
            distance.add(JSONObject.parseObject(PathPlanUtil.path(current,
                    destination, PathPlanUtil.STRATEGY_DISTANCE_FIRST), Navigation.class));
            congestion.add(JSONObject.parseObject(PathPlanUtil.path(current,
                    destination, PathPlanUtil.STRATEGY_CONGESTION_FIRST), Navigation.class));
            current = destination;
        }

        navList.add(speed);
        navList.add(distance);
        navList.add(congestion);

        /**
         * 速度最快方案
         */
        RoutePlan speedRoutePlan = new RoutePlan();
        speedRoutePlan.setPlanname(UserConstant.STRATEGY_SPEED);
        /**
         * 距离最短
         */
        RoutePlan distanceRoutePlan = new RoutePlan();
        distanceRoutePlan.setPlanname(UserConstant.STRATEGY_DISTANCE);
        /**
         * 避免拥堵
         */
        RoutePlan congestionRoutePlan = new RoutePlan();
        congestionRoutePlan.setPlanname(UserConstant.STRATEGY_VOID_CONGESTION);

        List<RoutePlan> routePlans = new ArrayList<RoutePlan>();
        routePlans.add(speedRoutePlan);
        routePlans.add(distanceRoutePlan);
        routePlans.add(congestionRoutePlan);

        Iterator<List<Navigation>> navIterator = navList.iterator();
        Iterator<RoutePlan> routePlanIterator = routePlans.iterator();

        while (navIterator.hasNext()) {

            List<Navigation> navigations = navIterator.next();
            Iterator<Navigation> navigationIterator = navigations.iterator();
            RoutePlan routePlan = routePlanIterator.next();
            HashSet<String> locationNameSet = new HashSet<String>();

            List<Location> passPoints = new ArrayList<Location>();

            while (navigationIterator.hasNext()) {

                Navigation speedNavigation = navigationIterator.next();
                Iterator<Route> speedIterator = speedNavigation.getRoute().iterator();

                while (speedIterator.hasNext()) {

                    Route speedRoute = speedIterator.next();
                    Iterator<TransferScheme> speedTransferSchemeIterator = speedRoute.getPaths().iterator();

                    while (speedTransferSchemeIterator.hasNext()) {

                        TransferScheme speedTransferScheme = speedTransferSchemeIterator.next();
                        routePlan.setDistance(routePlan.getDistance() + speedTransferScheme.getDistance());
                        routePlan.setDuration(routePlan.getDuration() + speedTransferScheme.getDuration());

                        Iterator<Step> speedStepsIterator = speedTransferScheme.getSteps().iterator();

                        while (speedStepsIterator.hasNext()) {

                            Step speedStep = speedStepsIterator.next();

                            StepInfo speedTmp = new StepInfo();
                            speedTmp.setDistance(speedStep.getDistance());
                            speedTmp.setDuration(speedStep.getDuration());

                            String[] speedPos = (speedStep.getPolyline().split(";"))[1].split(",");
                            Double speedLongitude = Double.valueOf(speedPos[0]);
                            Double speedLatitude = Double.valueOf(speedPos[1]);

                            Address speedAddress = JSONObject.parseObject(LocationQueryUtil.location(new Location(speedLongitude, speedLatitude)), Address.class);

                            AddressComponent speedAddressComponent = speedAddress.getRegeocode().getAddressComponent();

                            StringBuilder speedOrigin = new StringBuilder(speedAddress.getRegeocode().getFormatted_address());
                            StringBuilder speedPrefix = new StringBuilder().append(speedAddressComponent.getProvince()).
                                    append(speedAddressComponent.getCity()).append(speedAddressComponent.getDistrict()).append(speedAddressComponent.getTownship());
                            speedOrigin.delete(0, speedPrefix.length());

                            if (locationNameSet.contains(speedOrigin.toString())) {
                                Iterator<StepInfo> stepInfoIterator = routePlan.getStepInfos().iterator();
                                while (stepInfoIterator.hasNext()) {
                                    StepInfo stepInfo = stepInfoIterator.next();
                                    if (stepInfo.getStepname().equals(speedOrigin.toString())) {
                                        stepInfo.setDuration(stepInfo.getDuration() + speedTmp.getDuration());
                                        stepInfo.setDistance(stepInfo.getDistance() + speedTmp.getDistance());
                                    }
                                }
                            } else {
                                //抓取道路点，检验道路是否正确
                                passPoints.add(new Location(speedLongitude, speedLatitude));
                                speedTmp.setStepname(speedOrigin.toString());
                                routePlan.getStepInfos().add(speedTmp);
                                locationNameSet.add(speedOrigin.toString());
                            }
                        }
                    }
                }
                //为路线设置途经点
                routePlan.setPassPoints(passPoints);
            }
        }

        msg.put("speedRoutePlan", speedRoutePlan);
        msg.put("distanceRoutePlan", distanceRoutePlan);
        msg.put("congestionRoutePlan", congestionRoutePlan);

        return msg;
    }

    /**
     * 选择导航方案
     *
     * @param routePlan
     * @return
     */
    @RequestMapping(value = "/choose-route-plan", method = RequestMethod.POST)
    @ResponseBody
    public String chooseRoutePlan(@RequestBody RoutePlan routePlan) {
        return "";
    }

    @RequestMapping(value = "/checkRoutePlanPage", method = RequestMethod.GET)
    public String checkRoutePlanPage() {
        return "test/position";
    }

    @RequestMapping(value = "/check-route-plan", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkRoutePlan() {

        Map<String, Object> msg = new HashMap<String, Object>();

        List<Location> destinations = new ArrayList<Location>();

        //途经点
        destinations.add(new Location(110.295334, 25.277452));

        //起始点
        Location current = new Location(110.336514, 25.283836);

        List<List<Navigation>> navList = new ArrayList<List<Navigation>>();

        StringBuilder sb = new StringBuilder("https://restapi.amap.com/v3/staticmap?zoom=15&size=500*500&paths=10,0x0000ff,1,,:");

        List<Navigation> speed = new ArrayList<Navigation>();
        List<Navigation> distance = new ArrayList<Navigation>();
        List<Navigation> congestion = new ArrayList<Navigation>();

        Iterator<Location> locationIterator = destinations.iterator();

        while (locationIterator.hasNext()) {
            Location destination = locationIterator.next();
            speed.add(JSONObject.parseObject(PathPlanUtil.path(current,
                    destination, PathPlanUtil.STRATEGY_SPEED_FIRST), Navigation.class));
            distance.add(JSONObject.parseObject(PathPlanUtil.path(current,
                    destination, PathPlanUtil.STRATEGY_DISTANCE_FIRST), Navigation.class));
            congestion.add(JSONObject.parseObject(PathPlanUtil.path(current,
                    destination, PathPlanUtil.STRATEGY_CONGESTION_FIRST), Navigation.class));
            current = destination;
        }

        navList.add(speed);
        navList.add(distance);
        navList.add(congestion);

        /**
         * 速度最快方案
         */
        RoutePlan speedRoutePlan = new RoutePlan();
        speedRoutePlan.setPlanname(UserConstant.STRATEGY_SPEED);
        /**
         * 距离最短
         */
        RoutePlan distanceRoutePlan = new RoutePlan();
        distanceRoutePlan.setPlanname(UserConstant.STRATEGY_DISTANCE);
        /**
         * 避免拥堵
         */
        RoutePlan congestionRoutePlan = new RoutePlan();
        congestionRoutePlan.setPlanname(UserConstant.STRATEGY_VOID_CONGESTION);

        List<RoutePlan> routePlans = new ArrayList<RoutePlan>();
        routePlans.add(speedRoutePlan);
        routePlans.add(distanceRoutePlan);
        routePlans.add(congestionRoutePlan);

        Iterator<List<Navigation>> navIterator = navList.iterator();
        Iterator<RoutePlan> routePlanIterator = routePlans.iterator();

        while (navIterator.hasNext()) {

            List<Navigation> navigations = navIterator.next();
            Iterator<Navigation> navigationIterator = navigations.iterator();
            RoutePlan routePlan = routePlanIterator.next();
            HashSet<String> locationNameSet = new HashSet<String>();

            List<Location> passPoints = new ArrayList<Location>();

            while (navigationIterator.hasNext()) {

                Navigation speedNavigation = navigationIterator.next();
                Iterator<Route> speedIterator = speedNavigation.getRoute().iterator();

                while (speedIterator.hasNext()) {

                    Route speedRoute = speedIterator.next();
                    Iterator<TransferScheme> speedTransferSchemeIterator = speedRoute.getPaths().iterator();

                    while (speedTransferSchemeIterator.hasNext()) {

                        TransferScheme speedTransferScheme = speedTransferSchemeIterator.next();
                        routePlan.setDistance(routePlan.getDistance() + speedTransferScheme.getDistance());
                        routePlan.setDuration(routePlan.getDuration() + speedTransferScheme.getDuration());

                        Iterator<Step> speedStepsIterator = speedTransferScheme.getSteps().iterator();

                        while (speedStepsIterator.hasNext()) {

                            Step speedStep = speedStepsIterator.next();

                            StepInfo speedTmp = new StepInfo();
                            speedTmp.setDistance(speedStep.getDistance());
                            speedTmp.setDuration(speedStep.getDuration());

                            String[] speedPos = (speedStep.getPolyline().split(";"));

                            Double speedLongitude = null;
                            Double speedLatitude = null;

                            for (int x = 0; x < speedPos.length; x++) {
                                Double firstLongitude = Double.valueOf(speedPos[x].split(",")[0]);
                                Double firstLatitude = Double.valueOf(speedPos[x].split(",")[1]);
                                if (x == 0) {
                                    speedLongitude = firstLongitude;
                                    speedLatitude = firstLatitude;
                                }
                                passPoints.add(new Location(firstLongitude, firstLatitude));
                                sb.append(firstLongitude + "," + firstLatitude + ";");
                            }

                            Address speedAddress = JSONObject.parseObject(LocationQueryUtil.location(new Location(speedLongitude, speedLatitude)), Address.class);

                            AddressComponent speedAddressComponent = speedAddress.getRegeocode().getAddressComponent();

                            StringBuilder speedOrigin = new StringBuilder(speedAddress.getRegeocode().getFormatted_address());
                            StringBuilder speedPrefix = new StringBuilder().append(speedAddressComponent.getProvince()).
                                    append(speedAddressComponent.getCity()).append(speedAddressComponent.getDistrict()).append(speedAddressComponent.getTownship());
                            speedOrigin.delete(0, speedPrefix.length());

//                            if (locationNameSet.contains(speedOrigin.toString())) {
//                                Iterator<StepInfo> stepInfoIterator = routePlan.getStepInfos().iterator();
//                                while (stepInfoIterator.hasNext()) {
//                                    StepInfo stepInfo = stepInfoIterator.next();
//                                    if (stepInfo.getStepname().equals(speedOrigin.toString())) {
//                                        stepInfo.setDuration(stepInfo.getDuration() + speedTmp.getDuration());
//                                        stepInfo.setDistance(stepInfo.getDistance() + speedTmp.getDistance());
//                                    }
//                                }
//                            } else {
                            //抓取道路点，检验道路是否正确
//                                speedTmp.setStepname(speedOrigin.toString());

                            routePlan.getStepInfos().add(speedTmp);
                            locationNameSet.add(speedOrigin.toString());
//                            }
                        }
                    }
                }
                //为路线设置途经点
                routePlan.setPassPoints(passPoints);
                String 关键字 = "&key=3cf2cf32d943b809c8281b6ee305576d";
                System.out.println(sb.append(关键字).toString());
            }
        }
        msg.put("speedRoutePlan", speedRoutePlan);
        msg.put("distanceRoutePlan", distanceRoutePlan);
        msg.put("congestionRoutePlan", congestionRoutePlan);

        return msg;
    }

    /***
     * 单个目的地查询
     *
     * @return
     */
    @RequestMapping(value = "/point-info", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> singlePointInfo(@RequestBody Location dest) {
        Map<String, Object> msg = new HashMap<String, Object>();
        Location start = new Location(110.336514, 25.283836);
        Navigation navigation = JSONObject.parseObject(PathPlanUtil.path(start, dest, PathPlanUtil.STRATEGY_SPEED_FIRST), Navigation.class);
        if (navigation.getStatus() == MobileConstant.REQUEST_OK) {
            List<Step> steps = navigation.getRoute().get(0).getPaths().get(0).getSteps();
            Iterator<Step> stepIterator = steps.iterator();
            int count = 0;
            double score = 0.0, status = 0.0;
            while (stepIterator.hasNext()) {
                Step step = stepIterator.next();
                List<Tmc> tmcs = step.getTmcs();
                count += tmcs.size();
                Iterator<Tmc> tmcIterator = tmcs.iterator();
                while (tmcIterator.hasNext()) {
                    Tmc tmc = tmcIterator.next();
                    if (tmc.getStatus().equals(MobileConstant.ROAD_STATUS_UNKNOW)) {
                        score += MobileConstant.ROAD_STATUS_UNKNOW_VALUE;
                    } else if (tmc.getStatus().equals(MobileConstant.ROAD_STATUS_UNBLOCK)) {
                        score += MobileConstant.ROAD_STATUS_UNBLOCK_VALUE;
                    } else if (tmc.getStatus().equals(MobileConstant.ROAD_STATUS_AMBLE)) {
                        score += MobileConstant.ROAD_STATUS_AMBLE_VALUE;
                    } else if (tmc.getStatus().equals(MobileConstant.ROAD_STATUS_CONGESTION)) {
                        score += MobileConstant.ROAD_STATUS_CONGESTION_VALUE;
                    } else if (tmc.getStatus().equals(MobileConstant.ROAD_STATUS_STRICT_CONGESTION)) {
                        score += MobileConstant.ROAD_STATUS_STRICT_CONGESTION_VALUE;
                    }
                }
            }
            status = score / count;
            TransferScheme transferScheme = navigation.getRoute().get(0).getPaths().get(0);
            msg.put(MobileConstant.ROAD_STATUS, status);
            msg.put(MobileConstant.ROAD_DURATION, transferScheme.getDuration());
            msg.put(MobileConstant.ROAD_DISTANCE, transferScheme.getDistance());
            msg.put(CommonConstant.STATUS_CODE, StatusCode.SUCCESS);
        } else {
            //服务器请求失败
            msg.put(CommonConstant.STATUS_CODE, 500);
        }
        return msg;
    }
}
