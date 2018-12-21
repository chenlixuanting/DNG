package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.BaseDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;

/**
 *
 * @author Administrator
 * @date 9/13/2018
 */
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class<T> tClass;

    /**
     * 此处有坑
     *
     * @param sessionFactory
     */
    @Autowired
    public void setMysessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    /**
     * 获取泛型的具体类型
     */
    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.tClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    /**
     * 获取session
     */
    public Session getCurrentSession() throws Exception {

        Session session = null;

        try {
            session = getSessionFactory().getCurrentSession();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return session;

    }


    @Override
    public Boolean save(T entity) {

        Boolean flag = false;

        try {
            getHibernateTemplate().save(entity);
            flag = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public Boolean delete(T entity)
    {
        Boolean flag = false;

        try {
            getHibernateTemplate().delete(entity);
            flag = true;
        }catch (HibernateException e){
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public T findById(String id) {
        return getHibernateTemplate().load(tClass, id);
    }

    @Override
    public Boolean update(T entity) {

        Boolean flag = false;

        try {
            getHibernateTemplate().update(entity);
            flag = true;
        }catch (HibernateException e){
            e.printStackTrace();
        }

        return flag;
    }

}
