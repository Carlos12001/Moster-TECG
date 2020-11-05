package model.game;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.cards.*;
import model.deckcard.DeckStack;
import model.handcard.HandCardList;
import model.history.HistoryList;
import model.sockets.*;

import java.io.*;
import java.util.Random;

import static model.sockets.ConnectionType.CLIENT;
import static model.sockets.ConnectionType.SERVER;

public class Game {

    /**
     * This is the instance of the game.
     */
    private static volatile Game instance;

    /**
     * This is the instance of the player.
     */
    private final Player player;

    /**
     * This attribute indicates the type of the connection.
     */
    private ConnectionType typeConexion;

    /**
     * This attribute indicates who is the fist playing.
     */
    private ConnectionType whoFisrt;

    /**
     * This String contains the name of the opponent.
     */
    private String playerOtherName;

    /**
     * This is the mana of the opponent.
     */
    private int playerOtherMana;

    /**
     * This is the life of the opponent.
     */
    private int playerOtherLife;

    /**
     * This is the number of the round.
     */
    private short round = 1;

    /**
     * Here is the code of the opponets card.
     */
    private String codeOtherCard;

    /**
     * This is the instance of the client.
     */
    private Client client;

    /**
     * This instance is the server.
     */
    private Server server;

    /**
     * Here is the newest info that is sent.
     */
    private UpdateInfo updateInfo;

    /**
     * Here is created the deck of cards.
     */
    private DeckStack deckStack = new DeckStack((short)16);

    /**
     * Here is created the hand card list.
     */
    private HandCardList handCardList = new HandCardList();

    /**
     * Here is created the history list.
     */
    private HistoryList historyList = new HistoryList();

