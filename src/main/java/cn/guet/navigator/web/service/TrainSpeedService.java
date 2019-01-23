package cn.guet.navigator.web.service;

import cn.guet.navigator.web.pojo.backup.TrainSpeed;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Administrator
 */
public interface TrainSpeedService {

    Boolean saveTrainSpeed(TrainSpeed trainSpeed);

    List<TrainSpeed> listTrainSpeedByRoadId(String roadId);

    List<String> countAllDistinctRoadId();

    Boolean updateTrainSpeed(TrainSpeed trainSpeed);

    List<TrainSpeed> listTrainSpeedBySpecifyTimeAndRoadId(Timestamp startTime, Timestamp endTime, String roadId);

}
