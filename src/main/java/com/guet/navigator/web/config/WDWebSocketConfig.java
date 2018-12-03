package com.guet.navigator.web.config;

import com.guet.navigator.web.interceptor.HandshakeInterceptor;
import com.guet.navigator.web.websocket.WDWebSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 设备和微信小程序websocket连接配置类
 *
 */
//@Configuration
//@EnableWebMvc
//@EnableWebSocket
public class WDWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

        //前台 可以使用websocket环境
        webSocketHandlerRegistry.addHandler(getWDWebSocketHandler(),"/common/websocket/connect").addInterceptors(new HandshakeInterceptor());

        //前台 不可以使用websocket环境，则使用sockjs进行模拟连接
//        webSocketHandlerRegistry.addHandler(myWebSocketHandler(), "/sockjs/websocket").addInterceptors(new HandshakeInterceptor())
//                .withSockJS();

    }

    @Bean
    public WebSocketHandler getWDWebSocketHandler(){
        return new WDWebSocket();
    }
}
