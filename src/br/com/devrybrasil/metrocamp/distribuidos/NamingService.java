package br.com.devrybrasil.metrocamp.distribuidos;

public interface NamingService {
    void add (String nome, Address address);
    void remove (String nome);
    Address find (String nome);
}
