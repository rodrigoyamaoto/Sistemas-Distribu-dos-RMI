package br.com.devrybrasil.metrocamp.distribuidos.naming.server;

public class ServerNaming {
    public static void main(String[] args) {
        NamingMultiThreadServer server = new NamingMultiThreadServer(4321);
        server.startServer();
    }
}
