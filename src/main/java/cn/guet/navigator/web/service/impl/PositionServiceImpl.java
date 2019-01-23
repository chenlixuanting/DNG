package cn.guet.navigator.web.service.impl;

import cn.guet.navigator.web.dao.PositionDao;
import cn.guet.navigator.web.pojo.Position;
import cn.guet.navigator.web.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;

    @Override
    public Boolean save(Position position) {
        return positionDao.save(position);
    }

    @Override
    public Position getLatestPositionByDeviceId(String deviceId) {
        return positionDao.getLatestPositionByDeviceId(deviceId);
    }

}
