package com.example.websocket.listener;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author llc
 * @Description
 * @Date 2019/8/26 13:35
 */
@Component
public class SocketSessionMap {

    private final static ConcurrentMap<String, String> sessionMap = new ConcurrentHashMap<>();

    /**
     * 注册Session
     * @param userId
     * @param sessionId
     */
    public synchronized void registerSession(String userId, String sessionId) {
        sessionMap.put(userId,sessionId);
    }

    /**
     * 移除Session
     * @param userId
     * @param sessionId
     */
    public synchronized void removeSession(String userId, String sessionId) {
        sessionMap.remove(userId);
    }

    /**
     * 获取用户的SessionId
     * @param userId
     * @return
     */
    public String getUserSessionId(String userId){
        return sessionMap.get(userId);
    }

    /**
     * 获取所有Session集合
     * @return
     */
    public Map<String, String> queryAllSession(){
        return sessionMap;
    }
    /**
     * 获取集合的大小
     */
    public int onlineCount(){
        return sessionMap.size();
    }
}
