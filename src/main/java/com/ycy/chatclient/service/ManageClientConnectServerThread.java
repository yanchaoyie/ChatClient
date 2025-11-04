package com.ycy.chatclient.service;

import java.net.Socket;
import java.util.HashMap;

public class ManageClientConnectServerThread {
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    public static void addClientConnectServerThread(String userId,ClientConnectServerThread ccs) {
        hm.put(userId, ccs);
    }

    public static ClientConnectServerThread getClientConnectServerThread(String userId) {
        return hm.get(userId);
    }
}
