import cn.guet.navigator.web.utils.Location;
import cn.guet.navigator.web.utils.Navigation;
import cn.guet.navigator.web.utils.PathPlanUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class NavigationTest {

    @Test
    public void test() {

        Location start = new Location(110.419122, 25.313339);

        Location dest = new Location(110.336322, 25.283657);

        String str = PathPlanUtil.path(start, dest);

        Navigation navigation = JSONObject.parseObject(PathPlanUtil.path(start, dest), Navigation.class);

        return;
    }

}
