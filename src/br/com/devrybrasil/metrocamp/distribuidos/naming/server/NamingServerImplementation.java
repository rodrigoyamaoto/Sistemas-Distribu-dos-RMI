package br.com.devrybrasil.metrocamp.distribuidos.naming.server;

import br.com.devrybrasil.metrocamp.distribuidos.Address;
import br.com.devrybrasil.metrocamp.distribuidos.NamingService;

import java.util.HashMap;

public class NamingServerImplementation implements NamingService {

    HashMap<String, Address> addressHashMap;

    NamingServerImplementation() {
        addressHashMap = new HashMap<>();
    }

    @Override
    synchronized public void add(String nome, Address address) {
        addressHashMap.put(nome, address);
    }

    @Override
    synchronized public void remove(String nome) {
        addressHashMap.remove(nome);
    }

    @Override
    public Address find(String nome) {
        return addressHashMap.get(nome);
    }
}
