package cn.guet.navigator.web.service.impl;

import cn.guet.navigator.web.dao.TestPositionDao;
import cn.guet.navigator.web.service.TestPositionService;
import cn.guet.navigator.web.pojo.backup.TestPosition;
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
