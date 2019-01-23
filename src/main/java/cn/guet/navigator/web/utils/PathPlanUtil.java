package cn.guet.navigator.web.utils;

import org.junit.Test;

/**
 * 路线规划工具类
 *
 * @author Administrator
 */
public class PathPlanUtil {

    public static final String DRIVER_CAR_BASE_URL = "https://restapi.amap.com/v3/direction/driving?origin=:origin&destination=:destination&extensions=:extensions&output=:output&key=:key";

    public static final String MY_KEY = "3cf2cf32d943b809c8281b6ee305576d";

    public static final String ORIGIN = ":origin";
    public static final String DESTINATOIN = ":destination";
    public static final String EXTENSIONS = ":extensions";
    public static final String OUTPUT = ":output";
    public static final String KEY = ":key";

    /**
     * 路径查询api
     *
     * @param start
     * @param end
     * @return
     */
    public static String path(Location start, Location end) {

        String s = start.getLongitude() + "," + start.getLatitude();
        String e = end.getLongitude() + "," + end.getLatitude();

        StringBuilder url = new StringBuilder(DRIVER_CAR_BASE_URL);

        url.replace(url.indexOf(ORIGIN), url.indexOf(ORIGIN) + ORIGIN.length(), s);
        url.replace(url.indexOf(DESTINATOIN), url.indexOf(DESTINATOIN) + DESTINATOIN.length(), e);
        url.replace(url.indexOf(EXTENSIONS), url.indexOf(EXTENSIONS) + EXTENSIONS.length(), "all");
        url.replace(url.indexOf(OUTPUT), url.indexOf(OUTPUT) + OUTPUT.length(), "json");
        url.replace(url.indexOf(KEY), url.indexOf(KEY) + KEY.length(), MY_KEY);

        System.out.println(RestTempleUtil.get(url.toString(), Object.class, null));

        return "23";
    }

    @Test
    public void test() {
        PathPlanUtil.path(new Location(108.745658, 21.915935), new Location(108.762567, 21.931542));
    }

}
