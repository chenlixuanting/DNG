package cn.guet.navigator.web.service.impl;

import cn.guet.navigator.web.dao.OriginDataDao;
import cn.guet.navigator.web.service.OriginDataService;
import cn.guet.navigator.web.pojo.backup.OriginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class OriginDataServiceImpl implements OriginDataService {

    @Autowired
    private OriginDataDao originDataDao;

    @Override
    public List<OriginData> listAllOriginData() {
        return originDataDao.listAllOriginData();
    }

    @Override
    public List<OriginData> listSpecifyTimeOriginData(long startTime, long endTime) {
        return originDataDao.listSpecifyTimeOriginData(startTime, endTime);
    }

    @Override
    public List<OriginData> listPartOriginData(long number) {
        return originDataDao.listPartOriginData(number);
    }

    @Override
    public List<String> listOriginDataByDistinctDeviceId() {
        return originDataDao.listOriginDataByDistinctDeviceId();
    }

    @Override
    public List<OriginData> listOriginDataSpecifyDeviceId(String deviceId) {
        return originDataDao.listOriginDataSpecifyDeviceId(deviceId);
    }

}
