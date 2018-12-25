package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.TrainDataDao;
import com.guet.navigator.web.pojo.TrainData;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author Administrator
 */
@Repository
public class TrainDataDaoImpl extends BaseDaoImpl<TrainData> implements TrainDataDao {

    @Override
    public TrainData getTrainDataByRoadIdAndSpecifyTime(String roadId, long startTime, long endTime) {

        String hql = "from com.guet.navigator.web.pojo.TrainData as t where t.roadId=:roadId and t.startTime=:startTime and t.endTime=:endTime";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("roadId", roadId);
            query.setParameter("startTime", new Timestamp(startTime));
            query.setParameter("endTime", new Timestamp(endTime));
            return (TrainData) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
