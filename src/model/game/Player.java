package model.game;

import test.Hola;

/**
 *
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
    private Conextion typeConexion;

    /**
     *
     */
    public Player(String name){
        this.name = name;
        this.mana = 200;
        this.life = 1000;
    }


    /**
     *
     */
    public String getName() {
        return name;
    }

    /**
     *
     */
    public int getMana() {
        return mana;
    }

    /**
     *
     */
    public void setMana(int mana) {
        this.mana = mana;
        if(this.mana>200){
            this.mana = 200;
        }
    }

    /**
     *
     */
    public int getLife() {
        return life;
    }

    /**
     *
     */
    public void setLife(int life) {
        this.life = life;
        if(this.life>1000){
            this.life = 1000;
        }

    }
}