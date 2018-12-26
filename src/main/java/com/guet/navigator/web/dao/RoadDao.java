package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.Road;

import java.util.List;

/**
 * @author Administrator
 */
public interface RoadDao {

    Boolean save(Road road);

    List<Road> listAllRoad();

    List<Road> listRoadByName(String roadName);

    Road getRoadByRoadId(String roadId);

}
