package model.Card;

public class HandCardNode {
    private HandCardNode next;
    private HandCardNode previous;
    private Card cardN;

    public HandCardNode(Card cardN){
        this.next = null;
        this.previous = null;
        this.cardN = cardN;
    }

    public Card getCardN(){
        return  this.cardN;
    }

    public void setCardN(Card newCardN){
        this.cardN = newCardN;
    }

    public HandCardNode getNext(){
        return this.next;
    }

    public void setNext(HandCardNode handCardNode) {
        this.next = handCardNode;
    }

    public HandCardNode getPrevious(){
        return this.previous;
    }

    public void setPrevious(HandCardNode handCardNode){
        this.previous = handCardNode;
    }
}
