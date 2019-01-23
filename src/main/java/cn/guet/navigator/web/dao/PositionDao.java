package cn.guet.navigator.web.dao;

import cn.guet.navigator.web.pojo.Position;

/**
 * @author Administrator
 */
public interface PositionDao {

    /**
     * @param positionData
     * @return
     */
    Boolean save(Position positionData);

    /**
     * 根据deviceId获取一条最新的位置记录
     *
     * @param deviceId
     * @return
     */
    Position getLatestPositionByDeviceId(String deviceId);
}
