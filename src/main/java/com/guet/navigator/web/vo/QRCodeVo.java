package com.guet.navigator.web.vo;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class QRCodeVo implements Serializable{

    private static final long serialVersionUID = 6871548502322643330L;

    private String qrCodeString;

    public String getQrCodeString() {
        return qrCodeString;
    }

    public void setQrCodeString(String qrCodeString) {
        this.qrCodeString = qrCodeString;
    }

    public QRCodeVo(String qrCodeString) {
        this.qrCodeString = qrCodeString;
    }
}
