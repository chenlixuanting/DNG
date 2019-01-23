package com.guet.navigator.web.test;

import cn.guet.navigator.web.constant.common.CommonConstant;
import cn.guet.navigator.web.pojo.Administrator;
import cn.guet.navigator.web.pojo.Device;
import cn.guet.navigator.web.pojo.Photo;
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
 * @author Administrator
 * @date 9/13/2018
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
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Photo photo = new Photo();
        String prefix = UUID.randomUUID().toString();
        String suffix = "jpg";
        photo.setUrl(CommonConstant.LOCAL_NET_WEB + CommonConstant.ADMIN_PROFILE_PIC + prefix + "." + suffix);
        photo.setPrefix(prefix);
        photo.setSuffix(suffix);
        photo.setCreateTime(currentTime);
        photo.setUpdateTime(currentTime);
        administrator.setHeadPic(photo);
        administrator.setCreateTime(currentTime);
        administrator.setUpdateTime(currentTime);
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(administrator);
        tx.commit();
    }

    @Test
    public void insertDevice() {
        Device d = new Device();
        d.setDeviceId("ac9cfc4e-d28b-3f18-a9e1-4e62b8cf97d3");
        d.setCreateTime(new Timestamp(System.currentTimeMillis()));
        d.setDeviceName("dng");
        d.setDeviceVersion("1.0");
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

}
