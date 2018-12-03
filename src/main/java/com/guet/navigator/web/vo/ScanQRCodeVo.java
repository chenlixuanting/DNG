package com.guet.navigator.web.vo;

import java.io.Serializable;

public class ScanQRCodeVo implements Serializable{

    private String qrCodeStr;
    private Boolean scanFlag;

    public String getQrCodeStr() {
        return qrCodeStr;
    }

    public void setQrCodeStr(String qrCodeStr) {
        this.qrCodeStr = qrCodeStr;
    }

    public Boolean getScanFlag() {
        return scanFlag;
    }

    public void setScanFlag(Boolean scanFlag) {
        this.scanFlag = scanFlag;
    }

    public ScanQRCodeVo() {
    }
}
