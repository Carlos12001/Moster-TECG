package model.cards;

import model.game.Game;

/**
 *
 */
public class Henchman extends Card{

    /**
     *
     */
    private int atack;

    /**
     * @param code
     */
    public Henchman(String code) {
        super(code);
        setHenchman(this.getNumerCode());
    }

    /**
     * @param card
     */
    public Henchman(Card card) {
        super(card);
        setHenchman(this.getNumerCode());
    }

    /**
     *Set the card features.
     *
     * @param numCode The card number.
     */
    private void setHenchman(short numCode){
        switch (numCode) {
            case 0 -> this.setAtack(70);
            case 1 -> this.setAtack(100);
            case 2 -> this.setAtack(30);
            case 3 -> this.setAtack(40);
            case 4 -> this.setAtack(50);
            case 5 -> this.setAtack(20);
            case 6 -> this.setAtack(150);
            case 7 -> this.setAtack(80);
            case 8 -> this.setAtack(120);
            case 9 -> this.setAtack(340);
        }
    }

    /**
     * Gets atack.
     *
     * @return Value of atack.
     */
    public int getAtack() {
        return atack;
    }

    /**
     * Sets new atack.
     *
     * @param atack New value of atack.
     */
    public void setAtack(int atack) {
        this.atack = atack;
    }
}
