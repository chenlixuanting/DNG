package cn.guet.navigator.web.dao;

import cn.guet.navigator.web.pojo.backup.TrainData;

/**
 * @author Administrator
 */
public interface TrainDataDao {

    Boolean save(TrainData trainData);

    TrainData getTrainDataByRoadIdAndSpecifyTime(String roadId, long startTime, long endTime);

    Boolean update(TrainData trainData);

}
