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
 * This class handles the client connection.
 */
public class Client  {
    /**
     *  This is the port number, which begins at 1024.
     */
    private int port;

    /**
     * String containing the IP of the computer that the client is going connect.
     */
    private String IPserver;

    /**
     * This is the socket that is used to transfer information between computers.
     */
    private Socket clientSocket;

    /**
     * This lets the aplication read primitive Java data types.
     */
    private DataInputStream inputSocketInD;

    /**
     * This lets tje application write primitive Java data type.
     */
    private DataOutputStream clientOutD;

    /**
     * This is the constructor, it finds the server and connects to it.
     *
     * @param newPort int.
     * @param newIPserver String.
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
        } catch (Exception e) {
            closeConexion();
        }
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
     * This method returns the IP of the server.
     *
     * @return value of IPserver.
     */
    public String getIPserver() {
        return this.IPserver;
    }

    /**
     * This method connect to the server and send the jackson.
     *
     * @param jacksonStr New jackson String.
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
     * This method waits for the server and if there is one server it read the information and update the
     * information.
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

    /**
     * This method closes the connection between the server and the client.
     */
    public void closeConexion(){
        try {
            this.clientSocket.close();
        } catch (IOException ignored) {
        }
    }
}

