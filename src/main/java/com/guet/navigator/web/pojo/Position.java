package com.guet.navigator.web.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 设备实时坐标
 *
 * @author Administrator
 */
public class Position implements Serializable {

    private static final long serialVersionUID = -7064651417990704311L;

    /**
     * 主键
     */
    private String positionId;

    /**
     * 设备Id
     */
    private Device device;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 当前路况
     */
    private Boolean roadState;

    /**
     * 创建时间
     */
    private Timestamp currentTime;

    public Boolean getRoadState() {
        return roadState;
    }

    public void setRoadState(Boolean roadState) {
        this.roadState = roadState;
    }

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Position() {
    }
}
