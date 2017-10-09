package br.com.devrybrasil.metrocamp.distribuidos.server;

import java.util.List;

public interface MyRMIService {
    String getMessage();
    List<Character> getCharacter(String message);
    List<Integer> getMultiples(Integer step, Integer limit);
}
