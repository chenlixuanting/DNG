package com.guet.navigator.web.websocket;

import com.guet.navigator.web.service.DeviceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 设备和微信小程序间的WebSocket连接
 *
 */
public class WDWebSocket implements WebSocketHandler {

    @Autowired
    private DeviceRecordService deviceRecordService;

    //线程安全的sesionMap
    private Map<String,Object> sessionMap = new ConcurrentHashMap<String,Object>();

    //用户的WebSocketMap
    private Map<String,Object> userSessionMap = new ConcurrentHashMap<String,Object>();

    //设备的WebSocketMap
    private Map<String,Object> deviceSessionMap = new ConcurrentHashMap<String,Object>();

    /**
     * 连接建立后
     *
     * @param webSocketSession
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        //连接建立时,存储 key=sessionId,value=WebSocketSession
        sessionMap.put(webSocketSession.getId(),webSocketSession);
    }

    /**
     * 接收到消息时调用
     *
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        webSocketSession.sendMessage(new TextMessage("welcome websocket!"));
        System.out.println(webSocketMessage.getPayload());
    }

    /**
     * 传输出错
     * @param webSocketSession
     * @param throwable
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    /**
     * 连接关闭
     *
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        //从map集合中清除webSocketSession
        sessionMap.remove(webSocketSession.getId());
    }

    /**
     *
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
