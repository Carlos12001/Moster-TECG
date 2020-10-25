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
    private TextArea lalbelPrueba;

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

}
