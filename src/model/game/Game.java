package model.game;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.sockets.Server;
import model.sockets.Client;
import model.sockets.UpdateInfo;

import java.io.*;
import java.util.Random;

public class Game {

    /**
     *
     */
    private static volatile Game instance;
    /**
     *
     */
    private final Player player;
    /**
     *
     */
    private final ConnectionType typeConexion;
    /**
     *
     */
    private ConnectionType whoFisrt;
    /**
     *
     */
    private String playerOtherName;
    /**
     *
     */
    private int playerOtherMana;
    /**
     *
     */
    private int playerOtherLife;
    /**
     *
     */
    private byte round;
    /**
     *
     */
    private String cartTableOtherPlayer;
    /**
     *
     */
    private String cartTablePlayer;

    /**
     *
     */
    private Client client;

    /**
     *
     */
    private Server server;

    /**
     *
     */
    private UpdateInfo updateInfo;

    /**
     * This is the constructor
     * @param player New player object
     * @param typeConexion New connectionType
     */
    private Game(Player player, ConnectionType typeConexion) {
        this.player = player;
        this.typeConexion = typeConexion;
        this.updateInfo = new UpdateInfo(this.player.getName(), typeConexion);
    }


    /**
     * Sets new whoFisrt.
     *
     * @param whoFisrt New value of whoFisrt.
     */
    public void setWhoFisrt(ConnectionType whoFisrt) {
        this.whoFisrt = whoFisrt;
    }

    /**
     * Gets cartTableOtherPlayer.
     *
     * @return Value of cartTableOtherPlayer.
     */
    public String getCartTableOtherPlayer() {
        return cartTableOtherPlayer;
    }

    /**
     * Sets new cartTableOtherPlayer.
     *
     * @param cartTableOtherPlayer New value of cartTableOtherPlayer.
     */
    public void setCartTableOtherPlayer(String cartTableOtherPlayer) {
        this.cartTableOtherPlayer = cartTableOtherPlayer;
    }

    /**
     * Gets playerOtherLife.
     *
     * @return Value of playerOtherLife.
     */
    public int getPlayerOtherLife() {
        return playerOtherLife;
    }

    /**
     * Sets new playerOtherName.
     *
     * @param playerOtherName New value of playerOtherName.
     */
    public void setPlayerOtherName(String playerOtherName) {
        this.playerOtherName = playerOtherName;
    }

    /**
     * Gets playerOtherName.
     *
     * @return Value of playerOtherName.
     */
    public String getPlayerOtherName() {
        return playerOtherName;
    }

    /**
     * Gets round.
     *
     * @return Value of round.
     */
    public byte getRound() {
        return round;
    }

    /**
     * Sets new playerOtherLife.
     *
     * @param playerOtherLife New value of playerOtherLife.
     */
    public void setPlayerOtherLife(int playerOtherLife) {
        this.playerOtherLife = playerOtherLife;
    }

    /**
     * Sets new playerOtherMana.
     *
     * @param playerOtherMana New value of playerOtherMana.
     */
    public void setPlayerOtherMana(int playerOtherMana) {
        this.playerOtherMana = playerOtherMana;
    }

    /**
     * Gets whoFisrt.
     *
     * @return Value of whoFisrt.
     */
    public ConnectionType getWhoFisrt() {
        return whoFisrt;
    }

    /**
     * Sets new cartTablePlayer.
     *
     * @param cartTablePlayer New value of cartTablePlayer.
     */
    public void setCartTablePlayer(String cartTablePlayer) {
        this.cartTablePlayer = cartTablePlayer;
    }

    /**
     * Gets playerOtherMana.
     *
     * @return Value of playerOtherMana.
     */
    public int getPlayerOtherMana() {
        return playerOtherMana;
    }

    /**
     * Gets cartTablePlayer.
     *
     * @return Value of cartTablePlayer.
     */
    public String getCartTablePlayer() {
        return cartTablePlayer;
    }

    /** Sets new round
     * @param round
     */
    public void setRound(byte round) {
        this.round = round;
    }

    /**
     * Gets typeConexion.
     *
     * @return Value of typeConexion.
     */
    public ConnectionType getTypeConexion() {
        return typeConexion;
    }

    /**
     * Gets player.
     *
     * @return Value of player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * This method return the server class
     * @return server class
     */
    public Server getServer(){
        if(this.typeConexion== ConnectionType.SERVER){
            return this.server;
        }else {
            return null;
        }
    }

    /**
     * This method returns the client class
     * @return client class
     */
    public Client getClient() {
        if(this.typeConexion == ConnectionType.CLIENT) {
            return this.client;
        }else{
            return null;
        }
    }

    /**
     * This method returns the info
     * @return info
     */
    public UpdateInfo getUpdateInfo() {
        return updateInfo;
    }

    /**
     * This method sets the new information
     * @param updateInfo New Json
     */
    public void setUpdateInfo(UpdateInfo updateInfo){
        this.updateInfo = updateInfo;

    }

    /**Excuete the client
     * @param port New int
     * @param ip New String
     */
    public void createConnection(int port, String ip ){
        if ((this.client==null)&(this.typeConexion== ConnectionType.CLIENT)){
            this.client = new Client(port, ip);
            Random rnd = new Random();
            int ramdomNum = (int) (rnd.nextDouble() * 2 + 1);

            switch (ramdomNum){
                case 1:
                   this.whoFisrt = ConnectionType.SERVER;
                    break;
                case 2:
                    this.whoFisrt = ConnectionType.CLIENT;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + ramdomNum);
            }

            this.updateInfo = new UpdateInfo(this.player.getName(), this.whoFisrt);
            this.updateInfo.setPlayerSendMana(this.player.getMana());
            this.updateInfo.setPlayerSendLife(this.player.getLife());
            this.updateInfo.setCodeSendCart((short) 0);
            this.client.writeSocket(this.generateJackson());
        }
    }

    /**
     * TH
     */
    public void createConnection(){
        if ((this.server==null)&(this.typeConexion == ConnectionType.SERVER)){
            this.server = new Server();
            this.server.readSockect();
        }
    }

    /**
     * @return jsonRead
     */
    private String generateJackson() {
        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper mapper = new ObjectMapper();
        File jackson ;
        jackson =  new File(System.getProperty("user.dir")+"/src/data/Update.json");
        try {
            mapper.writeValue(jackson, this.updateInfo);

            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/data/Update.json"));
            String jsonRead = new String();
            String linea = "";


            //Lee linea por linea el archivo
            while ((linea = br.readLine()) != null) {
                jsonRead += linea;
            }

            return jsonRead;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param player
     * @param typeConexion
     * @return
     */
    public static Game getInstance(Player player, ConnectionType typeConexion) {

        Game result = instance;
        if (result != null) {
            return result;
        }
        synchronized(Game.class) {
            if (instance == null) {
                instance = new Game(player, typeConexion);
            }
            return instance;
        }
    }

    /** Return the game instance.
     * @return Game instance
     */
    public static Game getInstance() {
        return instance;
    }


}
