package com.company;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            boolean stop = false;
            while (!stop) {
                System.out.println("Cekam na klijenta...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Klijent je konektovan.");
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
