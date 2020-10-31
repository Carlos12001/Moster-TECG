package model.cards;

import model.game.Game;

public class Henchman extends Card{




    private int atack;

    public Henchman(String code) {
        super(code);
    }

    public Henchman(Card card){
        super(card);
    private void setAtack(int atack){
        this.atack = atack;
    }

    public int getAtack() {
        return atack;
    }

    public void setHenchman(short numCode){
        switch (numCode) {
            case 0:
                setAtack(70);
                break;
            case 1:
                setAtack(100);
                break;
            case 2:
                setAtack(30);
                break;
            case 3:
                setAtack(40);
                break;
            case 4:
                setAtack(50);
                break;
            case 5:
                setAtack(20);
                break;
            case 6:
                setAtack(20);
                break;
            case 7:
                setAtack(20);
                break;
            case 8:
                setAtack(20);
                break;
            case 9:
                setAtack(20);
                break;
        }
    }



    public void actionHenchman(){
        Game game = Game.getInstance();
        game.getPlayer().decreaseLife(this.atack);
    }

}
