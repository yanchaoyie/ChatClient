package com.ycy.common;

import java.io.Serializable;

/*
* 表示一个用户信息
* 会在网络上以类的形式发送
* */
public class User implements Serializable {// 实现序列化在网络上传输
    // 明确指定一个 serialVersionUID
    // 这个值可以是任意的 long 类型数字，通常 IDE 可以帮你生成一个。
    private static final long serialVersionUID = 1L;

    private String userId;
    private String password;

    public User() {

    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    public String getUserId() {
        return userId;
    }
    public String getPassword() {
        return password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
