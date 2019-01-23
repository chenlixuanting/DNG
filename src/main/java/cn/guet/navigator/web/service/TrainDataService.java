package cn.guet.navigator.web.service;

import cn.guet.navigator.web.pojo.backup.TrainData;

/**
 * @author Administrator
 */
public interface TrainDataService {

    Boolean saveTrainData(TrainData trainData);

    TrainData getTrainDataByRoadIdAndSpecifyTime(String roadId, long startTime, long endTime);

    Boolean updateTrainData(TrainData trainData);

}
