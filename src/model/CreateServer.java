package model;


import com.sun.javafx.geom.AreaOp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



/**
 *
 */
public class CreateServer implements Runnable{
    int port = 1024;

    public int getPort(){
        return this.port;
    }

    private void setPort(int num){
        this.port = num;
    }

    @Override
    public void run() {

        boolean portAlive = true;

        while (portAlive) {
            try {

                ServerSocket server = new ServerSocket(getPort());

                System.out.println("Su puerto es:" + String.valueOf(getPort()));

                portAlive = false;

                while (true) {
                    System.out.println("Listening");
                    Socket socketS = server.accept();

                    DataOutputStream serverOut = new DataOutputStream(socketS.getOutputStream());
                    DataInputStream serverInD = new DataInputStream(socketS.getInputStream()); // aqui se debe cambiar por un objetimput

                    serverOut.writeUTF("Conectado al servidor!");

                    String message = serverInD.readUTF();

                    System.out.println(message);


                    socketS.close();
                }
            } catch (IOException e) {
                setPort(++port);
            }
        }

    }
}
