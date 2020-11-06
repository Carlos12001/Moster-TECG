package model.history;

import model.cards.Card;

/**
 * This class creates nodes for the linked list of history, it contains all the information necessary to
 * show the recent play
 */
public class HistoryNode {

    /**
     * This is the pointer of the next history
     */
    private HistoryNode next;

    /**
     * This is the pointer of the previous history
     */
    private HistoryNode previous;

    /**
     * This string contains the name of the person who sent the card.
     */
    private String senderName;

    /**
     * This string contains the name of the person who receive the card.
     */
    private String reciberName;

    /**
     * This int contains the points of life the person who sent the card has.
     */
    private int senderLife;

    /**
     * This int contains the points of life the person who receive the card has.
     */
    private int reciberLife;

    /**
     * This number contains the number of the round.
     */
    private short round;

    private String cardcode;

    /**
     * Here is the card object.
     */
    private Card card;


    /**
     * This is the creator method where it builds a node with all the information necessary to show the recent play.
     *
     * @param senderName String
     * @param reciberName String
     * @param senderLife int
     * @param reciberLife int
     * @param cardcode String
     * @param round short
     */
    public HistoryNode(String senderName, String reciberName, int senderLife, int reciberLife, String cardcode, short round){
        this.next = null;
        this.previous = null;
        this.senderName = senderName;
        this.reciberName = reciberName;
        this.senderLife = senderLife;
        this.reciberLife = reciberLife;
        this.round = round;
        if (cardcode.equals("")){
            this.card = null;
        } else {
            this.card = new Card(cardcode);
        }

    }

    /**
     * Gets card.
     *
     * @return Card object.
     */
    public Card getCard() {
        return card;
    }

    /**
     * Gets senderName.
     *
     * @return Value of senderName.
     */
    public String getsenderName() {
        return senderName;
    }

    /**
     * Gets senderLife.
     *
     * @return Value of senderLife.
     */
    public int getsenderLife() {
        return senderLife;
    }

    /**
     * Gets reciberName.
     *
     * @return Value of reciberName.
     */
    public String getreciberName() {
        return reciberName;
    }

    /**
     * Gets cardImage.
     *
     * @return Value of cardImage.
     */
    public String getCardImage() {
        return card.getImage();
    }

    /**
     * Gets reciberLife.
     *
     * @return Value of reciberLife.
     */
    public int getreciberLife() {
        return reciberLife;
    }

    /**
     * Gets previous.
     *
     * @return Value of previous.
     */
    public HistoryNode getPrevious() {
        return previous;
    }

    /**
     * Gets next.
     *
     * @return Value of next.
     */
    public HistoryNode getNext() {
        return next;
    }

    /**
     * Sets new next.
     *
     * @param next New value of next.
     */
    public void setNext(HistoryNode next) {
        this.next = next;
    }

    /**
     * Sets new previous.
     *
     * @param previous New value of previous.
     */
    public void setPrevious(HistoryNode previous) {
        this.previous = previous;
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
     * Gets round.
     *
     * @return Value of round.
     */
    public short getRound() {
        return round;
    }
}
