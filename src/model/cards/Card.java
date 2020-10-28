package model.cards;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.sockets.ConnectionType;

public abstract class Card {

    private short numerCode;
    /**
     *
     */
    private String category;
    /**
     *
     */
    private String eventCart;//players, game, both. JSON
    /**
     *
     */
    private String code;
    /**
     *
     */
    private short costCard;//JSON
    /**
     *
     */
    private String image;
    /**
     *
     */
    private ConnectionType owner;

    public Card(){
    }

    public Card(String code){
        this.code = code;
        this.category = code.split("@")[0];
        this.numerCode =  Short.parseShort(code.split("@")[1]);
        this.setInfo();
    }

    /**
     * Gets code.
     *
     * @return Value of code.
     */
    public String getCode() {
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
    public String getEventCart(){
        return this.eventCart;
    }

    private void setInfo(){
        ObjectMapper mapper = new ObjectMapper();

        Card [][] array = new Card[3][1];//jackson

//        switch (this.category){
//            case "HENCHMAN"{
//            }
//            case "SECRET"{
//            }
//            case "SPELL"{
//            }
//            break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + this.category);
//        }

    }

    /**
     * Sets new owner.
     *
     * @param owner New value of owner.
     */
    public void setOwner(ConnectionType owner) {
        this.owner = owner;
    }

    /**
     * Gets owner.
     *
     * @return Value of owner.
     */
    public ConnectionType getOwner() {
        return owner;
    }
}
