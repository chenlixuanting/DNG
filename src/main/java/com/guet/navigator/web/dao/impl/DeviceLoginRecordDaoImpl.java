package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.DeviceLoginRecordDao;
import com.guet.navigator.web.pojo.LoginRecord;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public class DeviceLoginRecordDaoImpl extends BaseDaoImpl<LoginRecord> implements DeviceLoginRecordDao {

    @Override
    public LoginRecord findByDeviceId(String deviceId) {

        LoginRecord deviceRecord = null;
        String hql = "from com.guet.navigator.web.pojo.LoginRecord as d where d.deviceId=:deviceId";

        try {
            Query query = getCurrentSession().createQuery(hql).setParameter("deviceId", deviceId);
            deviceRecord = (LoginRecord) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deviceRecord;

    }

    @Override
    public Boolean saveDeviceRecord(LoginRecord deviceRecord) {
        return save(deviceRecord);
    }

}
