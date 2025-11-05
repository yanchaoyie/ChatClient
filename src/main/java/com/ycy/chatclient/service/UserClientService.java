package com.ycy.chatclient.service;

import com.ycy.common.Message;
import com.ycy.common.MessageType;
import com.ycy.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class UserClientService {
    private User u = new User();//把User类设置成属性，方便后面调用
    private Socket socket;//设置一个Socket属性，方便后面进行多线程编程时为每一个线程分配Socket
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void setObjectOutputStream(ObjectOutputStream oos) {
        this.out = oos;
    }

    public void setObjectInputStream(ObjectInputStream ois) {
        this.in = ois;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public boolean checkUser(String userId, String password) {
        u.setUserId(userId);
        u.setPassword(password);
        boolean flag = false;//判断标志，用于判断是否注册成功

        try {//向服务端发送用户数据
            socket = new Socket(InetAddress.getByName("172.31.153.119"), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            setObjectOutputStream(oos);
            setObjectInputStream(ois);

            System.out.println("no sent out");
            oos.writeObject(u);
            System.out.println("sent out");
            //从服务端得到确认信息
            Message ms = (Message) ois.readObject();
            //判断用户是否登录成功
            if(ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)){
                //登录成功,启动客户端的一个线程，来一直监听服务端的数据
                ClientConnectServerThread clientConnectServerThread
                        = new ClientConnectServerThread(socket);
                //把用户信息传进去方便线程在监听服务器的信息时进行信息的判断
                clientConnectServerThread.setUser(u);
                clientConnectServerThread.setIn(in);
                clientConnectServerThread.setOos(out);
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

    public void onlineUser(String userId) {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIENDS);//把消息格式设置为请求在线用户
        message.setSender(u.getUserId());//设置发送者
        try {//得到放在集合里的线程类里的socket的OutputStream()
            //ObjectOutputStream oos = new
                    //ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(u.getUserId()).getSocket().getOutputStream());
            out.writeObject(message);//发送请求
            out.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void userExit(){
        System.out.println("用户退出程序");
        Message message = new Message();
        message.setSender(u.getUserId());
        message.setMesType(MessageType.MESSAGE_USER_EXIT);
        try {
            //ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());//发送关闭消息
            out.writeObject(message);
            out.flush();
            System.out.println("用户退出程序");
            System.exit(0);//结束客户端主进程
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //私聊代码
    public void sendMessage(String getter,String message){
        Message mes = new Message();
        mes.setSender(u.getUserId());
        mes.setGetter(getter);
        mes.setMesType(MessageType.MESSAGE_COMM_MESSAGE);
        mes.setContent(message);
        try {
            //ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(mes);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
