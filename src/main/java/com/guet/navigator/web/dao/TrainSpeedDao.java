package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.TrainSpeed;

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