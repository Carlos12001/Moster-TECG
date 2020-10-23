package model.sockets;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.MonsterTECGApp;
import model.game.Game;

import javax.sql.rowset.serial.SerialArray;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 *
 */
public class Server {

    /**
     *
     */
    private ServerSocket server;

    /**
     *
     */
    private int port = 1024;

    /**
     *
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

    public void readSockect() {
        ClassReadServer InfoIN = new ClassReadServer(this.server);
        Thread hilo = new Thread(InfoIN);
        hilo.start();
    }

    private class ClassReadServer extends Thread{

        private ServerSocket socketChlid;

        private ClassReadServer(ServerSocket socketNew){
            this.socketChlid = socketNew;
        }


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

                    System.out.println(Info);

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

}
