import cn.guet.navigator.web.constant.user.UserConstant;
import cn.guet.navigator.web.dto.*;
import cn.guet.navigator.web.utils.LocationQueryUtil;
import cn.guet.navigator.web.utils.PathPlanUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.python.antlr.op.Add;

import java.util.*;

public class NavigationTest {

    @Test
    public void test() {

        List<Location> destinations = new ArrayList<Location>();
        List<Navigation> speed = new ArrayList<Navigation>();
        List<Navigation> distance = new ArrayList<Navigation>();
        List<Navigation> congestion = new ArrayList<Navigation>();

        List<RoutePlan> routePlans = new ArrayList<RoutePlan>();

        List<StepInfo> speedStepInfos = new ArrayList<StepInfo>();
        List<StepInfo> distanceStepInfos = new ArrayList<StepInfo>();
        List<StepInfo> congestionStepInfos = new ArrayList<StepInfo>();

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
//        destinations.add(new Location(108.298643, 22.856469));
        /**
         * 广西大学
         */
//        destinations.add(new Location(108.290815, 22.844167));

        Iterator<Location> locationIterator = destinations.iterator();

        while (locationIterator.hasNext()) {
            Location destination = locationIterator.next();
            speed.add(JSONObject.parseObject(PathPlanUtil.path(current, destination, PathPlanUtil.STRATEGY_SPEED_FIRST), Navigation.class));
            distance.add(JSONObject.parseObject(PathPlanUtil.path(current, destination, PathPlanUtil.STRATEGY_DISTANCE_FIRST), Navigation.class));
            congestion.add(JSONObject.parseObject(PathPlanUtil.path(current, destination, PathPlanUtil.STRATEGY_CONGESTION_FIRST), Navigation.class));
            current = destination;
        }

        Iterator<Navigation> speedNav = speed.iterator();
        Iterator<Navigation> disNav = distance.iterator();
        Iterator<Navigation> conNav = congestion.iterator();

        RoutePlan speedRoutePlan = new RoutePlan();
        speedRoutePlan.setPlanname("速度最快");

        RoutePlan distanceRoutePlan = new RoutePlan();
        distanceRoutePlan.setPlanname("距离最短");

        RoutePlan congestionRoutePlan = new RoutePlan();
        congestionRoutePlan.setPlanname("避免拥堵");

        while (speedNav.hasNext()) {
            Navigation nav = speedNav.next();
            Iterator<Route> routeIterator = nav.getRoute().iterator();
            while (routeIterator.hasNext()) {
                Route route = routeIterator.next();
                Iterator<TransferScheme> transferSchemeIterator = route.getPaths().iterator();
                while (transferSchemeIterator.hasNext()) {
                    TransferScheme transferScheme = transferSchemeIterator.next();
                    speedRoutePlan.setDistance(speedRoutePlan.getDistance() + transferScheme.getDistance());
                    speedRoutePlan.setDuration(speedRoutePlan.getDuration() + transferScheme.getDuration());
                    Iterator<Step> stepsIterator = transferScheme.getSteps().iterator();
                    Set<String> nameSet = new HashSet<String>();
                    while (stepsIterator.hasNext()) {
                        Step step = stepsIterator.next();
                        StepInfo tmp = new StepInfo();
                        tmp.setDistance(step.getDistance());
                        tmp.setDuration(step.getDuration());
                        String[] pos = (step.getPolyline().split(";"))[0].split(",");
                        Double longitude = Double.valueOf(pos[0]);
                        Double latitude = Double.valueOf(pos[1]);
                        Address address = JSONObject.parseObject(LocationQueryUtil.location(new Location(longitude, latitude)), Address.class);
                        AddressComponent addressComponent = address.getRegeocode().getAddressComponent();
                        StringBuffer origin = new StringBuffer(address.getRegeocode().getFormatted_address());
                        StringBuilder prefix = new StringBuilder().append(addressComponent.getProvince()).append(addressComponent.getCity()).append(addressComponent.getDistrict()).append(addressComponent.getTownship());
                        origin.delete(0, prefix.length());
                        if (!nameSet.contains(origin.toString())) {
                            tmp.setStepname(origin.toString());
                            nameSet.add(origin.toString());
                            speedRoutePlan.getStepInfos().add(tmp);
                        }
                    }
                }
            }
        }

        while (disNav.hasNext()) {
            Navigation nav = disNav.next();
            Iterator<Route> routeIterator = nav.getRoute().iterator();
            while (routeIterator.hasNext()) {
                Route route = routeIterator.next();
                Iterator<TransferScheme> transferSchemeIterator = route.getPaths().iterator();
                while (transferSchemeIterator.hasNext()) {
                    TransferScheme transferScheme = transferSchemeIterator.next();
                    distanceRoutePlan.setDistance(distanceRoutePlan.getDistance() + transferScheme.getDistance());
                    distanceRoutePlan.setDuration(distanceRoutePlan.getDuration() + transferScheme.getDuration());
                    Iterator<Step> stepsIterator = transferScheme.getSteps().iterator();
                    Set<String> nameSet = new HashSet<String>();
                    while (stepsIterator.hasNext()) {
                        Step step = stepsIterator.next();
                        StepInfo tmp = new StepInfo();
                        tmp.setDistance(step.getDistance());
                        tmp.setDuration(step.getDuration());
                        String[] pos = (step.getPolyline().split(";"))[0].split(",");
                        Double longitude = Double.valueOf(pos[0]);
                        Double latitude = Double.valueOf(pos[1]);
                        Address address = JSONObject.parseObject(LocationQueryUtil.location(new Location(longitude, latitude)), Address.class);
                        AddressComponent addressComponent = address.getRegeocode().getAddressComponent();
                        StringBuffer origin = new StringBuffer(address.getRegeocode().getFormatted_address());
                        StringBuilder prefix = new StringBuilder().append(addressComponent.getProvince()).append(addressComponent.getCity()).append(addressComponent.getDistrict()).append(addressComponent.getTownship());
                        origin.delete(0, prefix.length());
                        if (!nameSet.contains(origin.toString())) {
                            tmp.setStepname(origin.toString());
                            nameSet.add(origin.toString());
                            distanceRoutePlan.getStepInfos().add(tmp);
                        }
                    }
                }
            }
        }

        while (conNav.hasNext()) {
            Navigation nav = conNav.next();
            Iterator<Route> routeIterator = nav.getRoute().iterator();
            while (routeIterator.hasNext()) {
                Route route = routeIterator.next();
                Iterator<TransferScheme> transferSchemeIterator = route.getPaths().iterator();
                while (transferSchemeIterator.hasNext()) {
                    TransferScheme transferScheme = transferSchemeIterator.next();
                    congestionRoutePlan.setDistance(congestionRoutePlan.getDistance() + transferScheme.getDistance());
                    congestionRoutePlan.setDuration(congestionRoutePlan.getDuration() + transferScheme.getDuration());
                    Iterator<Step> stepsIterator = transferScheme.getSteps().iterator();
                    Set<String> nameSet = new HashSet<String>();
                    while (stepsIterator.hasNext()) {
                        Step step = stepsIterator.next();
                        StepInfo tmp = new StepInfo();
                        tmp.setDistance(step.getDistance());
                        tmp.setDuration(step.getDuration());
                        String[] pos = (step.getPolyline().split(";"))[0].split(",");
                        Double longitude = Double.valueOf(pos[0]);
                        Double latitude = Double.valueOf(pos[1]);
                        Address address = JSONObject.parseObject(LocationQueryUtil.location(new Location(longitude, latitude)), Address.class);
                        AddressComponent addressComponent = address.getRegeocode().getAddressComponent();
                        StringBuffer origin = new StringBuffer(address.getRegeocode().getFormatted_address());
                        StringBuilder prefix = new StringBuilder().append(addressComponent.getProvince()).append(addressComponent.getCity()).append(addressComponent.getDistrict()).append(addressComponent.getTownship());
                        origin.delete(0, prefix.length());
                        if (!nameSet.contains(origin.toString())) {
                            tmp.setStepname(origin.toString());
                            nameSet.add(origin.toString());
                            congestionRoutePlan.getStepInfos().add(tmp);
                        }
                    }
                }
            }
        }

        return;

    }


