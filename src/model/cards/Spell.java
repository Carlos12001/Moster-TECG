package model.cards;

public class Spell extends Card{

    private int atack;
    private int health;

    public Spell(String code) {
        super(code);
    }

    public Spell(Card card){
        super(card);
    }

}
