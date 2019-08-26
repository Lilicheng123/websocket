package com.example.websocket.pojo;

/**
 * @Author llc
 * @Description
 * @Date 2019/8/26 13:22
 */
public class Greeting {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Greeting(String content) {
        this.content = content;
    }
}
