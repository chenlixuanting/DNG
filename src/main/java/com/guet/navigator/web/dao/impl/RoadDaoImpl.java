package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.RoadDao;
import com.guet.navigator.web.pojo.Road;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public class RoadDaoImpl extends BaseDaoImpl<Road> implements RoadDao {

    @Override
    public List<Road> listAllRoad() {

        String hql = "from com.guet.navigator.web.pojo.Road";

        try {
            Query query = getCurrentSession().createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
