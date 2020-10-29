package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.game.Game;
import model.sockets.UpdateInfo;

import java.text.DecimalFormat;

/**
 *
 */
public class GameController {


    /**
     *
     */
    Game game = Game.getInstance();

    @FXML
    private TextArea lalbelPrueba;

    @FXML
    private Button buttonSendCart;

    @FXML
    private Button buttonSkipTurn;

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
    private ImageView cardD1;

    @FXML
    private ImageView cardD0;

    /**
     * @param event
     */
    @FXML
    private void addCardHandCard(MouseEvent event) {
        this.stackPaneDeckCart.setDisable(true);
        this.cardD0.setStyle("-fx-opacity:  0.4");
        if((this.game.getDeckStack().getTop()>-1)) {
            System.out.println(this.game.getDeckStack().pop().getCode());
            this.labelNumCarts.setText(this.game.getDeckStack().getTop()+1+"");


            ///AGREGAR A LA HAND CARD


        }

        if (this.game.getDeckStack().getTop()<=-1){
            this.cardD0.setVisible(false);
            this.cardD1.setVisible(false);
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
                this.cardD1.setRotate(10);
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
        this.cardD1.setRotate(0);
    }

    /**
     * @param event
     */
    @FXML
    private void handleSend(ActionEvent event) {

        //logica si tiene mana suficiente

        UpdateInfo oldInfo = this.game.getUpdateInfo();
        this.dissableGUI(true);
        //ENVIAR LA CARTA AL CENTRO CON DELETECARD
        if  ( this.game.getWhoFisrt() != this.game.getTypeConexion()) {
            this.game.setRound();
            //Agrega el historial la jugada
            updateGUI();
        }
        this.game.sendInfoOtherPlayer("");//obtener current code
        this.game.recibeNewInfo();
        this.recibeMessage(oldInfo);
    }

    @FXML
    private void handleSkipTurn(ActionEvent event) {

        //logica si tiene mana suficiente

        UpdateInfo oldInfo = this.game.getUpdateInfo();
        this.dissableGUI(true);
        if  ( this.game.getWhoFisrt() != this.game.getTypeConexion()) {
            this.game.setRound();
            //Agrega el historial la jugada
            updateGUI();
        }
        this.game.sendInfoOtherPlayer(true);
        this.game.recibeNewInfo();
        this.recibeMessage(oldInfo);

    }

    /**
     * @param setter This is boolean who blocks the GUI.
     */
    private void dissableGUI(boolean setter) {
        if (setter){
            this.cardD0.setStyle("-fx-opacity:  0.4");
        }else {
            this.cardD0.setStyle("-fx-opacity:  1");
        }

        this.buttonSendCart.setDisable(setter);
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
    private void recibeMessageAux(){
        this.dissableGUI(false);
        //metodos de recibir carta
        this.updateGUI();
    }

    /**
     *
     */
    private void updateGUI() {
        UpdateInfo info = this.game.getUpdateInfo();
        this.game.setWhoFisrt(info.getWhoFirst());
        this.lalbelPrueba.setText(this.game.getPlayerOtherName() + "\n" +
                this.game.getPlayerOtherLife() + "\n" +
                this.game.getPlayerOtherMana() + "\n" +
                this.game.getRound() );
    }

    /**
     *
     */
    @FXML
    private void initialize() {
        this.game = Game.getInstance();
        Double db;
        UpdateInfo info = this.game.getUpdateInfo();

        this.game.setWhoFisrt(info.getWhoFirst());
        ///handcart
        this.game.initDeck();


        this.lalbelPrueba.setText(info.getPlayerSendName() + "\n" +
                info.getPlayerSendLife() + "\n" +
                info.getPlayerSendMana() + "\n" +
                this.game.getRound() );

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
