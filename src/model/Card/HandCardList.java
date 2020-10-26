package model.Card;

import model.Card.Card;

public class HandCardList {

    private HandCardNode head;
    private HandCardNode tail;
    private HandCardNode currentDisplay = this.head;
    private int size;

    public HandCardList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    public int getSize(){
        return this.size;
    }

    public void insertLast(Card handCardNode){
        HandCardNode newCardNode = new HandCardNode(handCardNode);

        if (this.isEmpty()){
            this.head = this.tail = newCardNode;
        } else {
            this.head.setPrevious(newCardNode);
            newCardNode.setPrevious(this.tail);
            this.tail.setNext(newCardNode);
            this.tail = newCardNode;
        }
    }

    public Card deleteCard(String id){
        HandCardNode current = this.head;
        HandCardNode previous = this.head;
        HandCardNode tmp;
        while (current != null){
            if (current.getCardN().getId().equals(id)){ // hacer este emtodo en clase carta
                if (current == this.head){
                    this.head = current.getNext();
                    this.head.setPrevious(this.tail);
                    this.tail.setNext(this.head);
                } else if (current == this.tail){
                    this.tail =  previous.getNext();
                    previous.setNext(this.head);
                    this.head.setPrevious(this.tail);
                } else {
                    tmp = current.getNext();
                    previous.setNext(current.getNext());
                    tmp.setPrevious(current.getPrevious());
                }
                return current.getCardN();
            }
            else {
                previous = current;
                current = current.getNext();
            }
        }
        return null;
    }

    public Card displayCard(){
        return this.currentDisplay.getCardN();
    }

    public void setCurrentDisplay(){
        this.currentDisplay = this.currentDisplay.getNext();
    }
}
