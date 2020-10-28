package model.game;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.cards.*;
import model.deckcard.DeckStack;
import model.handcard.HandCardList;
import model.sockets.*;

import java.io.*;
import java.util.Random;

import static model.sockets.ConnectionType.CLIENT;
import static model.sockets.ConnectionType.SERVER;

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
    private short round = 1;
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

    private DeckStack deckStack = new DeckStack();

    private HandCardList handCardList = new HandCardList();


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
    public short getRound() {
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
     * Sets new round
     */
    public void setRound() {
        ++this.round;
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
    public Server getServer() {
        if (this.typeConexion == SERVER) {
            return this.server;
        } else {
            return null;
        }
    }

    /**
     * This method returns the client class
     *
     * @return client class
     */
    public Client getClient() {
        if (this.typeConexion == CLIENT) {
            return this.client;
        } else {
            return null;
        }
    }

    /**
     * This method returns the info
     *
     * @return info
     */
    public UpdateInfo getUpdateInfo() {
        return updateInfo;
    }

    /**
     * This method sets the new information
     *
     * @param updateInfo New Json
     */
    public void setUpdateInfo(UpdateInfo updateInfo) {
        this.updateInfo = updateInfo;
        this.round = updateInfo.getRound();
        this.playerOtherLife = updateInfo.getPlayerSendLife();
        this.playerOtherMana = updateInfo.getPlayerSendMana();
        this.playerOtherName = updateInfo.getPlayerSendName();
        this.cartTableOtherPlayer = updateInfo.getCodeSendCart();
        this.whoFisrt = updateInfo.getWhoFirst();
    }

    /**
     * Excuete the client
     *
     * @param port New int
     * @param ip   New String
     */
    public void createConnection(int port, String ip) {
        if ((this.client == null) & (this.typeConexion == CLIENT)) {
            this.client = new Client(port, ip);

            //Choice the random
            Random rnd = new Random();
            int ramdomNum = (int) (rnd.nextDouble() * 2 + 1);
            switch (ramdomNum) {
                case 1 -> this.whoFisrt = SERVER;
                case 2 -> this.whoFisrt = CLIENT;
                default -> throw new IllegalStateException("Unexpected value: " + ramdomNum);
            }

            this.updateInfo = new UpdateInfo(this.player.getName(), this.whoFisrt);
            this.updateInfo.setPlayerSendMana(this.player.getMana());
            this.updateInfo.setPlayerSendLife(this.player.getLife());
            this.updateInfo.setCodeSendCart("");
            this.client.writeSocket(this.generateJackson());
        }
    }

    /**
     * TH
     */
    public void createConnection() {
        if ((this.server == null) & (this.typeConexion == SERVER)) {
            this.server = new Server();
            this.server.readSockect();
        }
    }

    /**
     * @param skipTurn
     */
    public void sendInfoOtherPlayer(boolean skipTurn) {
        this.updateInfo.setPlayerSendName(player.getName());
        this.updateInfo.setPlayerSendLife(player.getLife());
        this.updateInfo.setPlayerSendMana(player.getMana());
        this.updateInfo.setRound(this.round);
        this.updateInfo.setCodeSendCart("");
        this.updateInfo.setSkipTurn(true);
        switch (this.getTypeConexion()) {
            case SERVER -> this.server.writeSocket(this.generateJackson());
            case CLIENT -> this.client.writeSocket(this.generateJackson());
            default -> throw new IllegalStateException("Unexpected value: " + this.getTypeConexion());
        }
    }

    /**
     * @param codeCart
     */
    public void sendInfoOtherPlayer(String codeCart) {
        this.updateInfo.setPlayerSendName(player.getName());
        this.updateInfo.setPlayerSendLife(player.getLife());
        this.updateInfo.setPlayerSendMana(player.getMana());
        this.updateInfo.setRound(this.round);
        this.updateInfo.setCodeSendCart(codeCart);
        switch (this.getTypeConexion()) {
            case SERVER -> this.server.writeSocket(this.generateJackson());
            case CLIENT -> this.client.writeSocket(this.generateJackson());
            default -> throw new IllegalStateException("Unexpected value: " + this.getTypeConexion());
        }
    }

    public void recibeNewInfo() {
        switch (this.getTypeConexion()) {
            case SERVER -> this.server.readSockect();
            case CLIENT -> this.client.readSockect();
            default -> throw new IllegalStateException("Unexpected value: " + this.getTypeConexion());
        }
    }

    /**
     * @return jsonRead
     */
    private String generateJackson() {
        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper mapper = new ObjectMapper();
        File jackson;
        jackson = new File(System.getProperty("user.dir") + "/src/data/Update.json");
        try {
            mapper.writeValue(jackson, this.updateInfo);

            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/data/Update" + ".json"));
            StringBuilder jsonRead = new StringBuilder();
            String linea;

            //Lee linea por linea el archivo
            while ((linea = br.readLine()) != null)
                jsonRead.append(linea);

            return jsonRead.toString();
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
     * @return instance
     */
    public static Game getInstance(Player player, ConnectionType typeConexion) {

        Game result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Game.class) {
            if (instance == null) {
                instance = new Game(player, typeConexion);
            }
            return instance;
        }
    }

    /**
     * Return the game instance.
     *
     * @return Game instance
     */
    public static Game getInstance() {
        return instance;
    }

    public HandCardList getHandCardList() {
        return handCardList;
    }

    public void first4cards() {
        ObjectMapper mapper = new ObjectMapper();
        File jackson;
        jackson = new File(System.getProperty("user.dir") + "/src/data/Cards.json");
        Card[][] arrayCard;

        try {
            arrayCard = mapper.readValue(jackson, Card[][].class);

            for (int i = 0; i < 3; i++) {

                switch (arrayCard[i][0].getCategory()) {
                    case "HENCHEMAN" -> {
                        Card newCardH = new Henchman(arrayCard[i][0].getCode());
                        this.handCardList.insertLast(newCardH);
                        break;
                    case "SECRET":
                        Secret newCardS = new Secret(arrayCard[i][0].getCode());
                        this.handCardList.insertLast(newCardS);
                        break;
                    case "SPELL":
                        Spell newCardSP = new Spell(arrayCard[i][0].getCode());
                        this.handCardList.insertLast(newCardSP);
                        break;
                }
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param player
     * @param typeConexion
     * @return instance
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
