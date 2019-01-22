package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.DeviceDao;
import com.guet.navigator.web.pojo.Device;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public class DeviceDaoImpl extends BaseDaoImpl<Device> implements DeviceDao {

    @Override
    public Device getByDeviceId(String deviceId) {
        String hql = "from com.guet.navigator.web.pojo.Device as d where d.deviceId=:deviceId";
        try {
            Query query = getCurrentSession().createQuery(hql).setParameter("deviceId", deviceId);
            return (Device) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Device> listAllDevice() {
        String hql = "from com.guet.navigator.web.pojo.Device";
        try {
            Query query = getCurrentSession().createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Device> listDeviceLimit(int start, int length) {
        String hql = "from com.guet.navigator.web.pojo.Device";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setFirstResult(start);
            query.setMaxResults(length);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delDeviceById(String deviceId) {
        return null;
    }

    @Override
    public Boolean delDevice(Device device) {
        return delete(device);
    }

}
