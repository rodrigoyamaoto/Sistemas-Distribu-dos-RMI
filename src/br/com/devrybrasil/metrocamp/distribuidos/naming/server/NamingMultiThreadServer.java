package br.com.devrybrasil.metrocamp.distribuidos.naming.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NamingMultiThreadServer {
    private int port;

    public NamingMultiThreadServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            ServerSocket ssock = new ServerSocket(port);
            while (true)
            {
                Socket socket = ssock.accept();
                System.out.println("Nova conexão");

                //Cria uma thread para cuidar da conexão
                NamingSkeleton workServer = new NamingSkeleton(socket);
                Thread workThread = new Thread(workServer);
                workThread.start();
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}
