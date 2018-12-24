package com.guet.navigator.web.service.impl;

import com.guet.navigator.web.dao.TestPositionDao;
import com.guet.navigator.web.pojo.TestPosition;
import com.guet.navigator.web.service.TestPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class TestPositionServiceImpl implements TestPositionService {

    @Autowired
    private TestPositionDao testPositionDao;

    @Override
    public List<TestPosition> listPosition() {
        return testPositionDao.listPosition();
    }

    @Override
    public List<TestPosition> getPositionsById(String userId) {
        return testPositionDao.getPositionsById(userId);
    }
}
