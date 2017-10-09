package br.com.devrybrasil.metrocamp.distribuidos.naming.server;

import br.com.devrybrasil.metrocamp.distribuidos.Address;
import br.com.devrybrasil.metrocamp.distribuidos.NamingService;
import br.com.devrybrasil.metrocamp.distribuidos.server.RMICodes;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class NamingSkeleton implements Runnable {
    Socket socketConnection;
    NamingService namingService;

    NamingSkeleton(Socket socket) {
        this.socketConnection = socket;
    }

    @Override
    public void run() {
        try {

            DataInputStream in = new DataInputStream(socketConnection.getInputStream());
            DataOutputStream out = new DataOutputStream(socketConnection.getOutputStream());
            this.namingService = new NamingServerImplementation();

            int methodCalled = in.readInt();
            switch (methodCalled) {
                case RMICodes.ADD:
                    handleAdd(in, out);
                    break;

                case RMICodes.REMOVE:
                    handleRemove(in, out);
                    break;

                case RMICodes.FIND:
                    handleFind(in, out);
                    break;

                default:
                    break;
            }
            socketConnection.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleAdd(DataInputStream in, DataOutputStream out) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(in);
        List<Object> parameters = (List<Object>) input.readObject();

        String name = (String) parameters.get(0);
        Address address = (Address) parameters.get(1);

        namingService.add(name, address);

        //Envia resposta
        ObjectOutputStream output = new ObjectOutputStream(out);
        output.writeObject(true);
    }

    private void handleRemove(DataInputStream in, DataOutputStream out) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(in);
        String name = (String) input.readObject();

        //Chama o método remoto
        namingService.remove(name);

        //Envia resposta
        ObjectOutputStream output = new ObjectOutputStream(out);
        output.writeObject(true);
    }

    private void handleFind(DataInputStream in, DataOutputStream out) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(in);
        String name = (String) input.readObject();

        //Chama o método remoto
        Address result = namingService.find(name);

        //Envia resposta
        ObjectOutputStream output = new ObjectOutputStream(out);
        output.writeObject(result);
    }
}