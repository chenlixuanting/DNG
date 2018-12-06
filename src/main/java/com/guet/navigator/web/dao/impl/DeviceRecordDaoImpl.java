package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.DeviceRecordDao;
import com.guet.navigator.web.pojo.DeviceRecord;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceRecordDaoImpl extends BaseDaoImpl<DeviceRecord> implements DeviceRecordDao{

    @Override
    public DeviceRecord findByDeviceId(String deviceId) {

        DeviceRecord deviceRecord = null;
        String hql = "from com.guet.navigator.web.pojo.DeviceRecord as d where d.deviceId=:deviceId";

        try {
            Query query = getCurrentSession().createQuery(hql).setParameter("deviceId",deviceId);
            deviceRecord = (DeviceRecord) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deviceRecord;

    }

    @Override
    public Boolean saveDeviceRecord(DeviceRecord deviceRecord) {
        return save(deviceRecord);
    }

}
