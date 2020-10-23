package model.game;

import model.sockets.Server;
import model.sockets.Client;
import model.sockets.UpdateInfo;

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

    private Client client;

    private Server server;

    private UpdateInfo updateInfo;

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

    /**
     * Sets new round.
     *
     */
    public void setRound() {
        this.round ++;
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

    public Server getServer(){
        if(this.typeConexion== ConnectionType.SERVER){
            return this.server;
        }else {
            return null;
        }
    }

    public Client getClient() {
        if(this.typeConexion == ConnectionType.CLIENT) {
            return this.client;
        }else{
            return null;
        }
    }

    public void createConnection(int port, String ip ){
        if ((this.client==null)&(this.typeConexion== ConnectionType.CLIENT)){
            this.client = new Client(port, ip);
           this.client.connectToServer("Estoy conectado");
        }
    }

    public void createConnection(){
        if ((this.server==null)&(this.typeConexion == ConnectionType.SERVER)){
            this.server = new Server();
            Thread serverThread = new Thread(this.server);
            serverThread.start();
        }
    }

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

    public static Game getInstance() {
        return instance;
    }

}
