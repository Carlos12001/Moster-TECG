package model.cards;

/**
 * @version 2.0
 */
public class Spell extends Card{

    /**
     *
     */
    private int atack;
    /**
     *
     */
    private int health;
    /**
     *
     */
    private int shifts;

    /**
     * @param code
     */
    public Spell(String code) {
        super(code);
        this.setSpell(this.getNumerCode());
    }

    /**
     * @param card
     */
    public Spell(Card card){
        super(card);
        this.setSpell(this.getNumerCode());
    }

    /**
     *Set the card features.
     *
     * @param numCode The card number.
     */
    private void setSpell(short numCode){
        switch (numCode) {
            case 0 -> this.setShifts(1);
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
     * Gets health.
     *
     * @return Value of health.
     */
    public int getHealth() {
        return health;
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

    /**
     * Sets new health.
     *
     * @param health New value of health.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Sets new shifts.
     *
     * @param shifts New value of shifts.
     */
    public void setShifts(int shifts) {
        this.shifts = shifts;
    }

    /**
     * Gets shifts.
     *
     * @return Value of shifts.
     */
    public int getShifts() {
        return shifts;
    }
}
