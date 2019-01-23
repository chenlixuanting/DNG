package cn.guet.navigator.web.dao;

import cn.guet.navigator.web.pojo.backup.TrainSpeed;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Administrator
 */
public interface TrainSpeedDao {

    Boolean save(TrainSpeed trainSpeed);

    List listTrainSpeedByRoadId(String roadId);

    List<String> countAllDistinctRoadId();

    Boolean update(TrainSpeed trainSpeed);

    List<TrainSpeed> listTrainSpeedBySpecifyTimeAndRoadId(Timestamp startTime,Timestamp endTime,String roadId);

}
