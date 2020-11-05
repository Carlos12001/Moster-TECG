package model.cards;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


/**
 * This class creates cards for the game.
 */
public class Card {

    /**
     * This is the identifier of each card .
     */
    private short numerCode;

    /**
     * This is the category of the card, it could be Hencheman, spell or secret.
     */
    private String category;

    /**
     * This is the combination of numberCode and category, it is obtain from the JSON.
     */
    private String code;

    /**
     * this is the cost of maná of each card.
     */
    private short costCard;

    /**
     * This is the name of the image.
     */
    private String image;

    public Card(){
    }

    /**
     * This creator is used when the card is not created yet. It only needs the code of the card and then it sets all
     * the information from the JSON
     *
     * @param code String card code
     */
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

    /**
     * This creator is called when you have the card, if obtains the information directly from the JSON
     *
     * @param card Card
     */
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
