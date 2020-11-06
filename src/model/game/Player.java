package model.game;

/**
 * Esta clase se maneja toda la informacion relacionada con el jugador como el nomrbe, vida y mana
 * @version 1.0
 */
public class Player {

    /**
     * This is the name of the player.
     */
    private final String name;

    /**
     * The mana is used to buy cards, the maximum is 1000.
     */
    private int mana;

    /**
     * this is the playe's life, the maximun is 1000.
     */
    private int life;

    /**
     * This is a percentage that is used on increaseLife Turn.
     */
    private double multiplier = 0.25;

    /**
     * This is the constructor, it sets the mana, name and life.
     */
    public Player(String name){
        this.name = name;
        this.mana = 600;
        this.life = 1000;
    }

    /**
     * This method returns the name of the player.
     *
     * @return String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method returns the mana of the player.
     *
     * @return Value of mana
     */
    public int getMana() {
        return this.mana;
    }

    /**
     * This method set the mana.
     *
     * @param mana New value of num
     */
    public void setMana(int mana) {
        this.mana = mana;
        if(this.mana >= 1000){
            this.mana = 1000;
        }
    }

    /**
     * This method sum points of mana.
     *
     * @param num New value of num
     */

    public void increaseMana(int num){
        if ((this.mana + num) >= 1000) {
            this.mana = 1000;
        } else {
            this.mana += num;
        }
    }

    /**
     * This method rest points of mana.
     *
     * @param num int points of mana to decrease
     */

    public void decreaseMana(int num){
        if ((this.mana - num) <= 0) {
            this.mana = 50;
        } else {
            this.mana -= num;
        }
    }

    /**
     * This method increase 25% of the mana.
     */
    public void increaseManaTurn() {
        int percentaje = (int) (this.multiplier * this.mana);

        if (this.mana >= 1000){
            this.mana = 1000;
        } else if ((this.mana + percentaje) >= 1000){
            this.mana = 1000;
        } else {
            this.mana += percentaje ;
        }
    }

    /**
     * This method retunrs the player's life.
     *
     * @return Value of life
     */
    public int getLife() {
        return this.life;
    }

    /**
     * This method set playes's life.
     *
     * @param life int new points of life
     */
    public void setLife(int life) {
        this.life = life;
        if(this.life>1000){
            this.life = 1000;
        }
    }

    /**
     * This method sum points of life.
     *
     * @param num New value of num
     */

    public void increaseLife(int num){
        if ((this.life + num) >= 1000) {
            this.life = 1000;
        } else {
            this.life += num;
        }
    }

    /**
     * This method rest points of life.
     *
     * @param num New value of num
     */

    public void decreaseLife(int num){
        this.life -= num;
    }

    /**
     * Sets new multiplier.
     *
     * @param multiplier New value of multiplier.
     */
    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Gets multiplier.
     *
     * @return Value of multiplier.
     */
    public double getMultiplier() {
        return multiplier;
    }
}

