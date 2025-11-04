package com.ycy.chatclient.service;

import com.ycy.common.Message;
import com.ycy.common.MessageType;
import com.ycy.common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class UserClientService {
    private User u = new User();//把User类设置成属性，方便后面调用
    private Socket socket;//设置一个Socket属性，方便后面进行多线程编程时为每一个线程分配Socket

    public boolean checkUser(String userId, String password) {
        u.setUserId(userId);
        u.setPassword(password);
        boolean flag = false;//判断标志，用于判断是否注册成功

        try {//向服务端发送用户数据
            socket = new Socket(InetAddress.getByName("172.31.153.119"), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);

            //从服务端得到确认信息
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) ois.readObject();
            //判断用户是否登录成功
            if(ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)){
                //登录成功,启动客户端的一个线程，来一直监听服务端的数据
                ClientConnectServerThread clientConnectServerThread
                        = new ClientConnectServerThread(socket);
                clientConnectServerThread.start();
                //创建一个类，用于管理一个用户的所有线程
                ManageClientConnectServerThread.addClientConnectServerThread(userId,clientConnectServerThread);

                flag = true;
            }else {
                //登录失败，关闭socket
                socket.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
