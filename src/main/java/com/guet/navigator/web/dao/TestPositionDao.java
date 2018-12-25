package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.backup.TestPosition;

import java.util.List;

/**
 * @author Administrator
 */
public interface TestPositionDao {

    List<TestPosition> listPosition();

    List<TestPosition> getPositionsById(String userId);

}
