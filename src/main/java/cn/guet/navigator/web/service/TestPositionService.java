package cn.guet.navigator.web.service;

import cn.guet.navigator.web.pojo.backup.TestPosition;

import java.util.List;

/**
 * @author Administrator
 */
public interface TestPositionService {

    List<TestPosition> listPosition();

    List<TestPosition> getPositionsById(String userId);
}
