package cn.guet.navigator.web.dto;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class Regeocode implements Serializable {

    private static final long serialVersionUID = -3325494871711568343L;

    private String formatted_address;

    private AddressComponent addressComponent;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    public Regeocode() {
    }
}
