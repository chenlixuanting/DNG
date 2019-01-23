package cn.guet.navigator.web.dao.impl;

import cn.guet.navigator.web.dao.LoginRecordDao;
import cn.guet.navigator.web.pojo.LoginRecord;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public class LoginRecordDaoImpl extends BaseDaoImpl<LoginRecord> implements LoginRecordDao {

    @Override
    public LoginRecord getByDeviceId(String deviceId) {
        String hql = "from cn.guet.navigator.web.pojo.LoginRecord as d where d.deviceId=:deviceId";
        try {
            Query query = getCurrentSession().createQuery(hql).setParameter("deviceId", deviceId);
            return (LoginRecord) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean saveDeviceRecord(LoginRecord deviceRecord) {
        return save(deviceRecord);
    }

    @Override
    public LoginRecord getByUserId(String userId) {
        String hql = "from cn.guet.navigator.web.pojo.LoginRecord as d where d.userId=:userId";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("userId", userId);
            return (LoginRecord) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
