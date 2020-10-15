package main;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



/**
 *
 */
public class createServer implements Runnable{
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
                //portLabel.setText("Su puerto es: " + String.valueOf(port));
                portAlive = false;

                while (true) {
                    System.out.println("Listening");
                    Socket socketS = server.accept();

                    DataInputStream serverInD = new DataInputStream(socketS.getInputStream());

                    String message = serverInD.readUTF();

                    System.out.println(message);

                    //textArea.append(message + "\n");

                    //socketS.close();
                }
            } catch (IOException e) {
                setPort(++port);
            }
        }

    }
}
