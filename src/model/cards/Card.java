package model.cards;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Card {

    private short numerCode;
    /**
     *
     */
    private String category;
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
     * Gets numerCode.
     *
     * @return Value of numerCode.
     */
    public short getNumerCode() {
        return numerCode;
    }

    /**
     * Gets image.
     *
     * @return Value of image.
     */
    public String getImage() {
        return image;
    }
}
