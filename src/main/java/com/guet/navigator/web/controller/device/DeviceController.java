package com.guet.navigator.web.controller.device;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guet.navigator.web.constant.user.DeviceConstant;
import com.guet.navigator.web.pojo.*;
import com.guet.navigator.web.python.PathQuery;
import com.guet.navigator.web.service.*;
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
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/device/user")
public class DeviceController {

    @Autowired
    private DeviceLoginRecordService deviceRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoadService roadService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private TrainSpeedService trainSpeedService;

    /**
     * 安卓设备请求获取用于生产二维码的字符串
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login/{deviceId}", method = RequestMethod.GET)
    @ResponseBody
    public QRCodeVo requestQRCode(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "deviceId") String deviceId) {

        //生成qrCodeStr唯一标识
        String qrCodeStr = UUID.randomUUID().toString();

        //获取当前会话的session
        HttpSession httpSession = request.getSession();

        //设置session的最大的生命周期为2分钟
        httpSession.setMaxInactiveInterval(60);

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
    @RequestMapping(value = "/login/{deviceId}", method = RequestMethod.PUT)
    @ResponseBody
    public DeviceLoginMessageVo login(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "deviceId") String deviceId) {

        //获取Session,若session失效则不创建
        HttpSession httpSession = request.getSession(false);

        //若session未失效
        if (!StringUtils.isEmpty(httpSession)) {

            long recordTime = httpSession.getCreationTime();
            long currentTime = System.currentTimeMillis();

            //session的时间超过60秒
            if ((currentTime - recordTime) >= 60000 * 60) {

                //设置Session失效
                httpSession.invalidate();

                //若session失效
                return new DeviceLoginMessageVo(3);

            } else {
                //查询数据库
                DeviceLoginRecord deviceRecord = deviceRecordService.findByDeviceId(deviceId);

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
     * 设备获取用户的详细信息
     *
     * @param request
     * @param response
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/detail/{deviceId}", method = RequestMethod.GET)
    @ResponseBody
    public User getDetailInfo(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "deviceId") String deviceId) {

        DeviceLoginRecord deviceRecord = deviceRecordService.findByDeviceId(deviceId);

        User u = deviceRecord.getUser();

        User user = userService.findByUserId(u.getUserId());

        return user;

    }

    /**
     * 存储小车实时(间隔为1秒)的定位数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/position", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> stroeDevicePosition(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> msg = new HashMap<String, Object>();
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[1000];
        try {
            InputStream inputStream = request.getInputStream();
            while (inputStream.read(bytes) != -1) {
                sb.append(new String(bytes, "utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] datas = sb.toString().split(".");
        for (int x=0;x<datas.length;x++){
            if (StringUtils.isEmpty(datas[x])){
                msg.put("statusCode",500);
                return msg;
            }
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Device device = deviceService.findByDeviceId(datas[0]);
            if(StringUtils.isEmpty(device)){
                msg.put("statusCode",600);
            }else{
                Position position = new Position();
                position.setDevice(device);
                position.setLongitude(Double.valueOf(datas[1]));
                position.setLatitude(Double.valueOf(datas[2]));
                position.setSpeed(Double.valueOf(datas[3]));
                position.setPresentTime(new Timestamp(sf.parse(datas[4]).getTime()));
                if(positionService.save(position)){
                    msg.put("statusCode",200);
                }else{
                    msg.put("statusCode",500);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 选择最佳的规划路线
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/planpath", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> congestionCalculation(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        List<PlanRoute> planRouteList = JSON.parseArray(data, PlanRoute.class);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Road> roadListOld = roadService.listAllRoad();
        List<Road> roadList = new ArrayList<Road>();
        List<Double> avgSpeed = new ArrayList<Double>();
        Map<String, Object> msg = new HashMap<String, Object>();
        for (int d = 0; d < roadListOld.size(); d++) {
            if (roadListOld.get(d).getRoadName().equals("咸宁西路")) {
                roadList.add(roadListOld.get(d));
            }
        }
        try {
            for (int x= 0;x<planRouteList.size();x++){
                Date date = sf.parse("2016-10-31 12:05:23");
                Timestamp startTime = new Timestamp(sf.parse("2016-10-31 12:00:00").getTime());
                Timestamp endTime = new Timestamp(sf.parse("2016-10-31 12:10:00").getTime());
                List<String> roadIds = new ArrayList<String>();
                PlanRoute planRoute = planRouteList.get(x);
                List<Point> totals = new ArrayList<Point>();
                totals.addAll(planRoute.getFrom());
                totals.addAll(planRoute.getPoints());
                totals.addAll(planRoute.getTo());
                for (int y=0;y<totals.size();y++){
                    roadIds.add(PathQuery.query(totals.get(y).getLongitude(),totals.get(y).getLatitude(),roadList));
                }
                for (int z = 0;z<roadIds.size();z++){
                    double totalSpeed = 0.0;
                    List<TrainSpeed> trainSpeedList = trainSpeedService.listTrainSpeedBySpecifyTimeAndRoadId(startTime,endTime,roadIds.get(z));
                    for (int d=0;d<trainSpeedList.size();d++){
                        totalSpeed += trainSpeedList.get(d).getSpeed();
                    }
                    avgSpeed.add(totalSpeed/trainSpeedList.size());
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        msg.put("avgSppedArr",avgSpeed);
        msg.put("statusCode", 200);
        return msg;
    }

//    @RequestMapping(value = "/pdata", method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> getTestPositionData() {
//        Map<String, Object> msg = new HashMap<String, Object>();
//        List<Road> roadList = new ArrayList<Road>();
////        roadList.add(roadService.getRoadByRoadId("40287e8167e12c270167e12c47d80317"));
////        roadList.add(roadService.getRoadByRoadId("40287e8167e12c270167e12c47dd0318"));
////        roadList.add(roadService.getRoadByRoadId("40287e8167e12c270167e12c47e30319"));
//        msg.put("number", roadList.size());
//        msg.put("positions", roadList);
//        return msg;
//    }

//    @RequestMapping("/position")
//    public String testPositionPage() {
//        return "test/position";
//    }

}
