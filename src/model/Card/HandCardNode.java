package model.Card;

public class HandCardNode {
    private HandCardNode next;
    private HandCardNode previous;
    private Object cardN;

    public HandCardNode(Object cardN){
        this.next = null;
        this.previous = null;
        this.cardN = cardN;
    }

    public  Object getCardN(){
        return  this.cardN;
    }

    public void setCardN(Object newCardN){
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

    public HandCardNode setPrevious(HandCardNode handCardNode){
        this.previous = handCardNode;
    }
}
