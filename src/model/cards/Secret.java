package model.cards;

import controller.GameController;

public class Secret extends Card {


    public Secret(String code) {
        super(code);
    }

    public Secret(Card card){
        super(card);
    }
}
