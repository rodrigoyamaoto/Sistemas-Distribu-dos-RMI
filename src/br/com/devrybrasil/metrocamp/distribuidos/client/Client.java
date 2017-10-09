package br.com.devrybrasil.metrocamp.distribuidos.client;

import br.com.devrybrasil.metrocamp.distribuidos.server.MyRMIService;

public class Client {
    public static void main(String[] args) {
        MyRMIService stub = new Stub();

        System.out.println(stub.getMessage());

        System.out.println("\nMultiplos de 2 até 10 = " + stub.getMultiples(2,5));

        System.out.println("\n" + stub.getCharacter("Rodrigo"));

    }
}
