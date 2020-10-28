package model.history;

public class HistoryList {
    private HistoryNode head;
    private HistoryNode tail;
    private int size;

    public HistoryList(){
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

    public void insertLast(History newHistory){
        HistoryNode newHistoryNode = new HistoryNode(newHistory);

        if (this.isEmpty()){
            this.head = this.tail = newHistoryNode;
            this.size++;
        } else {
            newHistoryNode.setPrevious(this.tail);
            this.tail.setNext(newHistoryNode);
            this.tail = newHistoryNode;
            this.size++;
        }
    }
/*
    public History displayHistory(String direction){

    }
    
 */
}
