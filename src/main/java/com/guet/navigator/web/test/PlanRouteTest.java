package com.guet.navigator.web.test;

import com.alibaba.fastjson.JSON;
import com.guet.navigator.web.pojo.backup.PlanRoute;
import com.guet.navigator.web.pojo.backup.Point;
import com.guet.navigator.web.pojo.backup.Road;
import com.guet.navigator.web.pojo.backup.TrainSpeed;
import com.guet.navigator.web.python.PathQuery;
import com.guet.navigator.web.service.RoadService;
import com.guet.navigator.web.service.TrainSpeedService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PlanRouteTest {

    @Autowired
    private RoadService roadService;

    @Autowired
    private TrainSpeedService trainSpeedService;

    @Test
    public void plan(){

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String data = "[{\"from\":{\"latitude\":34.25105,\"longitude\":108.975867},\"points\":[{\"latitude\":34.251072,\"longitude\":108.975868},{\"latitude\":34.251087,\"longitude\":108.974808},{\"latitude\":34.25116,\"longitude\":108.972687}],\"to\":{\"latitude\":34.251103,\"longitude\":108.987958}}]";

        List<PlanRoute> planRouteList = JSON.parseArray(data, PlanRoute.class);

        List<Road> roadListOld = roadService.listAllRoad();
        List<Road> roadList = new ArrayList<Road>();

        List<Double> avgSpeed = new ArrayList<Double>();

        for (int d = 0; d < roadListOld.size(); d++) {
            if (roadListOld.get(d).getRoadName().equals("咸宁西路")) {
                roadList.add(roadListOld.get(d));
            }
        }

        try {
            Date date = sf.parse("2016-10-31 12:05:23");
            Timestamp startTime = new Timestamp(sf.parse("2016-10-31 12:00:00").getTime());
            Timestamp endTime = new Timestamp(sf.parse("2016-10-31 12:10:00").getTime());

            for (int x= 0;x<planRouteList.size();x++){

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
                    List<TrainSpeed> trainSpeedList = trainSpeedService.listTrainSpeedBySpecifyTimeAndRoadId(startTime,endTime,roadIds.get(z));
                    double totalSpeed = 0.0;
                    for (int d=0;d<trainSpeedList.size();d++){
                        totalSpeed += trainSpeedList.get(d).getSpeed();
                    }
                    avgSpeed.add(totalSpeed/trainSpeedList.size());
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return;
    }

}
