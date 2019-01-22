package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.Position;

/**
 * @author Administrator
 */
public interface PositionService {

    /**
     * 保存一条新的位置记录
     *
     * @param position
     * @return
     */
    Boolean save(Position position);

    /**
     * 根据deviceId获取一条最新的位置记录
     *
     * @param deviceId
     * @return
     */
    Position getLatestPositionByDeviceId(String deviceId);

}
