package cn.guet.navigator.web.dto;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 7459931330174346953L;

    private Integer status;
    private String info;
    private Integer infocode;

    private Regeocode regeocode;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getInfocode() {
        return infocode;
    }

    public void setInfocode(Integer infocode) {
        this.infocode = infocode;
    }

    public Regeocode getRegeocode() {
        return regeocode;
    }

    public void setRegeocode(Regeocode regeocode) {
        this.regeocode = regeocode;
    }

    public Address() {
    }
}
