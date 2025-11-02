package com.ycy.chatclient.service;

import com.ycy.common.Message;

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
