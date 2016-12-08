/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc2server;

/**
 *
 * @author Udmin
 */

import java.io.*;
import java.net.*;

public class ServerConn extends Thread{
    RC2Server servGUI;
    
    public ServerConn(RC2Server exServGUI) 
    {
        servGUI = exServGUI;
        this.start();        
    }
    
    public void run(){
        try
        {			
            int serverPort = 8678;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            int i = 1;
            int meja = 1;
            System.out.println("WAITING CONNECTION");
            while(true) 
            {
                while(i<10)
                {
                    Socket clientSocket = listenSocket.accept();
                    String IP = clientSocket.getInetAddress().getHostAddress().toString();
//                    InetSocketAddress sockaddr = (InetSocketAddress)clientSocket.getRemoteSocketAddress();
//                    String IP = sockaddr.getAddress().toString();
                    System.out.println("CLIENT "+ i + " //" + IP + ":" + serverPort);
                    Connect c = new Connect(clientSocket, servGUI, IP, meja);
                    i = i + 1;
                    meja++;
                }
                while(i>9)
                {
                    Socket clientSocket = listenSocket.accept();
                    String IP = clientSocket.getInetAddress().getHostAddress().toString();
//                    InetSocketAddress sockaddr = (InetSocketAddress)clientSocket.getRemoteSocketAddress();
//                    String IP = sockaddr.getAddress().toString();
                    System.out.println("client"+ i + "//" + IP + ":" + serverPort);
                    Connect c = new Connect(clientSocket, servGUI, IP, meja);
                    i = i + 1;
                    meja++;
                }				
            }
        } 
        catch(IOException e) 
        {
            System.out.println("Listen: " +e.getMessage());
        }		
    }
}

