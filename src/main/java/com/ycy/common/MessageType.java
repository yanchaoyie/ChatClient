package com.ycy.common;

public interface MessageType {
    //在接口中定义的常量，用来表示不同的消息类型，直接通过接口名调用就能得到这个常量
    String MESSAGE_LOGIN_SUCCESS = "1";//登录成功
    String MESSAGE_LOGIN_FAIL = "2";//登录失败
}
