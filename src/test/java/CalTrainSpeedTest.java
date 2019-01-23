package com.guet.navigator.web.test;

import cn.guet.navigator.web.pojo.backup.TrainSpeed;
import cn.guet.navigator.web.python.CalSpeed;
import cn.guet.navigator.web.service.TrainSpeedService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CalTrainSpeedTest {

    @Autowired
    private TrainSpeedService trainSpeedService;

    @Test
    public void calSpeed() {

        List<String> roadIds = trainSpeedService.countAllDistinctRoadId();

        List<TrainSpeed> total = new ArrayList<TrainSpeed>();

        for (int x = 0; x < roadIds.size(); x++) {
            String roadId = roadIds.get(x);
            List<TrainSpeed> trainSpeedList = trainSpeedService.listTrainSpeedByRoadId(roadId);
            total.addAll(trainSpeedList);
        }

        for (int z = 0; z < total.size(); z++) {
            long time = total.get(z).getPresentTime().getTime() / 1000L;
            total.get(z).setCurrentTime((int) time);
        }

        String speeds = CalSpeed.cal(total);
        String[] sp = speeds.split(",");
        System.out.println(Arrays.toString(sp));
        for (int y = 0; y < sp.length; y++) {
            total.get(y).setSpeed(Double.valueOf(sp[y]));
            trainSpeedService.updateTrainSpeed(total.get(y));
        }

    }

}
