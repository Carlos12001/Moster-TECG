package model.sockets;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.game.Game;
import java.io.*;
import java.net.*;


/**
 * This class handles the server connection.
 */
public class Server {

    /**
     * This socket waits for a connection in the network
     */
    private ServerSocket server;

    /**
     * This is the port number, which begins at 1024.
     */
    private int port = 1024;

    /**
     * This is the socket that is trying to connect to the server
     */
    private Socket socketClient;

    /**
     * This lets the aplication read primitive Java data types.
     */
    private DataInputStream serverInD;
    /**
     * This lets tje application write primitive Java data type.
     */
    private DataOutputStream serverOutD;

    /**
     * This is the constructor, it finds the a free port and creates the ServerSocket
     */
    public Server() {
        boolean alive = true;

        while (alive) {
            try {
                server = new ServerSocket(getPort());
                alive = false;

            } catch (IOException e) {
                setPort(++this.port);
            }
        }
    }


    /**
     * This method connect to the client and send the jackson.
     *
     * @param jacksonStr New jackson String.
     */
    public void writeSocket(String jacksonStr) {
        try {

            serverOutD.writeUTF(jacksonStr);

            this.socketClient.setKeepAlive(true);
        } catch (IOException e) {
            closeConexion();
        }
    }

    /**
     *  This method waits for an client and if there is one client it read the information with an auxliar method.
     */
    public void readSockect() {
        if(this.socketClient==null) {
            this.waitJoinClient();
        }else{
            readSocketAux();
        }
    }

    /**
     * This method reads the information that is passed through the socket.
     */
    private void readSocketAux(){
        ObjectMapper mapper = new ObjectMapper();

        Thread thread = new Thread(()-> {
            try {

                String message = this.serverInD.readUTF();

                UpdateInfo Info = mapper.readValue(message, UpdateInfo.class);

                Game.getInstance().setUpdateInfo(Info);

                this.socketClient.setKeepAlive(true);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                closeConexion();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * This method returns the port number.
     *
     * @return Value of port.
     */
    public int getPort() {
        return this.port;
    }

    /**
     * This method set the port number.
     *
     * @param num New value of port.
     */
    private void setPort(int num) {
        this.port = num;
    }

    /**
     * This method returns the IP.
     *
     * @return Ip number.
     */
    public String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  This method is a thread listening the first client.
     */
    private void waitJoinClient() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    socketClient = server.accept();
                    this.serverInD = new DataInputStream(this.socketClient.getInputStream());
                    this.serverOutD = new DataOutputStream(this.socketClient.getOutputStream());
                    readSocketAux();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * This method ends the connection between the client and the server.
     */
    public void closeConexion(){
        try {
            this.server.close();
            this.socketClient.close();
        } catch (IOException ignored) {
        }
    }

}
