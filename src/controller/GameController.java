package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.game.Game;
import model.sockets.UpdateInfo;

public class GameController {


    /**
     *
     */
    Game game = Game.getInstance();

    /**
     *
     */
    @FXML
    private Button buttonSendCart;

    /**
     *
     */
    @FXML
    private Button buttomSkipTurn;

    @FXML
    private TextArea lalbelPrueba;

    /**
     *
     */
    @FXML
    public Label labelPLayerName;

    /**
     *
     */
    @FXML
    private Label labelTypeConnection;


    /**
     * @param event
     */
    @FXML
    private void handleSend(ActionEvent event) {

        //logica si tiene mana suficiente



        UpdateInfo oldInfo = this.game.getUpdateInfo();
        this.dissableGUI(true);
        if  ( game.getWhoFisrt() != this.game.getTypeConexion()) {
            game.setRound();
            //Agrega el historial la jugada
            updateGUI();
        }
        this.game.sendInfoOtherPlayer("");
        this.game.recibeNewInfo();
        this.recibeMessage(oldInfo);
    }

    /**
     * @param setter This is boolean who blocks the GUI.
     */
    private void dissableGUI(boolean setter) {
        this.buttonSendCart.setDisable(setter);
        this.buttomSkipTurn.setDisable(setter);
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

    private void recibeMessageAux(){
        this.dissableGUI(false);
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

    @FXML
    private void initialize() {
        this.game = Game.getInstance();
        this.labelPLayerName.setText(this.game.getPlayer().getName());
        this.labelTypeConnection.setText(this.game.getTypeConexion() + "");
        UpdateInfo info = this.game.getUpdateInfo();
        this.game.setWhoFisrt(info.getWhoFirst());


        this.lalbelPrueba.setText(info.getPlayerSendName() + "\n" +
                info.getPlayerSendLife() + "\n" +
                info.getPlayerSendMana() + "\n" +
                this.game.getRound() );

        if  (game.getWhoFisrt() == this.game.getTypeConexion()) {
            this.dissableGUI(false);
        } else{
            this.dissableGUI(true);
            this.recibeMessage(this.game.getUpdateInfo());
            this.game.recibeNewInfo();
        }
    }

}
