package cn.guet.navigator.web.dao;

import cn.guet.navigator.web.pojo.backup.Road;

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
