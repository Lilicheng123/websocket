package com.example.websocket.listener;

/**
 * @Author llc
 * @Description
 * @Date 2019/8/26 13:40
 */
public class Message {
    private int id; //用户ID
    private String content;//发送内容
    private int pid;    //发送到用户

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Message(int id, String content, int pid) {
        this.id = id;
        this.content = content;
        this.pid = pid;
    }

    public Message() {
    }
}
