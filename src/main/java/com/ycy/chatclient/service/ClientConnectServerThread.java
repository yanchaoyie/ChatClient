package com.ycy.chatclient.service;

import com.ycy.common.Message;
import com.ycy.common.MessageType;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //线程启动之后一直保持着和服务端的通信
        while(true){
            try{//等待着从服务端接收数据，如果没有收到就一直阻塞在这里
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message)ois.readObject();
                //接收到来自服务端的反馈
                if(message.getMesType().equals(MessageType.MESSAGE_RET_OFFLINE_FRIENDS)){
                    String[] s = message.getContent().split(" ");
                    //用户在线消息样式由逗号隔开。形式为100 200 sora
                    System.out.println("=========在线用户列表========");
                    for(int i = 0; i < s.length; i++){
                        System.out.print(s[i] + " ");
                    }
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
