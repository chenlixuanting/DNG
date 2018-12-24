package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.TestPositionDao;
import com.guet.navigator.web.pojo.TestPosition;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public class TestPositionDaoImpl extends BaseDaoImpl<TestPosition> implements TestPositionDao {

    @Override
    public List<TestPosition> listPosition() {

        String hql = "from com.guet.navigator.web.pojo.TestPosition";

        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setFirstResult(1);
            query.setMaxResults(10000);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<TestPosition> getPositionsById(String userId) {
        String hql = "from com.guet.navigator.web.pojo.TestPosition as o where o.userId=:userId order by o.createTime asc ";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("userId", userId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
