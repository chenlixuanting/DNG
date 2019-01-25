package cn.guet.navigator.web.utils;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class GetStaticMapUtil implements Serializable {

    private static final long serialVersionUID = 817678419958899086L;

    public static final String BASE_URL = "https://restapi.amap.com/v3/staticmap?zoom=14&size=1024*1024&paths=10,0x008000,0.6,,:&points&key=:key";

}
