package cn.guet.navigator.web.service.impl;

import cn.guet.navigator.web.dao.RoadDao;
import cn.guet.navigator.web.pojo.backup.Road;
import cn.guet.navigator.web.service.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class RoadServiceImpl implements RoadService {

    @Autowired
    private RoadDao roadDao;

    @Override
    public Boolean saveRoad(Road road) {
        return roadDao.save(road);
    }

    @Override
    public List<Road> listAllRoad() {
        return roadDao.listAllRoad();
    }

    @Override
    public List<Road> listRoadByName(String roadName) {
        return roadDao.listRoadByName(roadName);
    }

    @Override
    public Road getRoadByRoadId(String roadId) {
        return roadDao.getRoadByRoadId(roadId);
    }

}
