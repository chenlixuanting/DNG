package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.DeviceDao;
import com.guet.navigator.web.pojo.Device;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceDaoImpl extends BaseDaoImpl<Device> implements DeviceDao{

    @Override
    public Device findByDeviceId(String deviceId) {

        Device device = null;
        String hql = "from com.guet.navigator.web.pojo.Device as d where d.deviceId=:deviceId";

        try {
            Query query = getCurrentSession().createQuery(hql).setParameter("deviceId",deviceId);
            device = (Device) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return device;
    }

}
