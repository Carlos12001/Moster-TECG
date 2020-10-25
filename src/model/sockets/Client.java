package model.sockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.game.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * This class
 */
public class Client extends Thread{
    /**
     *
     */
    private int port;
    /**
     *
     */
    private String IPserver;
    /**
     *
     */
    private  Socket clientSocket;

    /**
     * @param newPort
     * @param newIPserver
     */
    public Client(int newPort, String newIPserver) {
        this.port = newPort;
        this.IPserver = newIPserver;
        try {
            this.clientSocket= new Socket(this.IPserver, this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * This method returns the port number
     * @return Value of port
     */
    public int getPort(){
        return this.port;
    }

    /**
     * This method returns the IP of the server
     * @return value of IPserver
     */
    public String getIPserver(){
        return this.IPserver;
    }

    /**
     * This method connect to the server and send the jackson
     * @param jacksonStr New jackson String
     */
    public void writeSocket(String jacksonStr) {
        System.out.println("\n\n--Write cliente--\n\n");


        try {
            DataOutputStream clientOutD = new DataOutputStream(this.clientSocket.getOutputStream());

            clientOutD.writeUTF(jacksonStr);

            this.clientSocket.setKeepAlive(true);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     *
     */
    public void readSockect() {
        ObjectMapper mapper = new ObjectMapper();

        System.out.println("\n\n--Listening cliente--\n\n");

        DataInputStream inputSocketInD;
        try {
            inputSocketInD = new DataInputStream(this.clientSocket.getInputStream());

            String message = inputSocketInD.readUTF();

            UpdateInfo Info = mapper.readValue(message, UpdateInfo.class);

            Game.getInstance().setUpdateInfo(Info);

            this.clientSocket.setKeepAlive(true);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

