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



    public void actionHenchman(){
        Game game = Game.getInstance();
        game.getPlayer().decreaseLife(this.atack);
    }

}
