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

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

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

    /**
     * TrainData缓存
     */
    Map<Long, TrainData> trainDataMap = new HashMap<Long, TrainData>();

    List<Road> roads;

    /**
     * 并发统计每个路段车流量的情况
     *
     * @throws ParseException
     */
    @Test
    public void createTrainData() throws ParseException {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /**
         * 10分钟间隔秒数
         */
        long minuteStep = 600;

        /**
         * 1小时间隔秒数
         */
        long hourStep = 3600;

        /**
         * 开始时间
         */
        long startTime = sf.parse("2016-10-31 00:00:00").getTime() / 1000L;

        /**
         * 结束时间
         */
        long endTime = sf.parse("2016-11-01 00:00:00").getTime() / 1000L;

        /**
         * 咸宁西路的所有路段集合
         */
        roads = roadService.listRoadByName("咸宁西路");


        for (int x = 0; x < roads.size(); x++) {
            long start = startTime;
            long end = endTime;
            while (start < end) {
                TrainData trainData = new TrainData();
                trainData.setRoadId(roads.get(x).getRoadId());
                trainData.setStartTime(new Timestamp(start * 1000L));
                trainData.setEndTime(new Timestamp((start + minuteStep) * 1000L));
                trainData.setCarNumber(0);
                trainDataMap.put(start, trainData);
                start += minuteStep;
            }
        }

        Iterator<Map.Entry<Long, TrainData>> iterator = trainDataMap.entrySet().iterator();

        while (iterator.hasNext()) {
            CountDownLatch countDownLatch = new CountDownLatch(6);
            Map.Entry<Long, TrainData> entry = iterator.next();
            TrainData trainData = entry.getValue();
            new Thread(new countThread(trainData.getStartTime(), trainData.getEndTime(), countDownLatch)).start();
        }

    }

    class countThread implements Runnable {

        private Timestamp subStartTime;
        private Timestamp subEndTime;

        private CountDownLatch countDownLatch;

        public Timestamp getSubStartTime() {
            return subStartTime;
        }

        public void setSubStartTime(Timestamp subStartTime) {
            this.subStartTime = subStartTime;
        }

        public Timestamp getSubEndTime() {
            return subEndTime;
        }

        public void setSubEndTime(Timestamp subEndTime) {
            this.subEndTime = subEndTime;
        }

        public countThread(Timestamp subStartTime, Timestamp subEndTime, CountDownLatch countDownLatch) {
            this.subStartTime = subStartTime;
            this.subEndTime = subEndTime;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            Long start = subStartTime.getTime() / 1000L;
            Long end = subEndTime.getTime() / 1000L;

            /**
             * 获取这个路段特定的点
             */
            List<OriginData> originData = originDataService.listSpecifyTimeOriginData(start, end);

            for (int x = 0; x < originData.size(); x++) {
                OriginData o = originData.get(x);
                if (!PathQuery.query(o.getLongitude(), o.getLatitude(), roads).equals("None")) {
                    TrainData t = trainDataMap.get(start);
                    t.setCarNumber(t.getCarNumber() + 1);
                }
            }
            countDownLatch.countDown();
        }

    }

}
