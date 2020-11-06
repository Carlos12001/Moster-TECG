package model.deckcard;

import model.cards.Card;

/**
 * This class is an stack, here is saved all the card from deck
 */
public class DeckStack {

    /**
     * This is a number that indicates the maximum size the stack can have.
     */
    private short maxSize;

    /**
     * This is the array that is used to stack all the cards
     */
    private Card[] stackArray;

    /**
     * This is a number tha indicates where the top is.
     */
    private short top;

    /**
     * Create a new Stack.
     *
     * @param max The max of the stack
     */
    public DeckStack(short max){
        this.maxSize = max;
        this.top = -1;
        this.stackArray = new Card[max];
    }

    /**
     * Push the new Card in the stack.
     *
     * @param newCard The new Card in the stack.
     */
    public void push(Card newCard){
        if (top < maxSize) {
            this.stackArray[++top] = newCard;
        }
    }

    /**
     * Get the cart in the top.
     *
     * @return Get the card in the top and delete in the stack.
     */
    public Card pop(){
        if (this.top>-1)
            return this.stackArray[top--];
        else
            return null;
    }

    /**
     * Get the cart in the top
     *
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
