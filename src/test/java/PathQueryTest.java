package com.guet.navigator.web.test;

import cn.guet.navigator.web.pojo.backup.OriginData;
import cn.guet.navigator.web.pojo.backup.Road;
import cn.guet.navigator.web.python.PathQuery;
import cn.guet.navigator.web.service.OriginDataService;
import cn.guet.navigator.web.service.RoadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PathQueryTest {

    @Autowired
    private RoadService roadService;

    @Autowired
    private OriginDataService originDataService;

    /**
     * 调用py归路算法
     */
    @Test
    public void queryPath() {
        List<Road> roadList = roadService.listAllRoad();
        List<OriginData> originDataList = originDataService.listPartOriginData(50000);
        System.out.println(new Date(1477908705 * 1000L).toString());
        for (int x = 0; x < originDataList.size(); x++) {
            OriginData originData = originDataList.get(x);
            System.out.println(originData.getDeviceId() + ":::" + originData.getPositionId() + ":::" + PathQuery.query(originData.getLongitude(), originData.getLatitude(), roadList));
        }
    }

}
