package model.cards;

/**
 *
 */
public class Secret extends Card {


    /**
     * This creator obtains the methods and attributes from the class card using the code of
     * the card
     * @param code String id of the card.
     */
    public Secret(String code) {
        super(code);
    }

    /**
     * This creator obtains the methods and attributes from the class card using the card
     *
     * @param card Card
     */
    public Secret(Card card){
        super(card);
    }
}
