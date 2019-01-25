package cn.guet.navigator.web.websocket;

import cn.guet.navigator.web.service.LoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务器和设备的WebSocket连接
 *
 * @author Administrator
 */
public class DeviceWebSocket implements WebSocketHandler {

    @Autowired
    private LoginRecordService deviceRecordService;

    /**
     * 线程安全的sesionMap
     */
    private Map<String, Object> sessionMap = new ConcurrentHashMap<String, Object>();

    /**
     * 设备的WebSocketMap
     */
    private Map<String, Object> deviceSessionMap = new ConcurrentHashMap<String, Object>();

    /**
     * 连接建立后
     *
     * @param webSocketSession
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
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
        System.out.println(webSocketMessage.getPayload());
    }

    /**
     * 传输出错
     *
     * @param webSocketSession
     * @param throwable
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.println("websocket send error:" + webSocketSession.getId());
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
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
