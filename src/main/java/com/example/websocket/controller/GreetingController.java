package com.example.websocket.controller;

import com.example.websocket.listener.Message;
import com.example.websocket.listener.SocketSessionMap;
import com.example.websocket.pojo.Greeting;
import com.example.websocket.pojo.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

/**
 * @Author llc
 * @Description
 * @Date 2019/8/26 13:21
 */
@Controller
public class GreetingController {
    @Autowired
    SocketSessionMap socketSessionMap;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        System.out.println("收到：" + message.toString() + "消息");
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/chatOut")
    public void sayHello(String userId) {
        String sessionId = socketSessionMap.getUserSessionId(userId);
        System.out.println("下线：" + userId + "  " + sessionId);
        socketSessionMap.removeSession(userId,sessionId);
    }

    @MessageMapping("/chat")
    public void sayHello(Message user) {
        System.out.println(user.getId()+"-->"+user.getPid()+":"+user.getContent());
        String userPid = String.valueOf(user.getPid());
        String userId = String.valueOf(user.getId());
        String sendTo = "/topic/chat/"+userPid;
        String content = user.getId()+":"+user.getContent();
        if (socketSessionMap.getUserSessionId(userPid)!=null){
            messagingTemplate.convertAndSend(sendTo, HtmlUtils.htmlEscape(content));
        }else {
            sendTo = "/topic/chat/"+userId;
            content = "对方已下线";
            messagingTemplate.convertAndSend(sendTo, HtmlUtils.htmlEscape(content));
        }
    }

    @RequestMapping("/chat/{id}")
    public String chat_page(@PathVariable int id, ModelMap model) {
        model.addAttribute("id", id);
        int count = socketSessionMap.onlineCount();
        model.addAttribute("count", count);
        return "chat";
    }

}
