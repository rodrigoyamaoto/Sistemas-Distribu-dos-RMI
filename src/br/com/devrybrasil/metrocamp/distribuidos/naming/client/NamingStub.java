package br.com.devrybrasil.metrocamp.distribuidos.naming.client;

import br.com.devrybrasil.metrocamp.distribuidos.Address;
import br.com.devrybrasil.metrocamp.distribuidos.NamingService;
import br.com.devrybrasil.metrocamp.distribuidos.client.Configuration;
import br.com.devrybrasil.metrocamp.distribuidos.server.RMICodes;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NamingStub implements NamingService {

    DataInputStream in = null;
    DataOutputStream out = null;
    ObjectInputStream input = null;
    ObjectOutputStream output = null;

    @Override
    public void add(String nome, Address address) {
        boolean returnValue;
        Socket socket = getSocket(RMICodes.ADD);
        try {
            //Escreve parâmetros
            List<Object> parameters = new ArrayList<>();

            parameters.add(nome);
            parameters.add(address);

            output.writeObject(parameters);

            //Canal de leitura de objetos
            ObjectInputStream input = new ObjectInputStream(in);

            //Lê String
            returnValue = (boolean) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //return returnValue;
    }

    @Override
    public void remove(String nome) {
        boolean returnValue;
        Socket socket = getSocket(RMICodes.REMOVE);
        try {

            output.writeObject(nome);

            //Canal de leitura de objetos
            ObjectInputStream input = new ObjectInputStream(in);

            //Lê String
            returnValue = (boolean) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //return returnValue;
    }

    @Override
    public Address find(String nome) {
        Address returnValue = null;
        Socket socket = getSocket(RMICodes.FIND);
        try {

            output.writeObject(nome);
            //Canal de leitura de objetos
            ObjectInputStream input = new ObjectInputStream(in);

            //Lê String
            returnValue = (Address) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }

    private Socket getSocket(int rmiCodes) {
        try {
            Address rmiAddress = Configuration.getRMIServerNamingAddress();
            Socket socket = new Socket(rmiAddress.getHost(), rmiAddress.getPort());
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            switch (rmiCodes) {
                case RMICodes.ADD:
                    this.out.writeInt(RMICodes.ADD);
                    this.output = new ObjectOutputStream(out);
                    break;
                case RMICodes.REMOVE:
                    this.out.writeInt(RMICodes.REMOVE);
                    this.output = new ObjectOutputStream(out);
                    break;
                case RMICodes.FIND:
                    this.out.writeInt(RMICodes.FIND);
                    this.output = new ObjectOutputStream(out);
                    break;
            }
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
