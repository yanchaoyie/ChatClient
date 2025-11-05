package com.ycy.chatclient.service;

import com.ycy.common.Message;
import com.ycy.common.MessageType;
import com.ycy.common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {
    private Socket socket;
    private User u =null;
    ObjectOutputStream oos;
    ObjectInputStream in;

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public void setUser(User u) {
        this.u = u;
    }

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //线程启动之后一直保持着和服务端的通信
        try {
            ObjectInputStream ois = getIn();
            ObjectOutputStream oos = getOos();

            while (true) {
                try {//等待着从服务端接收数据，如果没有收到就一直阻塞在这里
                    //ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    Message message = (Message) ois.readObject();
                    //接收到来自服务端的反馈
                    if (message.getMesType().equals(MessageType.MESSAGE_RET_OFFLINE_FRIENDS)) {
                        String[] s = message.getContent().split(" ");
                        //用户在线消息样式由逗号隔开。形式为100 200 sora
                        System.out.println("=========在线用户列表========");
                        for (int i = 0; i < s.length; i++) {
                            System.out.print(s[i] + " ");
                        }
                    }
                    if (message.getMesType().equals(MessageType.MESSAGE_COMM_MESSAGE)) {
                       //如果服务端发来信息的接收者是和该用户信息一直的就把信息读出来
                        System.out.println("\n收到来自用户 " + message.getSender() + "的信息");
                        System.out.println(message.getGetter());
                        System.out.println(message.getContent());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
