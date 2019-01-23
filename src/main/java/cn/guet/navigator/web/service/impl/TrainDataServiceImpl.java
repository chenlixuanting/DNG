package cn.guet.navigator.web.service.impl;

import cn.guet.navigator.web.dao.TrainDataDao;
import cn.guet.navigator.web.pojo.backup.TrainData;
import cn.guet.navigator.web.service.TrainDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class TrainDataServiceImpl implements TrainDataService {

    @Autowired
    private TrainDataDao trainDataDao;

    @Override
    public Boolean saveTrainData(TrainData trainData) {
        return trainDataDao.save(trainData);
    }

    @Override
    public TrainData getTrainDataByRoadIdAndSpecifyTime(String roadId, long startTime, long endTime) {
        return trainDataDao.getTrainDataByRoadIdAndSpecifyTime(roadId, startTime, endTime);
    }

    @Override
    public Boolean updateTrainData(TrainData trainData) {
        return trainDataDao.update(trainData);
    }

}
