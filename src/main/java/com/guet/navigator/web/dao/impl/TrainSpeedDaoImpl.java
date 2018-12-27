package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.TrainSpeedDao;
import com.guet.navigator.web.pojo.TrainSpeed;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Administrator
 */
@Repository
public class TrainSpeedDaoImpl extends BaseDaoImpl<TrainSpeed> implements TrainSpeedDao {

    @Override
    public List<TrainSpeed> listTrainSpeedByRoadId(String roadId) {
        String hql = "from com.guet.navigator.web.pojo.TrainSpeed as t where t.roadId=:roadId";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("roadId", roadId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> countAllDistinctRoadId() {
        String hql = "select distinct s.roadId from com.guet.navigator.web.pojo.TrainSpeed as s";
        try {
            Query query = getCurrentSession().createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TrainSpeed> listTrainSpeedBySpecifyTimeAndRoadId(Timestamp startTime, Timestamp endTime, String roadId) {
        String hql = "from com.guet.navigator.web.pojo.TrainSpeed as s where s.roadId=:roadId and s.startTime=:startTime and s.endTime=:endTime and s.speed!=0";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("roadId",roadId);
            query.setParameter("startTime",startTime);
            query.setParameter("endTime",endTime);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
