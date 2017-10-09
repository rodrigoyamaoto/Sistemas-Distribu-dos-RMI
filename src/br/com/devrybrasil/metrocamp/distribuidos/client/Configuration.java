package br.com.devrybrasil.metrocamp.distribuidos.client;

import br.com.devrybrasil.metrocamp.distribuidos.Address;
public class Configuration {
    public static Address getRMIServerAddress() {
        return new Address("localhost", 1234);
    }
    public static Address getRMIServerNamingAddress() {
        return new Address("localhost", 4321);
    }
}
