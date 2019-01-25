package cn.guet.navigator.web.config;

import cn.guet.navigator.web.interceptor.HandshakeInterceptor;
import cn.guet.navigator.web.websocket.DeviceWebSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 服务器和设备websocket连接配置类
 *
 * @author Administrator
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class DeviceWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

        //可以使用websocket环境
        webSocketHandlerRegistry.addHandler(getDeviceWebSocketHandler(), "/websocket/device/navigation")
                .addInterceptors(new HandshakeInterceptor());

    }

    @Bean
    public WebSocketHandler getDeviceWebSocketHandler() {
        return new DeviceWebSocket();
    }
}
