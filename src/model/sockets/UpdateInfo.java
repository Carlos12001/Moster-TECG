package model.sockets;

import model.game.ConnectionType;

public class UpdateInfo {

    /**
     *Player name who send the  information.
     */
    private String playerSendName;

    /**
     *PLayer name who start.
     */
    private ConnectionType whoFirst;

    /**
     *Player mana who send the information.
     */
    private int playerSendMana;

    /**
     *Player life who send the information.
     */
    private int playerSendLife;

    /**
     *Cart type to sent the other player.
     */
    private byte codeSendCart;

    /**
     *The round in the game.
     */
    private byte round;

    /**
     *Says if the other player skips the turn.
     */
    private boolean skipTurn;

    public UpdateInfo(String playerSendName, ConnectionType whoFirst){
        this.playerSendName = playerSendName;
        this.whoFirst = whoFirst;
        this.skipTurn = false;
        this.round = 1;
    }

    public byte getCodeSendCart() {
        return codeSendCart;
    }

    public void setCodeSendCart(byte codeSendCart) {
        this.codeSendCart = codeSendCart;
    }

    public byte getRound() {
        return round;
    }

    public void setRound(byte round) {
        this.round = round;
    }

    public boolean isSkipTurn() {
        return skipTurn;
    }

    public void setSkipTurn(boolean skipTurn) {
        this.skipTurn = skipTurn;
    }

    public String getPlayerSendName() {
        return playerSendName;
    }

    public void setPlayerSendName(String playerSendName) {
        this.playerSendName = playerSendName;
    }

    public ConnectionType getWhoFirst() {
        return whoFirst;
    }

    public void setWhoFirst(ConnectionType whoFirst) {
        this.whoFirst = whoFirst;
    }

    public int getPlayerSendMana() {
        return playerSendMana;
    }

    public void setPlayerSendMana(int playerSendMana) {
        this.playerSendMana = playerSendMana;
    }

    public int getPlayerSendLife() {
        return playerSendLife;
    }

    public void setPlayerSendLife(int playerSendLife) {
        this.playerSendLife = playerSendLife;
    }
}

