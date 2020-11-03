package model.history;

import model.cards.Card;

public class HistoryNode {

    private HistoryNode next;
    private HistoryNode previous;
    private String senderName;
    private String reciberName;
    private int senderLife;
    private int reciberLife;
    private String cardcode;
    private Card card;


    public HistoryNode(String senderName, String reciberName, int senderLife, int reciberLife, String cardcode){
        this.next = null;
        this.previous = null;
        this.senderName = senderName;
        this.reciberName = reciberName;
        this.senderLife = senderLife;
        this.reciberLife = reciberLife;
        this.card = new Card(cardcode);
    }


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
}
