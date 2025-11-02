package com.ycy.chatclient.view;

import java.util.Scanner;

public class ChatView {
    private boolean loop = true;
    private String key;

    public static void main(String[] args) {
        new ChatView().mainView();
        System.out.println("退出客户端系统");
    }


    private void mainView(){
        while(loop){
            System.out.println("==========欢迎登录聊天系统==========");
            System.out.println("\t\t1.登录系统");
            System.out.println("\t\t9.退出系统");

            System.out.print("请输入你的选择：");
            Scanner scanner = new Scanner(System.in);
            key = scanner.next();

            switch(key){
                case "1":
                    System.out.println("登录系统");
                    System.out.print("请输入用户id：");
                    String userId = scanner.next();
                    System.out.print("请输入密  码：");
                    String password = scanner.next();

                    //把登录信息封装成User对象发送给服务器验证是否有该用户,创建一个类在包service里的UserClientService类
                    //在这个类里向服务器传输User类信息，用来判断用户是否登录成功
                    if(true){
                        while (loop) {
                            //进入客户端二级菜单
                            System.out.println("==========欢迎(用户 " + userId + ")进入聊天系统==========");
                            System.out.println("\t\t1.显示在线人数");
                            System.out.println("\t\t2.私聊消息");
                            System.out.println("\t\t3.群发消息");
                            System.out.println("\t\t4.发送文件");
                            System.out.println("\t\t9.退出系统");
                            System.out.print("请输入你的选择：");
                            key = scanner.next();
                            switch (key) {
                                case "1":
                                    System.out.println("显示在线人数");
                                    break;
                                case "2":
                                    System.out.println("私聊消息");
                                    break;
                                case "3":
                                    System.out.println("群发消息");
                                    break;
                                case "4":
                                    System.out.println("发送文件");
                                    break;
                                case "9":
                                    System.out.println("退出系统");
                                    loop = false;
                                    break;
                            }
                        }

                    }else{
                        System.out.println("登录失败");
                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }
}
