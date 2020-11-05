package model.handcard;

import model.cards.Card;

/**
 * This class is the node of the hand card list.
 */
public class HandCardNode {

    /**
     * This is the pointer of the next card.
     */
    private HandCardNode next;

    /**
     * This if the pointer of the previous card.
     */
    private HandCardNode previous;

    /**
     * this is the saves the card object.
     */
    private Card cardN;

    /**
     * this is the creator
     *
     * @param cardN Card
     */
    public HandCardNode(Card cardN){
        this.next = null;
        this.previous = null;
        this.cardN = cardN;
    }

    /**
     * Gets cardN.
     *
     * @return Value of cardN.
     */
    public Card getCardN() {
        return cardN;
    }

    /**
     * Gets next.
     *
     * @return Value of next.
     */
    public HandCardNode getNext() {
        return next;
    }

    /**
     * Sets new next.
     *
     * @param next New value of next.
     */
    public void setNext(HandCardNode next) {
        this.next = next;
    }

    /**
     * Gets previous.
     *
     * @return Value of previous.
     */
    public HandCardNode getPrevious() {
        return previous;
    }

    /**
     * Sets new previous.
     *
     * @param previous New value of previous.
     */
    public void setPrevious(HandCardNode previous) {
        this.previous = previous;
    }

    /**
     * Sets new cardN.
     *
     * @param cardN New value of cardN.
     */
    public void setCardN(Card cardN) {
        this.cardN = cardN;
    }

}
