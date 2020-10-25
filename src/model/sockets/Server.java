package model.sockets;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.game.Game;
import java.io.*;
import java.net.*;


/**
 * This class handles the server connection
 */
public class Server {

    private static boolean firstConexion= false;

    /**
     *
     */
    private ServerSocket server;

    /**
     *
     */
    private int port = 1024;

    private Socket socketClient;

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
                e.getMessage();
            }
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
     * This method set the port number
     *
     * @param num New value of port
     */
    private void setPort(int num) {
        this.port = num;
    }

    /**
     * This method returns the IP
     *
     * @return Ip number
     */
    public String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * This method connect to the client and send the jackson
     * @param jacksonStr New jackson String
     */

    public void writeSocket(String jacksonStr) {
        System.out.println("\n\n--Write Servidor--\n\n");

        try {
            DataOutputStream serverOutD = new DataOutputStream(this.socketClient.getOutputStream());

            serverOutD.writeUTF(jacksonStr);

            this.socketClient.setKeepAlive(true);
        } catch (IOException e) {
            e.getMessage();
        }

    }

    /**
     *  This method read the message that client sends
     */
    public void readSockect() {
        if(this.socketClient==null) {
            ClassReadServer InfoIN = new ClassReadServer(this.server);
            Thread hilo = new Thread(InfoIN);
            hilo.start();
        }else{
            readSocketAux();
        }
    }

    /**
     *
     */
    private void readSocketAux(){

        System.out.println("\n\n--Listening Servidor--\n\n");

        ObjectMapper mapper = new ObjectMapper();

        try {

            DataInputStream serverInD = new DataInputStream(this.socketClient.getInputStream());

            String message = serverInD.readUTF();

            UpdateInfo Info = mapper.readValue(message, UpdateInfo.class);

            Game.getInstance().setUpdateInfo(Info);

            this.socketClient.setKeepAlive(true);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets server.
     *
     * @return Value of server.
     */
    public ServerSocket getServer() {
        return server;
    }

    /**
     *  This private class is a thread that listen incoming messages
     */
    private class ClassReadServer extends Thread {

        private ServerSocket serverChlid;

        /**
         * This is the constructor
         *
         * @param socketNew New ServerSocket
         */
        private ClassReadServer(ServerSocket socketNew) {
            this.serverChlid = socketNew;
        }

        /**
         * This is a funtion from the Thread
         */
        @Override
        public void run() {
            while (true) {
                System.out.println("\n\n--Listening Servidor--\n\n");
                try {
                    socketClient = this.serverChlid.accept();
                    readSocketAux();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
