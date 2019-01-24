package cn.guet.navigator.web.utils;

import cn.guet.navigator.web.dto.Location;

/**
 * 路线规划工具类
 *
 * @author Administrator
 */
public class PathPlanUtil {

    public static final String DRIVER_CAR_BASE_URL = "https://restapi.amap.com/v3/direction/driving?origin=:origin&strategy=:strategy&destination=:destination&extensions=:extensions&key=:key";

    public static final String MY_KEY = "3cf2cf32d943b809c8281b6ee305576d";

    public static final String ORIGIN = ":origin";
    public static final String DESTINATION = ":destination";
    public static final String EXTENSIONS = ":extensions";
    public static final String KEY = ":key";
    public static final String STRATEGY = ":strategy";

    public static final String STRATEGY_SPEED_FIRST = "0";
    public static final String STRATEGY_DISTANCE_FIRST = "2";
    public static final String STRATEGY_CONGESTION_FIRST = "4";

    /**
     * 路径查询api
     *
     * @param start
     * @param end
     * @return
     */
    public static String path(Location start, Location end, String strategy) {
        String s = start.getLongitude() + "," + start.getLatitude();
        String e = end.getLongitude() + "," + end.getLatitude();
        StringBuilder url = new StringBuilder(DRIVER_CAR_BASE_URL);
        url.replace(url.indexOf(ORIGIN), url.indexOf(ORIGIN) + ORIGIN.length(), s);
        url.replace(url.indexOf(DESTINATION), url.indexOf(DESTINATION) + DESTINATION.length(), e);
        url.replace(url.indexOf(EXTENSIONS), url.indexOf(EXTENSIONS) + EXTENSIONS.length(), "all");
        url.replace(url.indexOf(STRATEGY), url.indexOf(STRATEGY) + STRATEGY.length(), strategy);
        url.replace(url.indexOf(KEY), url.indexOf(KEY) + KEY.length(), MY_KEY);
        return RestTempleUtil.get(url.toString(), String.class, null);
    }

}
