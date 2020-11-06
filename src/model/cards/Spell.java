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
     * This creator obtains the methods and attributes from the class card using the code of
     * the card
     *
     * @param code String
     */
    public Spell(String code) {
        super(code);
        this.setSpell(this.getNumerCode());
    }

    /**
     * This creator obtains the methods and attributes from the class card and set the shifts using the card
     *
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
            case 1 -> this.setHealth(300);
            case 2 -> this.setShifts(3);
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
