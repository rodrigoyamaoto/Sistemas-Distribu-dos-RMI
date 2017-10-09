package br.com.devrybrasil.metrocamp.distribuidos.server;

import java.util.ArrayList;
import java.util.List;

public class RMIImplementation implements MyRMIService {

    @Override
    public String getMessage() {
        return "String do servidor, agora no lugar certo!";
    }

    @Override
    public List<Integer> getMultiples(Integer step, Integer limit) {
        List<Integer> multiples = new ArrayList<>();

        for(Integer i = step; i <= limit; i++){
            if( i % step == 0 )
                multiples.add(i);
        }
        return multiples;
    }

    @Override
    public List<Character> getCharacter(String string){
        List<Character> stringList = new ArrayList<Character>();
        char c;

        for(int i=0; i < string.length(); i++){
            c = string.charAt(i);
            stringList.add(c);
        }

        return stringList;
    }
}
