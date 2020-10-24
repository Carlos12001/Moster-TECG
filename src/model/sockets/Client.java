package model.sockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.MonsterTECGApp;
import model.game.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This class send the information through the socket
 * @Param newPort New value of newPort
 */
public class Client extends Thread{
    private int port;
    private String IPserver;
    private  Socket clientSocket;

    public Client(int newPort, String newIPserver) {
        this.port = newPort;
        this.IPserver = newIPserver;
        try {
            this.clientSocket= new Socket(this.IPserver, this.port);
        } catch (IOException e) {
            MonsterTECGApp.logger.error(e.getMessage());
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

        try {
            DataOutputStream clientOutD = new DataOutputStream(this.clientSocket.getOutputStream());

            System.out.println(jacksonStr);

            clientOutD.writeUTF(jacksonStr);

            clientOutD.close();

        } catch (IOException ioException) {
            MonsterTECGApp.logger.error(ioException);
        }
    }

    private void readSockect() {
        ClassReadClient infoIN = new ClassReadClient(this.clientSocket);
        Thread hilo = new Thread(infoIN);
        hilo.start();
    }

    private class ClassReadClient extends Thread{

        private  Socket socket;

        private ClassReadClient(Socket socket) {
            this.socket = socket;
        }


        @Override
        public void run() {
            ObjectMapper mapper = new ObjectMapper();

            while (true) {
                System.out.println("Listening cliente");
                try {

                    DataInputStream serverInD = new DataInputStream(this.socket.getInputStream());

                    String message = serverInD.readUTF();

                    UpdateInfo Info = mapper.readValue(message, UpdateInfo.class);

                    Game.getInstance().setUpdateInfo(Info);

                    serverInD.close();
                    socket.setKeepAlive(true);

                    this.join();
                    break;
                } catch (JsonMappingException e) {
                    MonsterTECGApp.logger.error(e.getMessage());
                } catch (JsonProcessingException e) {
                    MonsterTECGApp.logger.error(e.getMessage());
                } catch (IOException e) {
                    MonsterTECGApp.logger.error(e.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

