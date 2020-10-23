package model.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This class send the information through the socket
 * @Param newPort new int
 * @Param ner
 */
public class Client {
    private int port;
    private String IPserver;

    public Client(int newPort, String newIPserver) {
        this.port = newPort;
        this.IPserver = newIPserver;

    }

    public int getPort(){
        return this.port;
    }

    public String getIPserver(){
        return this.IPserver;
    }

    public void connectToServer(String jacksonStr) {

        int portOut = getPort() ;
        String IPout = getIPserver();

        try {
            Socket clientSocket = new Socket(IPout, portOut);

            DataOutputStream clientOutD = new DataOutputStream(clientSocket.getOutputStream());

            clientOutD.writeUTF(jacksonStr);

            clientOutD.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println(ioException.getMessage());
            //agregar al log
        }
    }

}
