package com.guet.navigator.web.vo;

import java.io.Serializable;

public class DeviceLoginMessageVo implements Serializable{

    private static final long serialVersionUID = 4529485722464011069L;

    private Integer statusCode;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public DeviceLoginMessageVo() {
    }

    public DeviceLoginMessageVo(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
