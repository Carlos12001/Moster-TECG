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
import model.handcard.HandCardList;
import model.sockets.UpdateInfo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * @version 7.0
 */
public class GameController {


    /**
     *
     */
    Game game = Game.getInstance();

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
    private void addCardHandCard(MouseEvent event) {
        this.stackPaneDeckCart.setDisable(true);
        this.cardD0.setStyle("-fx-opacity:  0.4");

        if((this.game.getDeckStack().getTop()>-1)&&(this.game.getHandCardList().getSize()<=10)) {


            this.game.getHandCardList().insertLast(this.game.getDeckStack().pop());

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
    private void revertSenalDeck(MouseEvent event) {
        this.labelNumCarts.setText(this.game.getDeckStack().getTop()+1 +"");
        this.labelNumCarts.setTextFill(Paint.valueOf("#000000"));
        this.cardD01.setRotate(0);
    }

    @FXML
    private void handlePreCard(ActionEvent event){
        this.cardD02.setImage(new Image("/images/" +
                this.game.getHandCardList().displayCard("previous").getImage()));
    }

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

            this.game.getPlayer().setMana(this.game.getPlayer().getMana()-this.game.getHandCardList().getCurrentDisplay().getCostCard());


            UpdateInfo oldInfo = this.game.getUpdateInfo();
            this.dissableGUI(true);


            if (this.game.getWhoFisrt() != this.game.getTypeConexion()) {
                this.game.setRound();
                //Agrega el historial la jugada
                updateGUI();
            }
            this.game.sendInfoOtherPlayer("");//obtener current code
            this.game.recibeNewInfo();
            this.recibeMessage(oldInfo);
        }
    }

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

        if(true) { //logica si tiene mana suficiente

            //Agrega el historial la jugada

            UpdateInfo oldInfo = this.game.getUpdateInfo();
            this.dissableGUI(true);
            if (this.game.getWhoFisrt() != this.game.getTypeConexion()) {
                this.game.setRound();
                updateGUI();
            }
            this.game.sendInfoOtherPlayer(true);
            this.game.recibeNewInfo();
            this.recibeMessage(oldInfo);
        }else{
            //sound Nop
        }
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
        //metodos de recibir carta
        this.updateGUI();
    }

    /**
     *
     */
    private void updateGUI() {

        //Init variables
        Double db;
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
    }

    /**
     * @param category
     * @param code
     */
    private void actionCard(String category, String code) {
        switch (category){
            case "HENCHEMAN" :
                Henchman henchman = new Henchman(code);
                this.actionHenchman(henchman);
            case "SECRET" :
                Card secret = new Secret(code);
            case "SPELL" :
                Card spell = new Spell(code);
        }
    }

    /**
     *
     */
    private void actionHenchman(Henchman henchman) {
        Game game = Game.getInstance();
        game.getPlayer().decreaseLife(henchman.getAtack());
    }



    /**
     *
     */
    @FXML
    private void initialize() {

        //Init variables
        Double db;

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
