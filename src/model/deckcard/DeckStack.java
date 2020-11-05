package model.deckcard;

import model.cards.Card;

/**
 *
 */
public class SizdeDeckStack {
    /**
     *
     */
    private short maxSize;
    /**
     *
     */
    private Card[] stackArray;
    /**
     *
     */
    private short top;

    /** Create a new Stack.
     * @param max The max of the stack
     */
    public DeckStack(short max){
        this.maxSize = max;
        this.top = -1;
        this.stackArray = new Card[max];
    }

    /** Push the new Card in the stack.
     * @param newCard The new Card in the stack.
     */
    public void push(Card newCard){
        if (top < maxSize) {
            this.stackArray[++top] = newCard;
        } else {
            System.out.println("pila llena"); // agregar al logger
        }
    }

    /** Get the cart in the top.
     * @return Get the card in the top and delete in the stack.
     */
    public Card pop(){
        if (this.top>-1)
            return this.stackArray[top--];
        else
            return null;
    }

    /** Get the cart in the top
     * @return Get the card in the top
     */
    public Card peek(){
        return this.stackArray[top];
    }

    /**
     * Gets top.
     *
     * @return Value of top.
     */
    public short getTop() {
        return top;
    }

    /**
     * Gets maxSize.
     *
     * @return Value of maxSize.
     */
    public short getMaxSize() {
        return maxSize;
    }
}
