package model.sockets;

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
    private String codeSendCart;

    /**
     *The round in the game.
     */
    private short round = 1;

    /**
     *Says if the other player skips the turn.
     */
    private boolean skipTurn;

    /**
     *
     */
    public UpdateInfo(){}

    /**
     * Sets new Playername and new whoFirst who send the  information..
     * @param playerSendName  New value of Player name who send the  information..
     * @param whoFirst New value of PLayer name who start..
     */
    public UpdateInfo(String playerSendName, ConnectionType whoFirst){
        this.playerSendName = playerSendName;
        this.whoFirst = whoFirst;
    }


    /**
     * Sets new Cart type to sent the other player..
     *
     * @param codeSendCart New value of Cart type to sent the other player..
     */
    public void setCodeSendCart(String codeSendCart) {
        this.codeSendCart = codeSendCart;
    }

    /**
     * Sets new Player life who send the information..
     *
     * @param playerSendLife New value of Player life who send the information..
     */
    public void setPlayerSendLife(int playerSendLife) {
        this.playerSendLife = playerSendLife;
    }

    /**
     * Gets Player life who send the information..
     *
     * @return Value of Player life who send the information..
     */
    public int getPlayerSendLife() {
        return playerSendLife;
    }

    /**
     * Sets new The round in the game..
     *
     * @param round New value of The round in the game..
     */
    public void setRound(short round) {
        this.round = round;
    }

    /**
     * Gets Player mana who send the information..
     *
     * @return Value of Player mana who send the information..
     */
    public int getPlayerSendMana() {
        return playerSendMana;
    }

    /**
     * Sets new Player name who send the  information..
     *
     * @param playerSendName New value of Player name who send the  information..
     */
    public void setPlayerSendName(String playerSendName) {
        this.playerSendName = playerSendName;
    }

    /**
     * Gets Cart type to sent the other player..
     *
     * @return Value of Cart type to sent the other player..
     */
    public String getCodeSendCart() {
        return codeSendCart;
    }

    /**
     * Gets The round in the game..
     *
     * @return Value of The round in the game..
     */
    public short getRound() {
        return round;
    }

    /**
     * Sets new Player mana who send the information..
     *
     * @param playerSendMana New value of Player mana who send the information..
     */
    public void setPlayerSendMana(int playerSendMana) {
        this.playerSendMana = playerSendMana;
    }

    /**
     * Gets Player name who send the  information..
     *
     * @return Value of Player name who send the  information..
     */
    public String getPlayerSendName() {
        return playerSendName;
    }

    /**
     * Gets PLayer name who start..
     *
     * @return Value of PLayer name who start..
     */
    public ConnectionType getWhoFirst() {
        return whoFirst;
    }

    /**
     * Sets new Says if the other player skips the turn..
     *
     * @param skipTurn New value of Says if the other player skips the turn..
     */
    public void setSkipTurn(boolean skipTurn) {
        this.skipTurn = skipTurn;
    }

    /**
     * Sets new PLayer name who start..
     *
     * @param whoFirst New value of PLayer name who start..
     */
    public void setWhoFirst(ConnectionType whoFirst) {
        this.whoFirst = whoFirst;
    }

    /**
     * Gets Says if the other player skips the turn..
     *
     * @return Value of Says if the other player skips the turn..
     */
    public boolean isSkipTurn() {
        return skipTurn;
    }

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "playerSendName='" + playerSendName + '\'' +
                ", whoFirst=" + whoFirst +
                ", playerSendMana=" + playerSendMana +
                ", playerSendLife=" + playerSendLife +
                ", codeSendCart=" + codeSendCart +
                ", round=" + round +
                ", skipTurn=" + skipTurn +
                '}';
    }

}

