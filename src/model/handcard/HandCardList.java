package model.handcard;

import model.cards.Card;

/**
 *
 */
public class HandCardList {

    /**
     * this id the head of the handcard list
     */
    private HandCardNode head;

    /**
     * this is the end of the handcard list
     */
    private HandCardNode tail;

    /**
     * this is the current card that is displayed
     */
    private HandCardNode currentDisplay;

    /**
     * This is the size of the list
     */
    private int size;


    /**
     * this is the creator of the list.
     */
    public HandCardList(){
        this.head = null;
        this.tail = null;
        this.currentDisplay = null;
        this.size = 0;
    }

    /**
     * This method verify if the list is empty.
     *
     * @return boolean
     */
    public boolean isEmpty(){
        return this.head == null;
    }



    /**
     * This method insert a new card at the end of the list.
     * @param handCardNode Card
     */
    public void insertLast(Card handCardNode){
        HandCardNode newCardNode = new HandCardNode(handCardNode);

        if (this.isEmpty()){
            this.head = this.tail = newCardNode;
        } else {
            this.tail.setNext(newCardNode);
            newCardNode.setPrevious(this.tail);
            newCardNode.setNext(this.head);
            this.tail = newCardNode;
            this.head.setPrevious(this.tail);
        }
        this.size++;
    }

    /**
     * This method deletes the current card and return it.
     *
     * @param id String that identifies the card
     * @return the card tha is deleted.
     */
    public Card deleteCard(String id){
        HandCardNode current = this.head;
        HandCardNode previous = this.head;
        HandCardNode tmp;
        while (current != null){
            if (current.getCardN().getCode().equals(id)){ // hacer este emtodo en clase carta
                if (current == this.head && current == this.tail){
                    this.head = this.tail = null;
                    this.size--;
                } else if (current == this.head){
                    this.head = current.getNext();
                    this.head.setPrevious(this.tail);
                    this.tail.setNext(this.head);
                    this.size--;
                } else if (current == this.tail){
                    this.tail =  previous;
                    this.tail.setNext(this.head);
                    this.head.setPrevious(this.tail);
                    this.size--;
                } else {
                    tmp = current.getNext();
                    previous.setNext(current.getNext());
                    tmp.setPrevious(current.getPrevious());
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

    /**
     * This method set the to the next node.
     */
    public void setDisplayCard(){
        if (this.isEmpty()){
            this.currentDisplay = null;
        } else{
            this.currentDisplay = this.currentDisplay.getNext();
        }
    }

    /**
     * This method set the currentDisplay to the tail.
     */
    public void setCurrentDisplayTail(){
        this.currentDisplay = this.tail;
    }

    /**
     * This method set the current display and returns the card in that position.
     *
     * @param direction String indicating the direction.
     * @return the current card.
     */
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

    /**
     * This method is used to display the list.
     */
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

    /**
     * Gets size.
     *
     * @return Value of size.
     */
    public int getSize() {
        return size;
    }
}
