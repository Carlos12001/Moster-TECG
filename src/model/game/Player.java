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
     * Este metodo retorna el nombre del jugador
     */
    public String getName() {
        return this.name;
    }

    /**
     * Este metodo retorna el mana del jugador
     */
    public int getMana() {
        return this.mana;
    }

    /**
     * Este metodo se encarga de colocar un nuevo mana
     */
    public void setMana(int mana) {
        this.mana = mana;
        if(this.mana > 1000){
            this.mana = 1000;
        }
    }

    /**
     * Este metodo se encarga de incrementar en un 25 porciento el mana del jugador
     *
     */
    public void increase25() {
        percentaje = (int) (0.25 * this.mana);

        if (this.mana > 1000){
            this.mana = 1000;
        } else if ((this.mana + percentaje) > 1000){
            this.mana = 1000;
        } else {
            this.mana += percentaje;
        }
    }

    /**
     * Este metodo retorna la vida del jugador
     */
    public int getLife() {
        return this.life;
    }

    /**
     * Este metodo coloca un nuevo entero en la vida del jugador
     */
    public void setLife(int life) {
        this.life = life;
        if(this.life>1000){
            this.life = 1000;
        }

    }
}