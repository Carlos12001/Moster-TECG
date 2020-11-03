package model.cards;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


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

    public Card(String code) {


        this.code = code;
        this.category = code.split("@")[0];
        this.numerCode = Short.parseShort(code.split("@")[1]);


        ObjectMapper mapper = new ObjectMapper();
        File jackson;
        jackson = new File(System.getProperty("user.dir") + "/src/data/Cards.json");
        Card[][] arrayCard;

        try {
            arrayCard = mapper.readValue(jackson, Card[][].class);
            Card setter;
            switch (this.category) {
                case "HENCHEMAN" -> {
                    setter = arrayCard[0][this.numerCode];
                }
                case "SECRET" -> {
                    setter = arrayCard[1][this.numerCode];
                }
                case "SPELL" -> {
                    setter = arrayCard[2][this.numerCode];
                }
                default -> throw new IllegalStateException("Unexpected value: " + this.category);
            }
            this.image = setter.image;
            this.costCard = setter.costCard;

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Card(Card card){
        this.code = card.code;
        this.category = card.code.split("@")[0];
        this.numerCode =  Short.parseShort(card.code.split("@")[1]);
        this.costCard = card.costCard;
        this.image = card.image;
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
