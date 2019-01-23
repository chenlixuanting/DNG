package cn.guet.navigator.web.vo;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class ScanQRCodeVo implements Serializable {

    private static final long serialVersionUID = 7480578782838674503L;
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
