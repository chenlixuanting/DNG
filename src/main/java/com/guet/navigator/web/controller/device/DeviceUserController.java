package com.guet.navigator.web.controller.device;

import com.alibaba.fastjson.JSON;
import com.guet.navigator.web.pojo.LoginRecord;
import com.guet.navigator.web.pojo.User;
import com.guet.navigator.web.pojo.backup.PlanRoute;
import com.guet.navigator.web.pojo.backup.Point;
import com.guet.navigator.web.pojo.backup.Road;
import com.guet.navigator.web.pojo.backup.TrainSpeed;
import com.guet.navigator.web.python.PathQuery;
import com.guet.navigator.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/device/user")
public class DeviceUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoadService roadService;
    @Autowired
    private TrainSpeedService trainSpeedService;
    @Autowired
    private DeviceLoginRecordService deviceRecordService;

    /**
     * 设备获取用户的详细信息
     *
     * @param request
     * @param response
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/info/{deviceId}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserInfo(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "deviceId") String deviceId) {
        LoginRecord deviceRecord = deviceRecordService.findByDeviceId(deviceId);
        User u = deviceRecord.getUser();
        User user = userService.findByUserId(u.getUserId());
        return user;
    }

    /**
     * 选择最佳的规划路线
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/path-plan", method = RequestMethod.POST)
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
            for (int x = 0; x < planRouteList.size(); x++) {
                Date date = sf.parse("2016-10-31 12:05:23");
                Timestamp startTime = new Timestamp(sf.parse("2016-10-31 12:00:00").getTime());
                Timestamp endTime = new Timestamp(sf.parse("2016-10-31 12:10:00").getTime());
                List<String> roadIds = new ArrayList<String>();
                PlanRoute planRoute = planRouteList.get(x);
                List<Point> totals = new ArrayList<Point>();
                totals.addAll(planRoute.getFrom());
                totals.addAll(planRoute.getPoints());
                totals.addAll(planRoute.getTo());
                for (int y = 0; y < totals.size(); y++) {
                    roadIds.add(PathQuery.query(totals.get(y).getLongitude(), totals.get(y).getLatitude(), roadList));
                }
                for (int z = 0; z < roadIds.size(); z++) {
                    double totalSpeed = 0.0;
                    List<TrainSpeed> trainSpeedList = trainSpeedService.listTrainSpeedBySpecifyTimeAndRoadId(startTime, endTime, roadIds.get(z));
                    for (int d = 0; d < trainSpeedList.size(); d++) {
                        totalSpeed += trainSpeedList.get(d).getSpeed();
                    }
                    avgSpeed.add(totalSpeed / trainSpeedList.size());
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        msg.put("avgSppedArr", avgSpeed);
        msg.put("statusCode", 200);
        return msg;
    }

}
