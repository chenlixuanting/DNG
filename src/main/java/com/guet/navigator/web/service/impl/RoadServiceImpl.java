package com.guet.navigator.web.service.impl;

import com.guet.navigator.web.dao.RoadDao;
import com.guet.navigator.web.pojo.Road;
import com.guet.navigator.web.service.RoadService;
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

}
