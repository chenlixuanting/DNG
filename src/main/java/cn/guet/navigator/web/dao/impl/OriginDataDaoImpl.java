package cn.guet.navigator.web.dao.impl;

import cn.guet.navigator.web.dao.OriginDataDao;
import cn.guet.navigator.web.pojo.backup.OriginData;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public class OriginDataDaoImpl extends BaseDaoImpl<OriginData> implements OriginDataDao {

    @Override
    public List<OriginData> listAllOriginData() {
        String hql = "from cn.guet.navigator.web.pojo.OriginData";
        try {
            Query query = getCurrentSession().createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OriginData> listSpecifyTimeOriginData(long startTime, long endTime) {
//        select distinct sage,ssex,sname from Student where sage between 20 and 22
        String hql = "from cn.guet.navigator.web.pojo.OriginData as o where o.currentTime between :startTime and :endTime";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OriginData> listPartOriginData(long number) {
        String hql = "from cn.guet.navigator.web.pojo.OriginData";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setFirstResult(1);
            query.setMaxResults((int) number);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> listOriginDataByDistinctDeviceId() {
        String hql = "select distinct(o.deviceId) from cn.guet.navigator.web.pojo.OriginData as o";
        try {
            Query query = getCurrentSession().createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OriginData> listOriginDataSpecifyDeviceId(String deviceId) {

        String hql = "from cn.guet.navigator.web.pojo.OriginData as o where o.deviceId=:deviceId";

        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("deviceId", deviceId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
