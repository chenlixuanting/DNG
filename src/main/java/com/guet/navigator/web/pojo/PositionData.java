package com.guet.navigator.web.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class PositionData implements Serializable{

    private static final long serialVersionUID = -7064651417990704311L;

    private String positionDataId;  //主键

    private Device device;  //外键

    private Double longitude;   //经度

    private Double latitude;    //纬度

    private Timestamp createTime;   //当前时间

    public String getPositionDataId() {
        return positionDataId;
    }

    public void setPositionDataId(String positionDataId) {
        this.positionDataId = positionDataId;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public PositionData() {
    }
}
