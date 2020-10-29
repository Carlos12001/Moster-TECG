package model.cards;

import model.game.Game;

public class Henchman extends Card{


    private int atack;

    public Henchman(String code) {
        super(code);
    }

    public void actionHenchman(short numCard){
        Game game = Game.getInstance();
        switch (numCard){
            case 0:
                game.getPlayer().decreaseLife(70);
            case 1:
                game.getPlayer().decreaseLife(100);
            case 2:
                game.getPlayer().decreaseLife(30);
            case 3:
                game.getPlayer().decreaseLife(40);
            case 4:
                game.getPlayer().decreaseLife(50);
            case 5:
                game.getPlayer().decreaseLife(20);
            case 6:
                game.getPlayer().decreaseLife(20);
            case 7:
                game.getPlayer().decreaseLife(20);
            case 8:
                game.getPlayer().decreaseLife(20);
            case 9:
                game.getPlayer().decreaseLife(20);
        }

    }

}
