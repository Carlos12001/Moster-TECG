package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import model.cards.Card;
import model.cards.Henchman;
import model.cards.Secret;
import model.cards.Spell;
import model.game.Game;
import model.sockets.UpdateInfo;

import java.util.Random;


/**
 * @version 7.0
 */
public class GameController {


    /**
     *
     */
    private Game game = Game.getInstance();

    private int dissableTurn = 0;

    /**
     *
     */
    @FXML
    private Label labelRound;

    /**
     *
     */
    @FXML
    private Button buttonSkipTurn;

    /**
     *
     */
    @FXML
    private Label labelTypeConnection;

    @FXML
    private Label labelNameOtherPlayer;

    @FXML
    private ProgressBar progressBarLifeOtherPlayer;

    @FXML
    private Label labelNameThisPLayer;

    @FXML
    private ProgressBar progressBarLifeThisPLayer;

    @FXML
    private Label labelManaThisPlayer;

    @FXML
    private Label labelNumCarts;

    @FXML
    private StackPane stackPaneDeckCart;

    @FXML
    private ImageView cardD01;

    @FXML
    private ImageView cardD0;

    @FXML
    private HBox hBoxHandCard;

    @FXML
    private Button buttonPreviousCard;

    @FXML
    private ImageView cardD02;

    @FXML
    private Button buttonNextCard;

    @FXML
    private VBox vBoxTableCard;

    @FXML
    private ImageView cardD03;

    @FXML
    private ImageView cardD04;

    @FXML
    private VBox vBoxHistory;

    @FXML
    private Button buttonPreviousHistory;

    @FXML
    private Label labelSender;

    @FXML
    private Label lifeSender;

    @FXML
    private ImageView cardD05;

    @FXML
    private Label labelReciber;

    @FXML
    private Label lifeReciber;

    @FXML
    private Button buttonNextHistory;


    /**
     * @param event
     */
    @FXML
    private void doSenalDeck(MouseEvent event) {

        if (this.game.getDeckStack().getTop()>-1) {
            this.labelNumCarts.setText(this.game.getDeckStack().getTop() + 1 + "-1");
            this.labelNumCarts.setTextFill(Paint.valueOf("#E06C75"));


            if (this.game.getDeckStack().getTop() > 0) {
                this.cardD01.setRotate(10);
            }
        }
    }

    /**
     * @param event
     */
    @FXML
    private void addCardHandCard(MouseEvent event) {
        this.stackPaneDeckCart.setDisable(true);
        this.cardD0.setStyle("-fx-opacity:  0.4");

        if((this.game.getDeckStack().getTop()>-1)&&(this.game.getHandCardList().getSize()<=10)) {

            this.game.getHandCardList().insertLast(this.game.getDeckStack().pop());

            this.cardD02.setVisible(true);

            this.hBoxHandCard.setDisable(false);

            this.game.getHandCardList().setCurrentDisplayTail();

            this.labelNumCarts.setText(this.game.getDeckStack().getTop()+1+"");

            this.cardD02.setImage(new Image("/images/" +
                    this.game.getHandCardList().displayCard("current").getImage()));
        }else{
            //sonido
        }

        if (this.game.getDeckStack().getTop()<=-1){
            this.cardD0.setVisible(false);
            this.cardD01.setVisible(false);
            this.cardD0.setDisable(true);
            this.cardD0.setDisable(true);
        }
    }

    /**
     * @param event
     */
    @FXML
    private void revertSenalDeck(MouseEvent event) {
        this.labelNumCarts.setText(this.game.getDeckStack().getTop()+1 +"");
        this.labelNumCarts.setTextFill(Paint.valueOf("#000000"));
        this.cardD01.setRotate(0);
    }

    /**
     * @param event
     */
    @FXML
    private void handlePreCard(ActionEvent event){
        this.cardD02.setImage(new Image("/images/" +
                this.game.getHandCardList().displayCard("previous").getImage()));
    }

