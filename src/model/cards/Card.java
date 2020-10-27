package model.cards;

public abstract class Card {

    /**
     *
     */
    private final String category;
    /**
     *
     */
    private String eventCart;//players, game, both. JSON
    /**
     *
     */
    private final short code;
    /**
     *
     */
    private short costCard;//JSON


    public Card(String category, short code){
        this.category = category;
        this.code = code;
    }

    /**
     * Gets code.
     *
     * @return Value of code.
     */
    public short getCode() {
        return code;
    }

    /**
     * Gets costCard.
     *
     * @return Value of costCard.
     */
    public short getCostCard() {
        return costCard;
    }

    /**
     * Gets category.
     *
     * @return Value of category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets eventCart.
     *
     * @return Value of eventCart.
     */
    public String getEventCart() {
        return eventCart;
    }
}
