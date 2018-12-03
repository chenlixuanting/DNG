package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.DeviceRecordDao;
import com.guet.navigator.web.pojo.DeviceRecord;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceRecordDaoImpl extends BaseDaoImpl<DeviceRecord> implements DeviceRecordDao{

    @Override
    public DeviceRecord findByDeviceId(String deviceId) {

        String hql = "from com.guet.navigator.web.pojo.DeviceRecord as d where d.deviceId=:deviceId";

        DeviceRecord deviceRecord = null;

        try {

            Query query = getCurrentSession().createQuery(hql).setParameter("deviceId",deviceId);

            List<DeviceRecord> deviceRecordList = query.list();

            if(!deviceRecordList.isEmpty()){
                deviceRecord = deviceRecordList.get(0);
            }

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
