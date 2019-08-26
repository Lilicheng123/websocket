package com.example.websocket.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * @Author llc
 * @Description
 * @Date 2019/8/26 13:32
 */
@Component
public class STOMPConnectEventListener implements ApplicationListener<SessionConnectEvent> {

    @Autowired
    SocketSessionMap socketSessionMap;

        @Override
        public void onApplicationEvent(SessionConnectEvent event) {
            StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
            String userId = sha.getFirstNativeHeader("id");
            String sessionId = sha.getSessionId();
            //判断客户端的连接状态
            switch (sha.getCommand()) {
                case CONNECT:
                    System.out.println("上线：" + userId + "  " + sessionId);
                    socketSessionMap.registerSession(userId, sessionId);
                    break;
                case DISCONNECT:
                    System.out.println("下线");
                    break;
                case SUBSCRIBE:
                    System.out.println("订阅");
                    break;
                case SEND:
                    System.out.println("发送");
                    break;
                case UNSUBSCRIBE:
                    System.out.println("取消订阅");
                    break;
                default:
                    break;
            }
        }
}
