package br.com.devrybrasil.metrocamp.distribuidos.server;

public class Server {
    public static void main(String[] args) {
        RMIMultiThreadServer server = new RMIMultiThreadServer(1234);
        server.startServer();
    }
}
