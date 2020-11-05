package model.history;

/**
 * This is a double linked list which contains history nodes. the user can navigate forward to the end
 * or backward to the beginning.
 */
public class HistoryList {

    /**
     * this is the head of the History list.
     */
    private HistoryNode head;

    /**
     * this is the end of the History list.
     */
    private HistoryNode tail;

    /**
     * This is the current history node that is displayed.
     */
    private HistoryNode currentDisplay;

    /**
     * This is the size of the list.
     */
    private int size;

    /**
     * this is the creator of the list.
     */
    public HistoryList(){
        this.head = null;
        this.tail = null;
        this.currentDisplay = null;
        this.size = 0;
    }

    /**
     * This method verify if the list is empty.
     *
     * @return  boolean
     */
    public boolean isEmpty(){
        return this.head == null;
    }

    /**
     * Gets size.
     *
     * @return Value of size.
     */
    public int getSize(){
        return this.size;
    }

    /**
     * This method insert a new history node at the end of the linked list. also increments the size.
     *
     * @param opponentName String
     * @param playerName String
     * @param opponentLife int
     * @param playerLife int
     * @param cardImage String
     * @param round short
     */
    public void insertLast(String opponentName, String playerName, int opponentLife, int playerLife, String cardImage, short round){
        HistoryNode newHistoryNode = new HistoryNode(opponentName, playerName, opponentLife, playerLife, cardImage, round);

        if (this.isEmpty()){
            this.head = this.tail = newHistoryNode;
        } else {
            newHistoryNode.setPrevious(this.tail);
            this.tail.setNext(newHistoryNode);
            this.tail = newHistoryNode;
        }
        this.size++;
    }

    /**
     * This method set the current display to the tail.
     */
    public void setCurrentDisplayTail(){
        if (this.isEmpty()){
            this.currentDisplay = null;
        } else {
            this.currentDisplay = this.tail;
        }
    }

    /**
     * This method set the current display to the head.
     */
    public void setCurrentDisplayHead(){
        if (this.isEmpty()){
            this.currentDisplay = null;
        } else {
            this.currentDisplay = this.head;
        }
    }


    /**
     * This method set the current display and returns the hisoryNode in that position.
     *
     * @param direction String indicating the direction.
     * @return current HistoryNode 
     */
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
