package com.guet.navigator.web.test;

import com.alibaba.fastjson.JSON;
import cn.guet.navigator.web.pojo.backup.Road;
import cn.guet.navigator.web.pojo.backup.Line;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class InserRoadDataTest {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * 插入路段数据
     */
    @Test
    public void insertRoadData() {
        File file = new File("E:\\road.json");
        StringBuilder sb = new StringBuilder();
        String str;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            try {
                while ((str = bf.readLine()) != null) {
                    sb.append(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Line> list = JSON.parseArray(sb.toString(), Line.class);
        Session session = sessionFactory.openSession();
        for (int x = 0; x < list.size(); x++) {
            Line line = list.get(x);
            Road road = new Road();
            road.setRoadName(line.getName());
            road.setStartLongitude(line.getStart().getLongitude());
            road.setStartLatitude(line.getStart().getLatitude());
            road.setEndLongitude(line.getEnd().getLongitude());
            road.setEndLatitude(line.getEnd().getLatitude());
            road.setCreateTime(new Timestamp(System.currentTimeMillis()));
            Transaction tx = session.beginTransaction();
            session.save(road);
            tx.commit();
        }
        System.out.println(sb.toString());
    }

}
