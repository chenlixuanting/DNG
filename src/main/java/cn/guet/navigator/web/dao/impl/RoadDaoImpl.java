package cn.guet.navigator.web.dao.impl;

import cn.guet.navigator.web.pojo.backup.Road;
import cn.guet.navigator.web.dao.RoadDao;
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

        String hql = "from cn.guet.navigator.web.pojo.Road";

        try {
            Query query = getCurrentSession().createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Road> listRoadByName(String roadName) {
        String hql = "from cn.guet.navigator.web.pojo.Road as r where r.roadName=:roadName";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("roadName", roadName);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Road getRoadByRoadId(String roadId) {
        String hql = "from cn.guet.navigator.web.pojo.Road as r where r.roadId=:roadId";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("roadId", roadId);
            return (Road) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
