package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.Position;

/**
 * @author Administrator
 */
public interface PositionDao {

    /**
     * @param positionData
     * @return
     */
    Boolean save(Position positionData);
}
