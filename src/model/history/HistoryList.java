package model.history;

public class HistoryList {
    private HistoryNode head;
    private HistoryNode tail;
    private HistoryNode currentDisplay;
    private int size;

    public HistoryList(){
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

    public void insertLast(String opponentName, String playerName, int opponentLife, int playerLife, String cardImage){
        HistoryNode newHistoryNode = new HistoryNode(opponentName, playerName, opponentLife, playerLife, cardImage);

        if (this.isEmpty()){
            this.head = this.tail = newHistoryNode;
        } else {
            newHistoryNode.setPrevious(this.tail);
            this.tail.setNext(newHistoryNode);
            this.tail = newHistoryNode;
        }
        this.size++;
    }

    public void setCurrentDisplay(){
        if (this.isEmpty()){
            this.currentDisplay = null;
        } else {

        }
    }

    public HistoryNode displayHistory(String direction){
        if (currentDisplay == null){
            this.currentDisplay = this.head;
            return this.currentDisplay;
        } else {
            switch (direction) {
                case "previous":
                    this.currentDisplay = this.currentDisplay.getPrevious();
                    return this.currentDisplay;
                case "next":
                    this.currentDisplay = this.currentDisplay.getNext();
                    return this.currentDisplay;
                case "current":
                    return this.currentDisplay;
            }
        }
        return null;
    }
}
