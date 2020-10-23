package model.sockets;


import com.sun.javafx.geom.AreaOp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 *
 */
public class Server implements Runnable{
    int port = 1024;
    String messageJSON;

    public int getPort(){
        return this.port;
    }

    private void setPort(int num){
        this.port = num;
    }

    public String getIp(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());;
            // agregar en el log
        }
        return null;
    }

    @Override
    public void run() {

        boolean portAlive = true;

        while(portAlive) {
            try {

                ServerSocket server = new ServerSocket(getPort());

                portAlive = false;

                while (true) {
                    System.out.println("Listening");

                    Socket socketS = server.accept();

                    DataInputStream serverInD = new DataInputStream(socketS.getInputStream());


                    String message = serverInD.readUTF();

                    this.messageJSON = message;
                    // luego pasar ese message al jackson

                    System.out.println(message);

                    socketS.close();
                }
            } catch (IOException e) {
                setPort(++port);
            }
        }

    }

    /**
     * Sets new messageJSON.
     *
     * @param messageJSON New value of messageJSON.
     */
    public void setMessageJSON(String messageJSON) {
        this.messageJSON = messageJSON;
    }

    /**
     * Gets messageJSON.
     *
     * @return Value of messageJSON.
     */
    public String getMessageJSON() {
        return messageJSON;
    }
}
