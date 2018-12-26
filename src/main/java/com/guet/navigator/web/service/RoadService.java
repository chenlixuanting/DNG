package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.Road;

import java.util.List;

/**
 * @author Administrator
 */
public interface RoadService {

    Boolean saveRoad(Road road);

    List<Road> listAllRoad();

    List<Road> listRoadByName(String roadName);

    public Road getRoadByRoadId(String roadId);

}
