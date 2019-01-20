package com.guet.navigator.web.pojo.backup;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Administrator
 */
public class TrainSpeed implements Serializable {

    private static final long serialVersionUID = 2607679051613379110L;

    private String speedId;
    private String roadId;
    private String deviceId;

    private Double longitude;
    private Double latitude;

    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp presentTime;

    private Integer currentTime;

    private Double speed;


    @Override
    public String toString() {
        return roadId + "," + deviceId + "," + longitude + "," + latitude + "," + currentTime;
    }

    public Integer getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Integer currentTime) {
        this.currentTime = currentTime;
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

    public String getSpeedId() {
        return speedId;
    }

    public void setSpeedId(String speedId) {
        this.speedId = speedId;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getPresentTime() {
        return presentTime;
    }

    public void setPresentTime(Timestamp presentTime) {
        this.presentTime = presentTime;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public TrainSpeed() {
    }
}
