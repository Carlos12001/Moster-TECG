package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.game.ConnectionType;
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
        ConnectionType typeC = game.getTypeConexion();

        switch (typeC){
            case SERVER ->{
                System.out.println("Soy servidor");
            }
            case CLIENT -> {
                System.out.println("Soy cliente");

            }
        }
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
    public void updateGUIMessage() {
    }

    @FXML
    private void initialize() {
        this.game = Game.getInstance();
        UpdateInfo info = this.game.getUpdateInfo();
        this.game.setWhoFisrt(info.getWhoFirst());

        this.lalbelPrueba.setText(info.getPlayerSendName() + "\n" +
                info.getPlayerSendLife() + "\n" +
                info.getPlayerSendMana() + "\n" +
                this.game.getTypeConexion() + "\n" +
                this.game.getRound() );

        ConnectionType typeC = game.getWhoFisrt();

        if ((typeC == ConnectionType.SERVER) && (this.game.getTypeConexion()==ConnectionType.SERVER)) {
            System.out.println("Empiezo el " + typeC);
            this.dissableGUI(false);
        } else if ((typeC == ConnectionType.CLIENT) && (this.game.getTypeConexion()==ConnectionType.CLIENT)){
            System.out.println("Empiezo el " + typeC);
            this.dissableGUI(false);
        }else{
            System.out.println("NO empiezo ");
            this.dissableGUI(true);
        }
    }

}
