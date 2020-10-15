package main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class joinServer {
    private int port;
    private String IPserver;

    public joinServer(int newPort, String newIPserver) {
        this.port = newPort;
        this.IPserver = newIPserver;

    }

    public int getPort(){
        return this.port;
    }

    public String getIPserver(){
        return this.IPserver;
    }

    public void connectToServer() {

        int portOut = getPort() ;
        String IPout = getIPserver();

        try {
            Socket clientSocket = new Socket(IPout, portOut);

            DataOutputStream clientOutD = new DataOutputStream(clientSocket.getOutputStream());

            clientOutD.writeUTF("cliente conectado");

            //clientOutD.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println(ioException.getMessage());
        }
    }

}
