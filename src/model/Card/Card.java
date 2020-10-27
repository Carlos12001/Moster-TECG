package model.Card;

public class Card {
    private String id;
    private String category;
    private short powerAtack;
    private short costCard;

    public Card(String category, String id){
        this.category = category;
        this.id = id;
    }

    public void setPowerAtack(short powerAtack){
        this.powerAtack= powerAtack;
    }

    public short getPowerAtack(){
        return this.powerAtack;
    }

    public void setCostCard(short costCard){
        this.costCard = costCard;
    }

    public String getId(){
        return this.id;
    }

    public String getCategory(){
        return this.category;
    }

}
