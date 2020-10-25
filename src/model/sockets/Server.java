package model.sockets;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.DataOutputAsStream;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.GameController;
import controller.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import main.MonsterTECGApp;
import model.game.Game;

import javax.sql.rowset.serial.SerialArray;
import javax.sql.rowset.spi.SyncFactory;
import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


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
                MonsterTECGApp.logger.error(e.getMessage());
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
            MonsterTECGApp.logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * This method connect to the client and send the jackson
     * @param jacksonStr New jackson String
     */

    public void writeSocket(String jacksonStr){

        try {
            DataOutputStream serverOutD = new DataOutputStream(this.server.accept().getOutputStream());

            serverOutD.writeUTF(jacksonStr);

            serverOutD.close();

        } catch (IOException e) {
            MonsterTECGApp.logger.error(e.getMessage());
        }

    }

    /**
     *  This method read the message that client sends
     */
    public void readSockect() {
        ClassReadServer InfoIN = new ClassReadServer(this.server);
        Thread hilo = new Thread(InfoIN);
        hilo.start();
    }

    /**
     *  This private class is a thread that listen incoming messages
     */
    private class ClassReadServer extends Thread{

        private ServerSocket socketChlid;

        /**
         * This is the constructor
         * @param socketNew New ServerSocket
         */
        private ClassReadServer(ServerSocket socketNew){
            this.socketChlid = socketNew;
        }


        /**
         * This is a funtion from the Thread
         */
        @Override
        public void run() {

            ObjectMapper mapper = new ObjectMapper();

            while (true) {
                System.out.println("Listening Servidor");
                try {

                    Socket socketS = this.socketChlid.accept();

                    DataInputStream serverInD = new DataInputStream(socketS.getInputStream());

                    String message = serverInD.readUTF();

                    UpdateInfo Info = mapper.readValue(message, UpdateInfo.class);

                    Game.getInstance().setUpdateInfo(Info);

                    serverInD.close();


                    break;
                } catch (JsonMappingException e) {
                    MonsterTECGApp.logger.error(e.getMessage());
                } catch (JsonProcessingException e) {
                    MonsterTECGApp.logger.error(e.getMessage());
                } catch (IOException e) {
                    MonsterTECGApp.logger.error(e.getMessage());
                }
            }

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
}
