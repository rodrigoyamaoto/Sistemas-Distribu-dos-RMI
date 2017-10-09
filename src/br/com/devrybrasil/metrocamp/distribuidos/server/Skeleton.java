package br.com.devrybrasil.metrocamp.distribuidos.server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Skeleton implements Runnable {
    Socket socketConnection;
    MyRMIService rmiService;

    Skeleton(Socket socket) {
        this.socketConnection = socket;
    }

    @Override
    public void run() {
        try {
            //Cria canais de comunicação
            DataInputStream in = new DataInputStream(socketConnection.getInputStream());
            DataOutputStream out = new DataOutputStream(socketConnection.getOutputStream());
            this.rmiService = new RMIImplementation();

            //Obtém o método que deve ser tratado
            int methodCalled = in.readInt();
            switch (methodCalled) {
                case RMICodes.GETMESSAGE:
                    handleGetMessage(in, out);
                    break;

                case RMICodes.GETMULTIPLES:
                    handleGetMultiples(in, out);
                    break;

                case RMICodes.GETCHARACTER:
                    handleGetCharacters(in, out);
                    break;

                default:
                    break;
            }
            socketConnection.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleGetMessage(DataInputStream in, DataOutputStream out) throws IOException {
        String result = rmiService.getMessage();
        ObjectOutputStream output = new ObjectOutputStream(out);
        output.writeObject(result);
    }

    private void handleGetMultiples(DataInputStream in, DataOutputStream out) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(in);
        ObjectOutputStream output = new ObjectOutputStream(out);
        List<Integer> parameters = (List<Integer>) input.readObject();

        if(parameters.get(1) < 1){
            output.writeObject(new ArithmeticException("Valor limite não pode ser menor que 1"));
        }
         else {
            //Chama o método remoto
            List<Integer> result = rmiService.getMultiples(parameters.get(0), parameters.get(1));

            output.writeObject(result);
        }
    }

    private void handleGetCharacters(DataInputStream in, DataOutputStream out) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(in);
        String parameters = (String) input.readObject();

        //Chama o método remoto
        List<Character> result = rmiService.getCharacter(parameters);

        //Envia resposta
        ObjectOutputStream output = new ObjectOutputStream(out);
        output.writeObject(result);
    }

}
