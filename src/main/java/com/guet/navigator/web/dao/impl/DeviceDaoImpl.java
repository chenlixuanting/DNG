package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.constant.DeviceConstant;
import com.guet.navigator.web.dao.DeviceDao;
import com.guet.navigator.web.pojo.Device;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceDaoImpl extends BaseDaoImpl<Device> implements DeviceDao{

    @Override
    public Device findByDeviceId(String deviceId) {

        String hql = "from com.guet.navigator.web.pojo.Device as d where d.deviceId=:deviceId";

        Device device = null;

        try {
            Query query = getCurrentSession().createQuery(hql).setParameter("deviceId",deviceId);
            List<Device> deviceList = query.list();
            if(!deviceList.isEmpty()){
                device = deviceList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return device;
    }

}
