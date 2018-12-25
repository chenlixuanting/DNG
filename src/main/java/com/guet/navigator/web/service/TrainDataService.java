package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.TrainData;

/**
 * @author Administrator
 */
public interface TrainDataService {

    Boolean saveTrainData(TrainData trainData);

    TrainData getTrainDataByRoadIdAndSpecifyTime(String roadId, long startTime, long endTime);

    Boolean updateTrainData(TrainData trainData);

}
