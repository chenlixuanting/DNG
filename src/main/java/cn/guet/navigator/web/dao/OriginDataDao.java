package cn.guet.navigator.web.dao;

import cn.guet.navigator.web.pojo.backup.OriginData;

import java.util.List;

/**
 * @author Administrator
 */
public interface OriginDataDao {

    List<OriginData> listAllOriginData();

    List<OriginData> listSpecifyTimeOriginData(long startTime, long endTime);

    List<OriginData> listPartOriginData(long number);

    List<String> listOriginDataByDistinctDeviceId();

    List<OriginData> listOriginDataSpecifyDeviceId(String deviceId);

}
