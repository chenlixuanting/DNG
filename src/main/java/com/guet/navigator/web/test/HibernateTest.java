package com.guet.navigator.web.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.guet.navigator.web.constant.common.CommonConstant;
import com.guet.navigator.web.pojo.*;
import com.guet.navigator.web.python.PathQuery;
import com.guet.navigator.web.service.RoadService;
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
import java.util.UUID;

/**
 * Created by Administrator on 9/13/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RoadService roadService;

    @Test
    public void insertAdministrator() {

        Administrator administrator = new Administrator();

        administrator.setAccount("2583744237@qq.com");
        administrator.setPassword("123456");
        administrator.setUsername("陈宣锦");
        administrator.setPosition("后台开发人员");
        administrator.setHeadPic(CommonConstant.LOCAL_NET_WEB + "/navigator" + CommonConstant.ADMIN_PROFILE_PIC + UUID.randomUUID().toString() + ".jpg");
        administrator.setCreateTime(new Timestamp(System.currentTimeMillis()));
        administrator.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(administrator);
        tx.commit();
    }

    @Test
    public void insertDevice() {
        Device d = new Device();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(d);
        tx.commit();
    }

    @Test
    public void deleteAdministrator() {

//        int id = 1;
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//        Administrator administrator = (Administrator) session.get(Administrator.class,1);
//        session.delete(administrator);
//        tx.commit();

    }

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

    @Test
    public void queryPath() {
        List<Road> roadList = roadService.listAllRoad();
        for (int x = 0; x < roadList.size(); x++) {
            System.out.println(PathQuery.query(roadList.get(x).getStartLongitude(), roadList.get(x).getStartLatitude(), roadList));
        }
    }

}
