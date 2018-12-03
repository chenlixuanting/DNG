package com.guet.navigator.web.pojo;

import java.io.Serializable;

/**
 * 设备
 *
 */
public class Device implements Serializable{

    private static final long serialVersionUID = 6729556043534645649L;

    private String deviceId;    //主键
    private String hdId;        //设备Id

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getHdId() {
        return hdId;
    }

    public void setHdId(String hdId) {
        this.hdId = hdId;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId='" + deviceId + '\'' +
                ", hdId='" + hdId + '\'' +
                '}';
    }

    public Device() {
    }

}
