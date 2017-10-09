package br.com.devrybrasil.metrocamp.distribuidos.naming.client;

import br.com.devrybrasil.metrocamp.distribuidos.Address;

public class ClientNaming {
    public static void main(String[] args) {
        NamingStub stub = new NamingStub();

        stub.add("Teste", new Address("myhost", 999));

        Address address = stub.find("asdf");
        if (address != null)
            System.out.println(address.getHost());
        else
            System.out.println("not found : asdf ");

        Address address2 = stub.find("Teste");
        if (address2 != null)
            System.out.println("found : " + address.getHost());
        else
            System.out.println(" found : Teste ");

        stub.remove("Teste");

        Address address3 = stub.find("Teste");
        if (address3 != null)
            System.out.println(" found even after remove : " + address.getHost());
        else
            System.out.println(" not found after remove ");

    }
}