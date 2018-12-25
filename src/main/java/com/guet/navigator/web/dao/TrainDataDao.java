package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.TrainData;

/**
 * @author Administrator
 */
public interface TrainDataDao {

    Boolean save(TrainData trainData);

    TrainData getTrainDataByRoadIdAndSpecifyTime(String roadId, long startTime, long endTime);

    Boolean update(TrainData trainData);

}
