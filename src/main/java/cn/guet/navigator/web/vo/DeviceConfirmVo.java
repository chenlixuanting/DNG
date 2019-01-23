package cn.guet.navigator.web.vo;

import java.io.Serializable;

public class DeviceConfirmVo implements Serializable{

    private static final long serialVersionUID = -5948175270445371417L;

    private String qrCodeStr;
    private Boolean loginFlag;

    public String getQrCodeStr() {
        return qrCodeStr;
    }

    public void setQrCodeStr(String qrCodeStr) {
        this.qrCodeStr = qrCodeStr;
    }

    public Boolean getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public DeviceConfirmVo() {
    }
}
