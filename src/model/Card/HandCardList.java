package model.Card;

public class HandCardList {

    private HandCardNode head;
    private HandCardNode tail;
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

    public void insertLast(Object handCardNode){
        HandCardNode newCardNode = new HandCardNode(handCardNode);

        if (this.isEmpty()){
            this.head = this.tail = newCardNode;
        } else {
            this.head.setNext(newCardNode);
            newCardNode.setPrevious(this.tail);
            this.tail.setNext(newCardNode);
            this.tail = newCardNode;
        }
    }

    public Object deleteCard(String id){
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
                return current;
            }
            else {
                previous = current;
                current = current.getNext();
            }
        }
    }
}
