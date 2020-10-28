package model.handcard;

import model.cards.Card;

public class HandCardNode {
    private HandCardNode next;
    private HandCardNode previous;
    private Card cardN;

    /**
     * @param cardN
     */
    public HandCardNode(Card cardN){
        this.next = null;
        this.previous = null;
        this.cardN = cardN;
    }

    /**
     * @return
     */
    public Card getCardN(){
        return  this.cardN;
    }

    /**
     * @param newCardN
     */
    public void setCardN(Card newCardN){
        this.cardN = newCardN;
    }

    /**
     * @return
     */
    public HandCardNode getNext(){
        return this.next;
    }

    /**
     * @param handCardNode
     */
    public void setNext(HandCardNode handCardNode) {
        this.next = handCardNode;
    }

    /**
     * @return
     */
    public HandCardNode getPrevious(){
        return this.previous;
    }

    /**
     * @param handCardNode
     */
    public void setPrevious(HandCardNode handCardNode){
        this.previous = handCardNode;
    }
}
