package model.history;

public class HistoryNode {
    private HistoryNode next;
    private HistoryNode previous;
    private History historyN;

    /**
     * Creates a new history node
     * @param history
     */
    public HistoryNode(History history){
        this.next = null;
        this.previous = null;
        this.historyN = history;
    }

    /**
     * Gets historyN.
     *
     * @return Value of historyN.
     */
    public History getHistoryN() {
        return historyN;
    }

    /**
     * Sets new historyN.
     *
     * @param historyN New value of historyN.
     */
    public void setHistoryN(History historyN) {
        this.historyN = historyN;
    }

    /**
     * Sets new previous.
     *
     * @param previous New value of previous.
     */
    public void setPrevious(HistoryNode previous) {
        this.previous = previous;
    }

    /**
     * Gets previous.
     *
     * @return Value of previous.
     */
    public HistoryNode getPrevious() {
        return previous;
    }

    /**
     * Gets next.
     *
     * @return Value of next.
     */
    public HistoryNode getNext() {
        return next;
    }

    /**
     * Sets new next.
     *
     * @param next New value of next.
     */
    public void setNext(HistoryNode next) {
        this.next = next;
    }
}
