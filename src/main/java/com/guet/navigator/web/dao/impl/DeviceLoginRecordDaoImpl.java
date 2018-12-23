package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.DeviceLoginRecordDao;
import com.guet.navigator.web.pojo.DeviceLoginRecord;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public class DeviceLoginRecordDaoImpl extends BaseDaoImpl<DeviceLoginRecord> implements DeviceLoginRecordDao {

    @Override
    public DeviceLoginRecord findByDeviceId(String deviceId) {

        DeviceLoginRecord deviceRecord = null;
        String hql = "from com.guet.navigator.web.pojo.DeviceLoginRecord as d where d.deviceId=:deviceId";

        try {
            Query query = getCurrentSession().createQuery(hql).setParameter("deviceId",deviceId);
            deviceRecord = (DeviceLoginRecord) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deviceRecord;

    }

    @Override
    public Boolean saveDeviceRecord(DeviceLoginRecord deviceRecord) {
        return save(deviceRecord);
    }

}