    /**
     * This is the constructor
     *
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
    public String getCodeOtherCard() {
        return codeOtherCard;
    }

    /**
     * Sets new cartTableOtherPlayer.
     *
     * @param codeOtherCard New value of cartTableOtherPlayer.
     */
    public void setCodeOtherCard(String codeOtherCard) {
        this.codeOtherCard = codeOtherCard;
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
     * Gets playerOtherMana.
     *
     * @return Value of playerOtherMana.
     */
    public int getPlayerOtherMana() {
        return playerOtherMana;
    }

    /**
     * Sets new round.
     */
    public void setRound() {
        ++this.round;
    }

    /**
     * Sets new typeConexion.
     *
     * @param typeConexion New value of typeConexion.
     */
    public void setTypeConexion(ConnectionType typeConexion) {
        this.typeConexion = typeConexion;
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
     * This method return the server class.
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
     * This method returns the client class.
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
     * This method returns the info.
     *
     * @return info
     */
    public UpdateInfo getUpdateInfo() {
        return updateInfo;
    }

    /**
     * This method sets the new information.
     *
     * @param updateInfo New Json
     */
    public void setUpdateInfo(UpdateInfo updateInfo) {
        this.updateInfo = updateInfo;
        this.round = updateInfo.getRound();
        this.playerOtherLife = updateInfo.getPlayerSendLife();
        this.playerOtherMana = updateInfo.getPlayerSendMana();
        this.playerOtherName = updateInfo.getPlayerSendName();
        this.codeOtherCard = updateInfo.getCodeSendCart();
        this.whoFisrt = updateInfo.getWhoFirst();

    }

    /**
     * Gets deckStack.
     *
     * @return Value of deckStack.
     */
    public DeckStack getDeckStack() {
        return deckStack;
    }

    /**
     * Gets the history list.
     *
     * @return Value of Here is created the history list..
     */
    public HistoryList getHistoryList() {
        return historyList;
    }

    /**
     * Gets Here is created the hand card list..
     *
     * @return Value of Here is created the hand card list..
     */
    public HandCardList getHandCardList() {
        return handCardList;
    }

    /**
     * Excuete the client.
     *
     * @param port New int
     * @param ip   New String
     */
    public void createConnection(int port, String ip) {
        if ((this.client == null) & (this.typeConexion == CLIENT)) {
            try {
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
            }catch (Exception e){
                this.client =null;
                this.typeConexion = null;
            }

        }
    }

    /**
     * This method creates the server if the conection type is SERVER.
     */
    public void createConnection() {
        if ((this.server == null) & (this.typeConexion == SERVER)) {
            this.server = new Server();
            this.server.readSockect();
        }
    }

    /**
     * This method sends the information to the opponent.
     *
     * @param skipTurn boolean
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
     * This method sends the information of the player to the opponent.
     *
     * @param codeCart String code of the card
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

    /**
     * This method reads the information from the socket.
     */
    public void recibeNewInfo() {
        switch (this.getTypeConexion()) {
            case SERVER -> this.server.readSockect();
            case CLIENT -> this.client.readSockect();
            default -> throw new IllegalStateException("Unexpected value: " + this.getTypeConexion());
        }
    }

    /**
     * this method reads the jackson and generates a JSON.
     *
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
     * This method creates the full deck stack, with random cards.
     */
    public void initDeckCard() {
        ObjectMapper mapper = new ObjectMapper();
        File jackson;
        jackson = new File(System.getProperty("user.dir") + "/src/data/Cards.json");
        Card[][] arrayCard;

        try {
            arrayCard = mapper.readValue(jackson, Card[][].class);

            Random rnd = new Random();
            for (int i = 0; i < this.getDeckStack().getMaxSize(); i++) {

                int num1 = rnd.nextInt(3);

                int num2 = rnd.nextInt(10);

                switch (num1) {
                    case 0-> {
                        Card newCardH = new Hencheman(arrayCard[num1][num2]);
                        this.deckStack.push(newCardH);
                    }
                    case 1 -> {
                        Secret newCardS = new Secret(arrayCard[num1][num2]);
                        this.deckStack.push(newCardS);
                    }
                    case 2 -> {
                        Spell newCardSP = new Spell(arrayCard[num1][num2]);
                        this.deckStack.push(newCardSP);
                    }
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
     * This method creates the first hand card with four cards. The cards are randomly selected.
     */
    public void initHandCard() {
        ObjectMapper mapper = new ObjectMapper();
        File jackson;
        jackson = new File(System.getProperty("user.dir") + "/src/data/Cards.json");
        Card[][] arrayCard;

        try {
            arrayCard = mapper.readValue(jackson, Card[][].class);

            Random rnd = new Random();
            for (int i = 0; i <= 3; i++) {

                int num1 = rnd.nextInt(3);

                int num2 = rnd.nextInt(10);

                switch (num1) {
                    case 0-> {
                        Card newCardH = new Hencheman(arrayCard[num1][num2]);
                        this.handCardList.insertLast(newCardH);
                    }
                    case 1 -> {
                        Secret newCardS = new Secret(arrayCard[num1][num2]);
                        this.handCardList.insertLast(newCardS);
                    }
                    case 2 -> {
                        Spell newCardSP = new Spell(arrayCard[num1][num2]);
                        this.handCardList.insertLast(newCardSP);
                    }
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
     * This method ends the connection between the server and the client.
     *
     * @throws IllegalStateException when it has a bad connection type.
     */
    public void finishConexion () throws IllegalStateException{

        switch (this.getTypeConexion()) {
            case SERVER -> this.server.closeConexion();
            case CLIENT -> this.client.closeConexion();
            default -> throw new IllegalStateException("Unexpected value: " + this.getTypeConexion());
        }
    }

    /**
     * This is the Singleton, if the instance is created the it won't create a new instance.
     *
     * @param player Player object.
     * @param typeConexion Type of connection this game has.
     * @return instance game instance.
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

    /**
     * Return the game instance.
     *
     * @return Game instance
     */
    public static Game getInstance() {
        return instance;
    }

}
