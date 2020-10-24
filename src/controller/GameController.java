package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.game.Game;
import model.sockets.UpdateInfo;

public class GameController {

    Game game = Game.getInstance();


    @FXML
    private Button buttonSendCart;

    @FXML
    private Button buttomSkipTurn;

    @FXML
    private Label lalbelPrueba;

    @FXML
    private void handleSend(ActionEvent event) {

    }

    private void dissableGUI(boolean setter) {
        this.buttomSkipTurn.setDisable(setter);
        this.buttomSkipTurn.setDisable(setter);
    }


    public void updateGUIMessage() {
    }


    @FXML
    private void initialize() {
        this.game = Game.getInstance();
        this.lalbelPrueba.setText(this.game.getUpdateInfo().toString());
    }

    private class  WaitUpload extends Thread {
        @Override
        public void run() {
            boolean runner = true;
            UpdateInfo oldInfo = game.getUpdateInfo();
            System.out.println(oldInfo);
            while (runner) {
                if (oldInfo.equals(game.getUpdateInfo()))
                    runner = true;
                else {
                    runner = false;
                    System.out.println(game.getUpdateInfo());
//                    updateGUIMessage();
                    try {
                        this.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
