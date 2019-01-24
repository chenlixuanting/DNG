import cn.guet.navigator.web.dto.Location;
import cn.guet.navigator.web.dto.Navigation;
import cn.guet.navigator.web.utils.PathPlanUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NavigationTest {

    @Test
    public void test() {

        List<Location> destinations = new ArrayList<Location>();
        List<Navigation> speed = new ArrayList<Navigation>();
        List<Navigation> distance = new ArrayList<Navigation>();
        List<Navigation> congestion = new ArrayList<Navigation>();

        /**
         * 桂林电子科技大学金鸡岭校区
         */
        Location current = new Location(110.337704, 25.284115);
        /**
         * 桂林十字街
         */
        destinations.add(new Location(110.295334, 25.277452));
        /**
         * 桂林师范大学育才校区
         */
        destinations.add(new Location(110.3249, 25.266355));
        /**
         * 桂林理工大学
         */
        destinations.add(new Location(108.298643, 22.856469));
        /**
         * 广西大学
         */
        destinations.add(new Location(108.290815, 22.844167));

        Iterator<Location> locationIterator = destinations.iterator();

        while (locationIterator.hasNext()) {
            Location destination = locationIterator.next();
            speed.add(JSONObject.parseObject(PathPlanUtil.path(current, destination, PathPlanUtil.STRATEGY_SPEED_FIRST), Navigation.class));
            distance.add(JSONObject.parseObject(PathPlanUtil.path(current, destination, PathPlanUtil.STRATEGY_DISTANCE_FIRST), Navigation.class));
            congestion.add(JSONObject.parseObject(PathPlanUtil.path(current, destination, PathPlanUtil.STRATEGY_CONGESTION_FIRST), Navigation.class));
        }



        return;
    }

}