    /**
     * @param event
     */
    @FXML
    private void selectCard(MouseEvent event) {
        if (!this.game.getHandCardList().isEmpty() &&
            (this.game.getPlayer().getMana()-this.game.getHandCardList().getCurrentDisplay().getCostCard()>=0))
        {
            this.labelManaThisPlayer.setText( this.game.getPlayer().getMana() +  "- "+
                    this.game.getHandCardList().getCurrentDisplay().getCostCard()
                    + "");
            this.labelManaThisPlayer.setTextFill(Paint.valueOf("#000000"));


        }else if((this.game.getPlayer().getMana()-this.game.getHandCardList().getCurrentDisplay().getCostCard()<0)){
            this.labelManaThisPlayer.setText("X");
            this.labelManaThisPlayer.setTextFill(Paint.valueOf("#E06C75"));
        }else{
            this.labelManaThisPlayer.setText("X");
            this.labelManaThisPlayer.setTextFill(Paint.valueOf("#66d8f2"));
        }
    }

    /**
     * @param event
     */
    @FXML
    private void handleSend(MouseEvent event) {

        if (!this.game.getHandCardList().isEmpty() &&
                (this.game.getPlayer().getMana()-this.game.getHandCardList().getCurrentDisplay().getCostCard()>=0)) {
            Card current = this.game.getHandCardList().getCurrentDisplay();

            this.game.getPlayer().decreaseMana( current.getCostCard());

            this.cardD04.setImage(new Image("/images/" +
                   current.getImage()));

            //Increase Mana
            this.game.getPlayer().increaseManaTurn();

            UpdateInfo oldInfo = this.game.getUpdateInfo();
            this.dissableGUI(true);



            if (this.game.getWhoFisrt() != this.game.getTypeConexion() && this.dissableTurn<=1) {
                this.game.setRound();
            }

            //Actualiza la interfaz.
            updateGUI();


            //The code card select
            this.game.sendInfoOtherPlayer(current.getCode());

            //Delete the card of the handCard
            this.game.getHandCardList().deleteCard(current.getCode());
            this.game.getHandCardList().setDisplayCard();

            //Do the acttion Card
            this.actionCard(current.getCode(), true);

            //Sets the new image
            if(!this.game.getHandCardList().isEmpty()){
                this.cardD02.setImage(new Image("/images/" +
                        this.game.getHandCardList().displayCard("current").getImage()));
            }else {
                this.cardD02.setImage(new Image("/images/" +
                        "ReverseCards.png"));
            }

            //Listing the sockets
            this.game.recibeNewInfo();

            //Listing the GUI
            this.recibeMessage(oldInfo);
        }else {
//            this.cardD02.setImage(new Image("/images/" +
//                    "ReverseCards.png"));
        }
    }

    /**
     * @param event
     */
    @FXML
    private void unselectCard(MouseEvent event) {
        this.labelManaThisPlayer.setText(this.game.getPlayer().getMana() + "");
        this.labelManaThisPlayer.setTextFill(Paint.valueOf("#66d8f2"));
    }

    @FXML
    private void handleNextCard(ActionEvent event) {
        this.cardD02.setImage(new Image("/images/" +
                this.game.getHandCardList().displayCard("next").getImage()));

    }

    @FXML
    private void handleSkipTurn(ActionEvent event) {

        //Increase Mana
        this.game.getPlayer().increaseManaTurn();

        UpdateInfo oldInfo = this.game.getUpdateInfo();
        this.dissableGUI(true);
        if (this.game.getWhoFisrt() != this.game.getTypeConexion() && this.dissableTurn<=1) {
            this.game.setRound();
        }


        updateGUI();
        this.game.sendInfoOtherPlayer(true);
        this.game.recibeNewInfo();
        this.recibeMessage(oldInfo);
    }

    @FXML
    private void handlePreHistory(ActionEvent event) {

    }

