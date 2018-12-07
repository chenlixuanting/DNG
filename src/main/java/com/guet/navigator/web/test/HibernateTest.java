package com.guet.navigator.web.test;

import com.guet.navigator.web.constant.common.CommonConstant;
import com.guet.navigator.web.pojo.Administrator;
import com.guet.navigator.web.pojo.Device;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by Administrator on 9/13/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {

    @Autowired
    SessionFactory sessionFactory;

    @Test
    public void insertAdministrator() {

        Administrator administrator = new Administrator();

        administrator.setAccount("2583744237@qq.com");
        administrator.setPassword("123456");
        administrator.setUsername("陈宣锦");
        administrator.setPosition("后台开发人员");
        administrator.setHeadPic(CommonConstant.LOCAL_NET_WEB+"/navigator"+CommonConstant.ADMIN_PROFILE_PIC+ UUID.randomUUID().toString()+".jpg");
        administrator.setCreateTime(new Timestamp(System.currentTimeMillis()));
        administrator.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(administrator);
        tx.commit();
    }

    @Test
    public void insertDevice(){
        Device d = new Device();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(d);
        tx.commit();
    }

    @Test
    public void deleteAdministrator(){

//        int id = 1;
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//        Administrator administrator = (Administrator) session.get(Administrator.class,1);
//        session.delete(administrator);
//        tx.commit();

    }

}
