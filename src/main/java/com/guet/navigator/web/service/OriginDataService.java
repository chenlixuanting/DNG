package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.OriginData;

import java.util.List;

/**
 * @author Administrator
 */
public interface OriginDataService {

    List<OriginData> listAllOriginData();

    List<OriginData> listSpecifyTimeOriginData(long startTime, long endTime);

    List<OriginData> listPartOriginData(long number);

    List<String> listOriginDataByDistinctDeviceId();

    List<OriginData> listOriginDataSpecifyDeviceId(String deviceId);

}
