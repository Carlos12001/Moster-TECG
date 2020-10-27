package model.Deck;

import model.Card.Card;

import javax.swing.*;

public class DeckStack {
    private int maxSize;
    private Card[] stackArray;
    private int top;

    public void DeckStack(int max){
        this.maxSize = max;
        this.top = -1;
    }

    public void push(Card newCard){
        if (top < maxSize) {
            this.stackArray[++top] = newCard;
        } else {
            System.out.println("pila llena"); // agregar al logger
        }
    }

    public Card pop(){
        return this.stackArray[top--];
    }

    public Card peek(){
        return this.stackArray[top];
    }
}
