package cn.guet.navigator.web.dto;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class AddressComponent implements Serializable{

    private static final long serialVersionUID = -44487790508776037L;

    private String country;

    private String province;

    private String city;

    private String district;

    private String township;

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public AddressComponent() {
    }
}
