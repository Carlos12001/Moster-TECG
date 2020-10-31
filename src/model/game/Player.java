package model.game;

/**
 * Esta clase se maneja toda la informacion relacionada con el jugador como el nomrbe, vida y mana
 * @version 1
 */
public class Player {

    /**
     *
     */
    private final String name;
    /**
     *
     */
    private int mana;
    /**
     *
     */
    private int life;

    /**
     *
     */
    private int percentaje;

    /**
     *
     */
    public Player(String name){
        this.name = name;
        this.mana = 200;
        this.life = 1000;
    }

    /**
     * This method returns the name of the player
     * @return String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method returns the mana of the player
     * @return Value of mana
     */
    public int getMana() {
        return this.mana;
    }

    /**
     * This method set the mana
     * @param mana New value of num
     */
    public void setMana(int mana) {
        this.mana = mana;
        if(this.mana >= 1000){
            this.mana = 1000;
        }
    }

    /**
     * This method sum points of mana
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
     * This method rest points of mana
     * @param num int points of mana to decrease
     */

    public void decreaseMana(int num){
        if ((this.mana - num) <= 0) {
            this.mana = 0;
        } else {
            this.mana -= num;
        }
    }

    /**
     * This method increase 25% of the mana
     */
    public void increase25() {
        percentaje = (int) (0.25 * this.mana);

        if (this.mana >= 1000){
            this.mana = 1000;
        } else if ((this.mana + percentaje) >= 1000){
            this.mana = 1000;
        } else {
            this.mana += percentaje;
        }
    }

    /**
     * Este metodo retorna la vida del jugador
     * @return Value of life
     */
    public int getLife() {
        return this.life;
    }

    /**
     * This method set playes's life
     * @param life int new points of life
     */
    public void setLife(int life) {
        this.life = life;
        if(this.life>1000){
            this.life = 1000;
        }
    }

    /**
     * This method sum points of life
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
     * This method rest points of life
     * @param num New value of num
     */

    public void decreaseLife(int num){
        int tmp = this.life;
        if ((tmp - num) <= 0) {
            this.life = 0;
        } else {
            this.life -= num;
        }
    }
}

