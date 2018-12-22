package com.guet.navigator.web.pojo;

import java.io.Serializable;

/**
 * 设备
 */
public class Device implements Serializable{

    private static final long serialVersionUID = 6729556043534645649L;

    /**
     * 主键
     */
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Device(String deviceId) {
        this.deviceId = deviceId;
    }

    public Device() {
    }

}
