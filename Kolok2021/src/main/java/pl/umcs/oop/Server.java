package pl.umcs.oop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private List<ClientThread> clients = new ArrayList<>();


    public List<ClientThread> getClients() {
        return clients;
    }

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            {
                throw new RuntimeException(e);
            }

        }

    }

    public void run() {
        while (true) {
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
                ClientThread thread = new ClientThread(clientSocket, this);
                clients.add(thread);
                thread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void removeClient(ClientThread client) {
        clients.remove(client);
        System.out.println("removed" + client);
    }


}
