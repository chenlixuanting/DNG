package cn.guet.navigator.web.service.impl;

import cn.guet.navigator.web.dao.TrainSpeedDao;
import cn.guet.navigator.web.service.TrainSpeedService;
import cn.guet.navigator.web.pojo.backup.TrainSpeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class TrainSpeedServiceImpl implements TrainSpeedService {

    @Autowired
    private TrainSpeedDao trainSpeedDao;

    @Override
    public Boolean saveTrainSpeed(TrainSpeed trainSpeed) {
        return trainSpeedDao.save(trainSpeed);
    }

    @Override
    public List<TrainSpeed> listTrainSpeedByRoadId(String roadId) {
        return trainSpeedDao.listTrainSpeedByRoadId(roadId);
    }

    @Override
    public List<String> countAllDistinctRoadId() {
        return trainSpeedDao.countAllDistinctRoadId();
    }

    @Override
    public Boolean updateTrainSpeed(TrainSpeed trainSpeed) {
        return trainSpeedDao.update(trainSpeed);
    }

    @Override
    public List<TrainSpeed> listTrainSpeedBySpecifyTimeAndRoadId(Timestamp startTime, Timestamp endTime, String roadId) {
        return trainSpeedDao.listTrainSpeedBySpecifyTimeAndRoadId(startTime,endTime,roadId);
    }

}
