package br.com.devrybrasil.metrocamp.distribuidos;

import java.io.Serializable;

public class Address implements Serializable{
    private String host;
    private int port;

    public Address(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "(" + host + "," + port + ")";
    }
}