    @FXML
    private void handleNextHistory(ActionEvent event) {

    }





    /**
     * @param setter This is boolean who blocks the GUI.
     */
    private void dissableGUI(boolean setter) {

        if (setter){
            this.cardD0.setStyle("-fx-opacity:  0.4");
            this.cardD02.setStyle("-fx-opacity: 0.4");
        }else {
            this.cardD0.setStyle("-fx-opacity:  1");
            this.cardD02.setStyle("-fx-opacity: 1");
        }

        this.cardD02.setDisable(setter);
        this.hBoxHandCard.setDisable(setter);
        this.buttonSkipTurn.setDisable(setter);
        this.stackPaneDeckCart.setDisable(setter);
    }

    /**
     *
     */
    private void recibeMessage(UpdateInfo oldInfo) {

        Thread thread = new Thread(() -> {
            Runnable updater = this::recibeMessageAux;
            while (true) {
                if (!oldInfo.equals(Game.getInstance().getUpdateInfo())){
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                    break;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     *
     */
    private void recibeMessageAux() {
        this.dissableGUI(false);
        this.updateGUI();
        this.actionCard(this.game.getCodeOtherCard(), false);
        this.blockTurn();
    }

    /**
     *
     */
    private void updateGUI() {

        //Init variables
        double db;
        UpdateInfo info = this.game.getUpdateInfo();

        //Set new Information
        this.game.setWhoFisrt(info.getWhoFirst());

        this.labelRound.setText(this.game.getRound() + "");

        this.labelNameOtherPlayer.setText(
                this.game.getUpdateInfo().getPlayerSendName());

        db= ((double) this.game.getUpdateInfo().getPlayerSendLife())/1000;
        this.progressBarLifeOtherPlayer.
                setProgress(db);

        this.labelNameThisPLayer.setText(
                this.game.getPlayer().getName());


        db= ((double) this.game.getPlayer().getLife())/1000;
        this.progressBarLifeThisPLayer.
                setProgress(db);

        this.labelManaThisPlayer.setText(
                this.game.getPlayer().getMana()+"");

        //Especial Blocks

        if(this.game.getDeckStack().getTop()<=-1){
            this.stackPaneDeckCart.setDisable(true);
            this.cardD0.setVisible(false);
            this.cardD01.setVisible(false);
        }else{
            this.stackPaneDeckCart.setDisable(false);
            this.cardD0.setVisible(true);
            this.cardD01.setVisible(true);
        }

        if (this.game.getHandCardList().isEmpty()){
            this.hBoxHandCard.setDisable(true);
            this.cardD02.setVisible(false);
        }else{
            this.hBoxHandCard.setDisable(!true);
            this.cardD02.setVisible(!false);
        }
    }

    /**
     * @param code
     */
    private void actionCard(String code, boolean sender) {
        this.cardD03.setVisible(true);
        switch (code.split("@")[0]) {
            case "HENCHEMAN" -> {
                Henchman henchman = new Henchman(code);
                if (!sender) this.cardD03.setImage(new Image("/images/" + henchman.getImage()));
                this.actionHenchman(henchman, sender);
            }
            case "SECRET" -> {
                Secret secret = new Secret(code);
                if (!sender) this.cardD03.setImage(new Image("/images/" + secret.getImage()));
            }
            case "SPELL" -> {
                Spell spell = new Spell(code);
                if (!sender) this.cardD03.setImage(new Image("/images/" + spell.getImage()));
                this.actionSpell(spell, sender);
            }
            default -> this.cardD03.setVisible(false);
        }

        this.GAMEOVER();
    }

    /**
     *
     */
    private void actionHenchman(Henchman henchman, boolean sender) {
        Game game = Game.getInstance();
        if(sender) {

            this.game.setPlayerOtherLife((int) ((double) game.getPlayerOtherLife() - henchman.getAtack()));
            this.progressBarLifeOtherPlayer.setProgress(((double)game.getPlayerOtherLife() )/ 1000);
        }else {
            game.getPlayer().decreaseLife(henchman.getAtack());
            this.progressBarLifeThisPLayer.setProgress(((double) game.getPlayer().getLife()) / 1000);
        }
    }


    /**
     * @param secret
     * @param sender
     */
    private void actionSecret(Secret secret, boolean sender) {
    }

    /**
     * @param spell
     * @param sender
     */
    private void actionSpell(Spell spell, boolean sender) {
        short numCode = spell.getNumerCode();
        switch (numCode) {
            case 0 -> {
                if (!sender) {
                    this.dissableTurn = spell.getShifts();
                }
            }
            case 1 -> {
                if(sender) {
                    game.getPlayer().increaseLife(spell.getHealth());
                    this.progressBarLifeThisPLayer.setProgress(((double) game.getPlayer().getLife()) / 1000);

                }else {

                    this.game.setPlayerOtherLife((int) ((double) game.getPlayerOtherLife() + spell.getHealth()));
                    this.progressBarLifeOtherPlayer.setProgress(((double)game.getPlayerOtherLife() )/ 1000);
                }
            }
            case 2 -> {
                if (!sender) {
                    this.dissableTurn = spell.getShifts();
                }
            }
            case 3 -> {
                if(sender){
                    if((this.game.getHandCardList().getSize()<=10)) {
                        Random rnd = new Random();
                        Card cd;

                        switch (rnd.nextInt(3)) {
                            case 0 -> cd = new Henchman("HENCHMAN@"+rnd.nextInt(10));
                            case 1 -> cd = new Secret("SPELL@"+rnd.nextInt(10));
                            case 2 -> cd = new Spell("SPELL@"+rnd.nextInt(10));
                            default -> throw new IllegalStateException("Unexpected value: " + rnd.nextInt(3));
                        }
                        this.game.getHandCardList().insertLast(cd);

                        this.game.getHandCardList().setCurrentDisplayTail();

                        this.cardD02.setImage(new Image("/images/" +
                                this.game.getHandCardList().displayCard("current").getImage()));
                    }
                }else{
                    if((this.game.getDeckStack().getTop()>-1)) {
                        this.game.getHandCardList().insertLast(this.game.getDeckStack().pop());
                        this.labelNumCarts.setText(this.game.getDeckStack().getTop()+1 +"");
                        this.labelNumCarts.setTextFill(Paint.valueOf("#000000"));
                        this.cardD01.setRotate(0);
                    }
                }
            }
            case 4 -> {
                if(sender){
                    this.game.getPlayer().increaseMana(1200);

                    game.getPlayer().decreaseLife((int) (0.4 * this.game.getPlayer().getLife()));
                    this.progressBarLifeThisPLayer.setProgress(((double) game.getPlayer().getLife()) / 1000);
                }else{
                    this.game.setPlayerOtherMana(1000);

                    this.game.setPlayerOtherLife(game.getPlayerOtherLife() - (int) (0.4 * this.game.getPlayerOtherLife()));
                    this.progressBarLifeOtherPlayer.setProgress(((double)game.getPlayerOtherLife() )/ 1000);
                }
            }
            case 5 -> {
                this.game.setPlayerOtherLife((int) ((double)game.getPlayerOtherLife() - (double)game.getPlayerOtherLife()*0.3));
                this.progressBarLifeOtherPlayer.setProgress(((double)game.getPlayerOtherLife() )/ 1000);


                game.getPlayer().decreaseLife((int) (this.game.getPlayer().getLife()*0.3));
                this.progressBarLifeThisPLayer.setProgress(((double) game.getPlayer().getLife()) / 1000);
            }
            case 6 -> {
                double more;
                if(sender){

                    more = (double) game.getPlayerOtherLife() * 0.2;

                    //Me sumo la vida
                    game.getPlayer().increaseLife((int) more);
                    this.progressBarLifeThisPLayer.setProgress(((double) game.getPlayer().getLife()) / 1000);

                    //Le quito la vida
                    this.game.setPlayerOtherLife((int) (this.game.getPlayerOtherLife()-more));


                }else{
                    more = (double) game.getPlayer().getLife() * 0.2;

                    //Me quito la vida que me quito el otro
                    game.getPlayer().decreaseLife((int) more);
                    this.progressBarLifeThisPLayer.setProgress(((double) game.getPlayer().getLife()) / 1000);

                    //Le sumo la vida que me quito el otro jugador
                    this.game.setPlayerOtherLife((int) (this.game.getPlayerOtherLife()+more));
                }
                this.progressBarLifeOtherPlayer.setProgress( (double) (this.game.getPlayerOtherLife())/1000);
            }
            case 7 -> {
                if (sender) {
                    this.game.getPlayer().setMultiplier(0.3);
                }
            }
            case 8 -> {
                if(sender){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Mana del otro Jugador");
                    alert.setContentText("El ahorita tiene un mana de " + this.game.getPlayerOtherMana());
                    alert.showAndWait();
                }
            }
            case 9 -> {
                if(sender){
                    this.game.getPlayer().increaseMana(40);
                    this.labelManaThisPlayer.setText(String.valueOf(this.game.getPlayer().getMana()));
                }else{
                    this.game.setPlayerOtherMana(this.game.getPlayerOtherMana()+40);
                }
            }
        }
    }


    private void blockTurn(){
        if(this.dissableTurn>0){
            this.handleSkipTurn(new ActionEvent());
            --this.dissableTurn;
        }
    }

    private void GAMEOVER(){
        int me  = this.game.getPlayer().getLife();
        int other = this.game.getPlayerOtherLife();

        if (me<=0){
            this.game.finishConexion();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("¡GAME OVER!");
            alert.setContentText("Has perdido contra: " + this.game.getPlayerOtherName());
            alert.showAndWait();
        }else if(other<=0){
            this.game.finishConexion();
            this.game.finishConexion();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("¡GAME OVER!");
            alert.setContentText("¡Has gandado contra!: " + this.game.getPlayerOtherName());
            alert.showAndWait();
        }else {
            //NO PASA NADA
        }
    }



    /**
     *
     */
    @FXML
    private void initialize() {

        //Init variables
        double db;

        //Init Atributtes
        this.game = Game.getInstance();
        UpdateInfo info = this.game.getUpdateInfo();
        this.game.setWhoFisrt(info.getWhoFirst());

        //Init HandCard, DeckCard and History.
        this.game.initHandCard();

        this.cardD02.setImage(new Image("/images/" +
                this.game.getHandCardList().displayCard("current").getImage()));

        this.game.initDeckCard();

        //  SetsInformation

        this.labelRound.setText(this.game.getRound() + "" );

        this.labelNameOtherPlayer.setText(
                this.game.getUpdateInfo().getPlayerSendName());

        db= ((double) this.game.getUpdateInfo().getPlayerSendLife())/1000;
        this.progressBarLifeOtherPlayer.
                setProgress(db);

        this.labelNameThisPLayer.setText(
                this.game.getPlayer().getName());


        db= ((double) this.game.getPlayer().getLife())/1000;
        this.progressBarLifeThisPLayer.
                setProgress(db);

        this.labelManaThisPlayer.setText(
                this.game.getPlayer().getMana()+"");

        this.labelTypeConnection.setText(
                this.game.getTypeConexion() + "");
        this.labelNumCarts.setText(
                this.game.getDeckStack().getTop()+1 + "");

        if  (game.getWhoFisrt() == this.game.getTypeConexion()) {
            this.dissableGUI(false);
        } else{
            this.dissableGUI(true);
            this.recibeMessage(this.game.getUpdateInfo());
            this.game.recibeNewInfo();
        }
    }

}
