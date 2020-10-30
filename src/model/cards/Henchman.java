package model.cards;

import model.game.Game;

public class Henchman extends Card{




    private int atack;

    public Henchman(String code) {
        super(code);
    }

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
                setImage("src/images/PRECARDS/C-HENCHAN/HENCHEMAN@0.jpg");
                break;
            case 1:
                setAtack(100);
                setImage("src/images/PRECARDS/C-HENCHAN/HENCHEMAN@1.jpg");
                break;
            case 2:
                setAtack(30);
                setImage("src/images/PRECARDS/C-HENCHAN/HENCHEMAN@2.jpg");
                break;
            case 3:
                setAtack(40);
                setImage("src/images/PRECARDS/C-HENCHAN/HENCHEMAN@3.jpg");
                break;
            case 4:
                setAtack(50);
                setImage("src/images/PRECARDS/C-HENCHAN/HENCHEMAN@4.jpg");
                break;
            case 5:
                setAtack(20);
                setImage("src/images/PRECARDS/C-HENCHAN/HENCHEMAN@5.jpg");
                break;
            case 6:
                setAtack(20);
                setImage("src/images/PRECARDS/C-HENCHAN/HENCHEMAN@6.jpg");
                break;
            case 7:
                setAtack(20);
                setImage("src/images/PRECARDS/C-HENCHAN/HENCHEMAN@7.jpg");
                break;
            case 8:
                setAtack(20);
                setImage("src/images/PRECARDS/C-HENCHAN/HENCHEMAN@8.jpg");
                break;
            case 9:
                setAtack(20);
                setImage("src/images/PRECARDS/C-HENCHAN/HENCHEMAN@9.jpg");
                break;
        }
    }



    public void actionHenchman(short numCard){
        Game game = Game.getInstance();
        switch (numCard){
            case 0:
                game.getPlayer().decreaseLife(this.atack);
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
