package model.history;

public class History {
    private String cardPlayed;
    private String name;
    private String event;
    private short round;
    private int myLife;
    private int opponentLife;


    /**
     * This is the constructor of History class
     * @param name
     * @param cardPlayed
     * @param round
     * @param opponentLife
     * @param myLife
     */
    public History(String name, String cardPlayed, short round, int opponentLife, int myLife){
        this.name = name;
        this.cardPlayed = cardPlayed;
        this.round = round;
        this.opponentLife = opponentLife;
        this.myLife = myLife;

        // ver como setear el evento
        // EL + name + lanzo la carta + cardPlayed.
    }


    /**
     * Sets new opponentLife.
     *
     * @param opponentLife New value of opponentLife.
     */
    public void setOpponentLife(int opponentLife) {
        this.opponentLife = opponentLife;
    }

    /**
     * Sets new cardPlayed.
     *
     * @param cardPlayed New value of cardPlayed.
     */
    public void setCardPlayed(String cardPlayed) {
        this.cardPlayed = cardPlayed;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets opponentLife.
     *
     * @return Value of opponentLife.
     */
    public int getOpponentLife() {
        return opponentLife;
    }

    /**
     * Gets cardPlayed.
     *
     * @return Value of cardPlayed.
     */
    public String getCardPlayed() {
        return cardPlayed;
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
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
     * Sets new event.
     *
     * @param event New value of event.
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * Gets myLife.
     *
     * @return Value of myLife.
     */
    public int getMyLife() {
        return myLife;
    }

    /**
     * Sets new round.
     *
     * @param round New value of round.
     */
    public void setRound(short round) {
        this.round = round;
    }

    /**
     * Sets new myLife.
     *
     * @param myLife New value of myLife.
     */
    public void setMyLife(int myLife) {
        this.myLife = myLife;
    }

    /**
     * Gets event.
     *
     * @return Value of event.
     */
    public String getEvent() {
        return event;
    }
}
