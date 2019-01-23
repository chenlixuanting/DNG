package cn.guet.navigator.web.dao.impl;

import cn.guet.navigator.web.dao.PositionDao;
import cn.guet.navigator.web.pojo.Position;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public class PositionDaoImpl extends BaseDaoImpl<Position> implements PositionDao {

    @Override
    public Position getLatestPositionByDeviceId(String deviceId) {
        String hql = "from cn.guet.navigator.web.pojo.Position as p order by p.createTime desc";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setFirstResult(0);
            query.setMaxResults(1);
            return (Position) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
