package br.com.devrybrasil.metrocamp.distribuidos.client;

import br.com.devrybrasil.metrocamp.distribuidos.Address;
import br.com.devrybrasil.metrocamp.distribuidos.server.MyRMIService;
import br.com.devrybrasil.metrocamp.distribuidos.server.RMICodes;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Stub implements MyRMIService {

    DataInputStream in = null;
    DataOutputStream out = null;
    ObjectInputStream input = null;
    ObjectOutputStream output = null;

    @Override
    public String getMessage() {
        String returnValue = null;
        Socket socket = getSocket(RMICodes.GETMESSAGE);

        try
        {
            returnValue = (String) input.readObject();
            input.close();
        }
        catch (IOException e) { e.printStackTrace(); }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
        finally
        {
            try { socket.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }
        return returnValue;
    }

    @Override
    public List<Integer> getMultiples(Integer step, Integer limit)  {
        List<Integer> multiples = null;
        Socket socket = getSocket(RMICodes.GETMULTIPLES);
        try {
            //Escreve parâmetros
            List<Integer> parameters = new ArrayList<Integer>();
            parameters.add(step);
            parameters.add(limit);
            output.writeObject(parameters);

            //Canal de leitura de objetos
            ObjectInputStream input = new ObjectInputStream(in);

            //Lê Resultado
            Object returnedValue =   input.readObject();

            if (returnedValue instanceof ArithmeticException)
            {
                throw (ArithmeticException)returnedValue;
            } else {
                multiples = (List<Integer>)returnedValue;
            }
        }
        catch (IOException IOException)
        {
            IOException.printStackTrace();
        }
        catch (ClassNotFoundException classNotFoundException)
        {
            classNotFoundException.printStackTrace();
        }
        catch (ArithmeticException arithmeticException)
        {
            arithmeticException.printStackTrace();
        }
        finally
        {
            try { socket.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }
        return multiples;
    }

    @Override
    public List<Character> getCharacter(String message) {
        List<Character> returnValue = null;
        Socket socket = getSocket(RMICodes.GETCHARACTER);
        try {
            //Escreve parâmetros
            String parameters = message;
            output.writeObject(parameters);

            //Canal de leitura de objetos
            ObjectInputStream input = new ObjectInputStream(in);

            //Lê String
            returnValue = (List<Character>) input.readObject();
        }
        catch (IOException e) { e.printStackTrace(); }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
        finally
        {
            try { socket.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }
        return returnValue;
    }

    private Socket getSocket(int rmiCodes) {
        try
        {
            Address rmiAddress = Configuration.getRMIServerAddress();
            Socket socket = new Socket(rmiAddress.getHost(), rmiAddress.getPort());
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            switch(rmiCodes){
                case RMICodes.GETMESSAGE:
                    this.out.writeInt(RMICodes.GETMESSAGE);
                    this.input = new ObjectInputStream(in);
                    break;
                case RMICodes.GETMULTIPLES:
                    this.out.writeInt(RMICodes.GETMULTIPLES);
                    this.output = new ObjectOutputStream(out);
                    break;
                case RMICodes.GETCHARACTER:
                    this.out.writeInt(RMICodes.GETCHARACTER);
                    this.output = new ObjectOutputStream(out);
                    break;
            }
            return socket;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
