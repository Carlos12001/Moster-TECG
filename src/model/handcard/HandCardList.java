package model.handcard;

import model.cards.Card;

public class HandCardList {

    private HandCardNode head;
    private HandCardNode tail;
    private HandCardNode currentDisplay;
    private int size;


    public HandCardList(){
        this.head = null;
        this.tail = null;
        this.currentDisplay = null;
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
            size++;
        } else {
            this.head.setPrevious(newCardNode);
            newCardNode.setPrevious(this.tail);
            this.tail.setNext(newCardNode);
            this.tail = newCardNode;
            this.tail.setNext(this.head);
            size++;
        }
    }

    public Card deleteCard(String id){
        HandCardNode current = this.head;
        HandCardNode previous = this.head;
        HandCardNode tmp;
        while (current != null){
            if (current.getCardN().getCode().equals(id)){ // hacer este emtodo en clase carta
                if (current == this.head){
                    this.head = current.getNext();
                    this.head.setPrevious(this.tail);
                    this.tail.setNext(this.head);
                    setCurrentDisplayTail();
                    this.size--;
                } else if (current == this.tail){
                    this.tail =  previous.getNext();
                    previous.setNext(this.head);
                    this.head.setPrevious(this.tail);
                    setCurrentDisplayTail();
                    this.size--;
                } else {
                    tmp = current.getNext();
                    previous.setNext(current.getNext());
                    tmp.setPrevious(current.getPrevious());
                    setCurrentDisplayTail();
                    this.size--;
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

    public void setCurrentDisplayTail(){
        this.currentDisplay = this.tail;
    }

    public Card displayCard(String direction){
        if (currentDisplay == null){
            this.currentDisplay = this.head;
            return this.currentDisplay.getCardN();
        } else {
            switch (direction) {
                case "previous":
                    this.currentDisplay = this.currentDisplay.getPrevious();
                    return this.currentDisplay.getCardN();
                case "next":
                    this.currentDisplay = this.currentDisplay.getNext();
                    return this.currentDisplay.getCardN();
                case "current":
                    return this.currentDisplay.getCardN();
            }
        }
        return null;
    }

    public void displayListPrint(){
        StringBuilder result = new StringBuilder("[ ");

        if(!this.isEmpty()) {
            HandCardNode current = this.head;
            for (int i = 0; i < this.size; i++) {
                result.append(current.getCardN().getCode()).append(", ");
                current = current.getNext();
            }
        }

        result.append(" ]");

        System.out.println(result);
    }

    /**
     * Gets currentDisplay.
     *
     * @return Value of currentDisplay.
     */
    public Card getCurrentDisplay() {
        return currentDisplay.getCardN();
    }

    /**
     * Gets tail.
     *
     * @return Value of tail.
     */
    public Card getTail() {
        return tail.getCardN();
    }

    /**
     * Gets head.
     *
     * @return Value of head.
     */
    public Card getHead() {
        return head.getCardN();
    }
}
