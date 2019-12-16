package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        int port = 0;
        for (int i = 0; i< args.length; i++) {
            if (args[i].equals("-p")) {
                port = Integer.parseInt(args[i+1]);
            }
        }
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server running on port " + serverSocket.getLocalPort());
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("Server says hello!");
            System.out.println(in.readLine());
            //todo wstawic tutaj czy bot oraz jak wielka plansza
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