    @Test
    public void test2() {

        //起始点
        Location current = new Location(110.336514, 25.283836);

        List<Location> destinations = new ArrayList<Location>();

        //途经点
        destinations.add(new Location(110.289039, 25.093886));

        List<List<Navigation>> navList = new ArrayList<List<Navigation>>();

        List<Navigation> speed = new ArrayList<Navigation>();
        List<Navigation> distance = new ArrayList<Navigation>();
        List<Navigation> congestion = new ArrayList<Navigation>();

        Iterator<Location> locationIterator = destinations.iterator();

        while (locationIterator.hasNext()) {
            Location destination = locationIterator.next();
            speed.add(JSONObject.parseObject(PathPlanUtil.path(current, destination, PathPlanUtil.STRATEGY_SPEED_FIRST), Navigation.class));
            distance.add(JSONObject.parseObject(PathPlanUtil.path(current, destination, PathPlanUtil.STRATEGY_DISTANCE_FIRST), Navigation.class));
            congestion.add(JSONObject.parseObject(PathPlanUtil.path(current, destination, PathPlanUtil.STRATEGY_CONGESTION_FIRST), Navigation.class));
            current = destination;
        }

        navList.add(speed);
        navList.add(distance);
        navList.add(congestion);

        /**
         * 速度最快方案
         */
        RoutePlan speedRoutePlan = new RoutePlan();
        speedRoutePlan.setPlanname(UserConstant.STRATEGY_SPEED);
        /**
         * 距离最短
         */
        RoutePlan distanceRoutePlan = new RoutePlan();
        distanceRoutePlan.setPlanname(UserConstant.STRATEGY_DISTANCE);
        /**
         * 避免拥堵
         */
        RoutePlan congestionRoutePlan = new RoutePlan();
        congestionRoutePlan.setPlanname(UserConstant.STRATEGY_VOID_CONGESTION);

        List<RoutePlan> routePlans = new ArrayList<RoutePlan>();
        routePlans.add(speedRoutePlan);
        routePlans.add(distanceRoutePlan);
        routePlans.add(congestionRoutePlan);

        Iterator<List<Navigation>> navIterator = navList.iterator();
        Iterator<RoutePlan> routePlanIterator = routePlans.iterator();

        while (navIterator.hasNext()) {

            List<Navigation> navigations = navIterator.next();
            Iterator<Navigation> navigationIterator = navigations.iterator();
            RoutePlan routePlan = routePlanIterator.next();
            HashSet<String> locationNameSet = new HashSet<String>();

            while (navigationIterator.hasNext()) {

                Navigation speedNavigation = navigationIterator.next();
                Iterator<Route> speedIterator = speedNavigation.getRoute().iterator();

                while (speedIterator.hasNext()) {

                    Route speedRoute = speedIterator.next();
                    Iterator<TransferScheme> speedTransferSchemeIterator = speedRoute.getPaths().iterator();

                    while (speedTransferSchemeIterator.hasNext()) {

                        TransferScheme speedTransferScheme = speedTransferSchemeIterator.next();
                        routePlan.setDistance(routePlan.getDistance() + speedTransferScheme.getDistance());
                        routePlan.setDuration(routePlan.getDuration() + speedTransferScheme.getDuration());

                        Iterator<Step> speedStepsIterator = speedTransferScheme.getSteps().iterator();

                        while (speedStepsIterator.hasNext()) {

                            Step speedStep = speedStepsIterator.next();

                            StepInfo speedTmp = new StepInfo();

                            speedTmp.setDistance(speedStep.getDistance());
                            speedTmp.setDuration(speedStep.getDuration());

                            String[] speedPos = (speedStep.getPolyline().split(";"))[1].split(",");

                            Double speedLongitude = Double.valueOf(speedPos[0]);
                            Double speedLatitude = Double.valueOf(speedPos[1]);

                            Address speedAddress = JSONObject.parseObject(LocationQueryUtil.location(new Location(speedLongitude, speedLatitude)), Address.class);

                            AddressComponent speedAddressComponent = speedAddress.getRegeocode().getAddressComponent();

                            StringBuilder speedOrigin = new StringBuilder(speedAddress.getRegeocode().getFormatted_address());
                            StringBuilder speedPrefix = new StringBuilder().append(speedAddressComponent.getProvince()).
                                    append(speedAddressComponent.getCity()).append(speedAddressComponent.getDistrict()).append(speedAddressComponent.getTownship());
                            speedOrigin.delete(0, speedPrefix.length());

                            if (locationNameSet.contains(speedOrigin.toString())) {
                                Iterator<StepInfo> stepInfoIterator = routePlan.getStepInfos().iterator();
                                while (stepInfoIterator.hasNext()) {
                                    StepInfo stepInfo = stepInfoIterator.next();
                                    if (stepInfo.getStepname().equals(speedOrigin.toString())) {
                                        stepInfo.setDuration(stepInfo.getDuration() + speedTmp.getDuration());
                                        stepInfo.setDistance(stepInfo.getDistance() + speedTmp.getDistance());
                                    }
                                }
                            } else {
                                speedTmp.setStepname(speedOrigin.toString());
                                routePlan.getStepInfos().add(speedTmp);
                                locationNameSet.add(speedOrigin.toString());
                            }

                        }
                    }
                }
            }

        }

        return;

    }

}
