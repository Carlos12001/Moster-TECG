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
import java.net.UnknownHostException;

/**
 * This class
 */
public class Client extends Thread {
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
    private Socket clientSocket;
    private DataInputStream inputSocketInD;
    private DataOutputStream clientOutD;

    /**
     * @param newPort
     * @param newIPserver
     */
    public Client(int newPort, String newIPserver) {
        this.port = newPort;
        this.IPserver = newIPserver;
        try {
            this.clientSocket = new Socket(this.IPserver, this.port);
            this.inputSocketInD = new DataInputStream(this.clientSocket.getInputStream());
            this.clientOutD = new DataOutputStream(this.clientSocket.getOutputStream());
        } catch (UnknownHostException e) {
           closeConexion();
        } catch (IOException e) {
            closeConexion();
        }
    }


    /**
     * This method returns the port number
     *
     * @return Value of port
     */
    public int getPort() {
        return this.port;
    }

    /**
     * This method returns the IP of the server
     *
     * @return value of IPserver
     */
    public String getIPserver() {
        return this.IPserver;
    }

    /**
     * This method connect to the server and send the jackson
     *
     * @param jacksonStr New jackson String
     */
    public void writeSocket(String jacksonStr) {
        try {
            this.clientOutD.writeUTF(jacksonStr);
            this.clientSocket.setKeepAlive(true);
        } catch (IOException ioException) {
            closeConexion();
        }
    }

    /**
     *
     */
    public void readSockect() {
        ObjectMapper mapper = new ObjectMapper();
        Thread thread = new Thread(() -> {
            try {
                String message = this.inputSocketInD.readUTF();

                UpdateInfo Info = mapper.readValue(message, UpdateInfo.class);

                Game.getInstance().setUpdateInfo(Info);

                this.clientSocket.setKeepAlive(true);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                closeConexion();
            } catch (IOException e) {
                closeConexion();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void closeConexion(){
        try {
            this.clientSocket.close();
        } catch (IOException ex) {
        }
    }
}

