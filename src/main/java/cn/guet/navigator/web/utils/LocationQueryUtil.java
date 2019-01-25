package cn.guet.navigator.web.utils;

import cn.guet.navigator.web.dto.Location;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class LocationQueryUtil implements Serializable {

    private static final long serialVersionUID = 1554420955208394761L;

    public static final String DRIVER_CAR_BASE_URL = "https://restapi.amap.com/v3/geocode/regeo?key=:key&location=:location&poitype=&radius=1000&extensions=all&batch=false&roadlevel=0";

    public static final String MY_KEY = "3cf2cf32d943b809c8281b6ee305576d";

    public static final String KEY = ":key";
    public static final String LOCATION = ":location";

    /**
     * 逆地理编码
     *
     * @param location
     * @return
     */
    public static String location(Location location) {
        String s = location.getLongitude() + "," + location.getLatitude();
        StringBuilder url = new StringBuilder(DRIVER_CAR_BASE_URL);
        url.replace(url.indexOf(LOCATION), url.indexOf(LOCATION) + LOCATION.length(), s);
        url.replace(url.indexOf(KEY), url.indexOf(KEY) + KEY.length(), MY_KEY);
        return RestTempleUtil.get(url.toString(), String.class, null);
    }


}
