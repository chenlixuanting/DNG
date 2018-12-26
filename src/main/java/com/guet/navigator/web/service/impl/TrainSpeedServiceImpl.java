package com.guet.navigator.web.service.impl;

import com.guet.navigator.web.dao.TrainSpeedDao;
import com.guet.navigator.web.pojo.TrainSpeed;
import com.guet.navigator.web.service.TrainSpeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
