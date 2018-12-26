package com.guet.navigator.web.test;

import com.guet.navigator.web.pojo.OriginData;
import com.guet.navigator.web.pojo.Road;
import com.guet.navigator.web.pojo.TrainData;
import com.guet.navigator.web.python.PathQuery;
import com.guet.navigator.web.service.OriginDataService;
import com.guet.navigator.web.service.RoadService;
import com.guet.navigator.web.service.TrainDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TrainDataTest {

    @Autowired
    private RoadService roadService;

    @Autowired
    private OriginDataService originDataService;

    @Autowired
    private TrainDataService trainDataService;

    @Test
    public void createTrainData() throws ParseException {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Road> roadListOld = roadService.listAllRoad();
        List<Road> roadList = new ArrayList<Road>();
        List<OriginData> total = new ArrayList<OriginData>();

        Set<String> set = new HashSet<String>();
        Map<String, List<OriginData>> map = new HashMap<String, List<OriginData>>();

        for (int d = 0; d < roadListOld.size(); d++) {
            if (roadListOld.get(d).getRoadName().equals("咸宁西路")) {
                roadList.add(roadListOld.get(d));
            }
        }

        long step1 = 600;
        long startTime1 = sf.parse("2016-10-31 12:00:00").getTime() / 1000;
        long endTime1 = sf.parse("2016-10-31 12:20:00").getTime() / 1000;

        while (startTime1 < endTime1) {
            List<OriginData> originDataList = originDataService.listSpecifyTimeOriginData(startTime1, startTime1 + step1);
            map.put(String.valueOf(startTime1), originDataList);
            startTime1+=step1;
        }

        try {
            for (int x = 0; x < roadList.size(); x++) {

                Road road = roadList.get(x);
                long step = 600;

                long startTime = sf.parse("2016-10-31 12:00:00").getTime() / 1000;
                long endTime = sf.parse("2016-10-31 12:20:00").getTime() / 1000;

                while (startTime < endTime) {

                    set.clear();

                    List<OriginData> originDataList = map.get(String.valueOf(startTime));

                    for (int z = 0; z < originDataList.size(); z++) {

                        if (originDataList.get(z).getCurrentTime() < startTime || originDataList.get(z).getCurrentTime() > endTime) {
                            System.out.println("数据出错!");
                            return;
                        }

                        if (PathQuery.query(originDataList.get(z).getLongitude(), originDataList.get(z).getLatitude(), roadList).equals(road.getRoadId())) {
                            if (set.contains(originDataList.get(z).getDeviceId())) {
                                continue;
                            } else {
                                TrainData trainData = trainDataService.getTrainDataByRoadIdAndSpecifyTime(road.getRoadId(), startTime * 1000, (startTime + step) * 1000);
                                set.add(originDataList.get(z).getDeviceId());
                                if (StringUtils.isEmpty(trainData)) {
                                    TrainData t = new TrainData();
                                    t.setRoadId(road.getRoadId());
                                    t.setStartLongitude(road.getStartLongitude());
                                    t.setStartLatitude(road.getStartLatitude());
                                    t.setEndLongitude(road.getEndLongitude());
                                    t.setEndLatitude(road.getEndLatitude());
                                    t.setCarNumber(1);
                                    t.setStartTime(new Timestamp(startTime * 1000));
                                    t.setEndTime(new Timestamp((startTime + step) * 1000));
                                    t.setCreateTime(new Timestamp(System.currentTimeMillis()));
                                    trainDataService.saveTrainData(t);
                                }else{
                                    trainData.setCarNumber(trainData.getCarNumber() + 1);
                                    trainDataService.updateTrainData(trainData);
                                }
                            }
                        }
                    }

                    startTime += step;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
