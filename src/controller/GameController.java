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

    /**
     *
     */
    @FXML
    private TextArea lalbelPrueba;


    /**
     * @param event
     */
    @FXML
    private void handleSend(ActionEvent event) {
        UpdateInfo oldInfo = this.game.getUpdateInfo();
        this.dissableGUI(true);
        if  ( game.getWhoFisrt() != this.game.getTypeConexion()) {
            System.out.println("Cambie de turno soy " + this.game.getTypeConexion());
            game.setRound();
            //Agrega el historial la jugada
            updateGUI();
        }
//      this.game.sendInfoOtherPlayer((short) 0); Envio el mensaje
        this.recibeMessage(oldInfo);
    }

    /**
     * @param setter
     */
    private void dissableGUI(boolean setter) {
        this.buttonSendCart.setDisable(setter);
        this.buttomSkipTurn.setDisable(setter);
    }

    /**
     *
     */
    private void recibeMessage(UpdateInfo oldInfo) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        updateGUI();
                    }
                };
                boolean runner = true;
                while (runner) {
                    if (!oldInfo.equals(Game.getInstance().getUpdateInfo())){
                        // UI update is run on the Application thread
                        Platform.runLater(updater);
                        runner = false;
                        break;
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     *
     */
    private void updateGUI() {
        UpdateInfo info = this.game.getUpdateInfo();
        this.game.setWhoFisrt(info.getWhoFirst());

        this.lalbelPrueba.setText(info.getPlayerSendName() + "\n" +
                info.getPlayerSendLife() + "\n" +
                info.getPlayerSendMana() + "\n" +
                this.game.getRound() );

    }

    @FXML
    private void initialize() {
        this.game = Game.getInstance();
        UpdateInfo info = this.game.getUpdateInfo();
        this.game.setWhoFisrt(info.getWhoFirst());

        this.lalbelPrueba.setText(info.getPlayerSendName() + "\n" +
                info.getPlayerSendLife() + "\n" +
                info.getPlayerSendMana() + "\n" +
                this.game.getRound() );

        if  (game.getWhoFisrt() == this.game.getTypeConexion()) {
            System.out.println("Empiezo el " + this.game.getTypeConexion());
            this.dissableGUI(false);
        } else{
            System.out.println("NO empiezo " + this.game.getTypeConexion());
            this.dissableGUI(true);
//            this.game.recibeNewInfo();
        }
    }

}
