package cn.guet.navigator.web.config;

/**
 * 设备和微信小程序websocket连接配置类
 *
 * @author Administrator
 */
//@Configuration
//@EnableWebMvc
//@EnableWebSocket
//public class WechatDeviceWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
//
//        //前台 可以使用websocket环境
//        webSocketHandlerRegistry.addHandler(getWechatDeviceWebSocketHandler(),"/common/websocket/connect").addInterceptors(new HandshakeInterceptor());
//
//        //前台 不可以使用websocket环境，则使用sockjs进行模拟连接
////        webSocketHandlerRegistry.addHandler(myWebSocketHandler(), "/sockjs/websocket").addInterceptors(new HandshakeInterceptor())
////                .withSockJS();
//
//    }
//
//    @Bean
//    public WebSocketHandler getWechatDeviceWebSocketHandler(){
//        return new WechatDeviceWebSocket();
//    }
//}
