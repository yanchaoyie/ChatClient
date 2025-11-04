package com.ycy.common;

import java.io.Serializable;
/**
 * 客户端和服务端发送消息时传输的对象
 */

public class Message implements Serializable {
    // 明确指定一个 serialVersionUID
    // 这个值可以是任意的 long 类型数字，通常 IDE 可以帮你生成一个。
    // 这个生成的可能会和服务端这个类的值不一样，导致编译器认为这不是同一个类抛出异常
    private static final long serialVersionUID = 1L;
    private String sender;//发送者
    private String getter;//接收者
    private String content;// 内容
    private String sendTime;//发送时间
    private String mesType;//消息类型



    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }
}
