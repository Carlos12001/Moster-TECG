package model.cards;

/**
 * This class sets characteristics of hencheman type cards.
 */
public class Hencheman extends Card{

    /**
     * This is the points of attack of each card.
     */
    private int atack;

    /**
     * This creator obtains the methods and attributes from the class card and set the attack using the code of
     * the card
     *
     * @param code String id of the card.
     */
    public Hencheman(String code) {
        super(code);
        setHenchman(this.getNumerCode());
    }

    /**
     * This creator obtains the methods and attributes from the class card and set the attack. Using the card
     *
     * @param card Card
     */
    public Hencheman(Card card) {
        super(card);
        setHenchman(this.getNumerCode());
    }

    /**
     * Set the card features.
     *
     * @param numCode The card number.
     */
    private void setHenchman(short numCode){
        switch (numCode) {
            case 0 -> this.setAtack(150);
            case 1 -> this.setAtack(200);
            case 2 -> this.setAtack(100);
            case 3 -> this.setAtack(80);
            case 4 -> this.setAtack(140);
            case 5 -> this.setAtack(110);
            case 6 -> this.setAtack(130);
            case 7 -> this.setAtack(200);
            case 8 -> this.setAtack(300);
            case 9 -> this.setAtack(200);
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
