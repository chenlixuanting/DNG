package cn.guet.navigator.web.utils;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class District implements Serializable {
    private static final long serialVersionUID = 821447172127632224L;

    private String name;
    private String adcode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public District() {
    }
}
