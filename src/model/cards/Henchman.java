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
    }

    /**
     * @param card
     */
    public Henchman(Card card) {
        super(card);
    }

    /**
     * @param numCode
     */
    public void setHenchman(short numCode){
        switch (numCode) {
            case 0:
                this.setAtack(70);
                break;
            case 1:
                this.setAtack(100);
                break;
            case 2:
                this.setAtack(30);
                break;
            case 3:
                this.setAtack(40);
                break;
            case 4:
                this.setAtack(50);
                break;
            case 5:
                this.setAtack(20);
                break;
            case 6:
                this.setAtack(20);
                break;
            case 7:
                this.setAtack(20);
                break;
            case 8:
                this.setAtack(20);
                break;
            case 9:
                this.setAtack(20);
                break;
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
