package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.game.Conextion;
import model.game.Game;
import model.game.Player;
import model.sockets.CreateServer;
import model.sockets.JoinServer;

/**
 *This class controller all the MenuView of the game
 * @author Carlos Andrés Mata Calderón
 * @version 1.0
 */
public class MenuController {

    private  Game game;

    /**Value injected by FXMLLoader
     * fx:id="vBoxselect"
     */
    @FXML
    private VBox vBoxSelect;

    /**Value injected by FXMLLoader
     * fx:id="textFieldName"
     */
    @FXML
    private TextField textFieldName;

    /**Value injected by FXMLLoader
     * fx:id="vBoxCreate"
     */
    @FXML
    private VBox vBoxCreate;

    /**Value injected by FXMLLoader
     * fx:id="labelPortServer"
     */
    @FXML
    private Label labelPortServer;

    /**Value injected by FXMLLoader
     * fx:id="labelIPServer"
     */
    @FXML
    private Label labelIPServer;

    /**Value injected by FXMLLoader
     * fx:id="vBoxJoin"
     */
    @FXML
    private VBox vBoxJoin;

    /**Value injected by FXMLLoader
     * fx:id="textFieldIp"
     */
    @FXML
    private TextField textFieldIp;

    /**Value injected by FXMLLoader
     * fx:id="textFieldPuerto"
     */
    @FXML
    private TextField textFieldPuerto;


    /**
     * @param event
     */
    @FXML
    private void handleCreateServer(ActionEvent event) {

        this.game = Game.getInstance(new Player(this.textFieldName.getText()), Conextion.SERVER);

        this.vBoxSelect.setVisible(false);
        this.vBoxSelect.setDisable(true);
        this.vBoxCreate.setVisible(!false);
        this.vBoxCreate.setDisable(!true);

        CreateServer Server = new CreateServer();
        Thread serverThread = new Thread(Server);
        serverThread.start();
        labelPortServer.setText("Su puerto es: " + Server.getPort()); // esto es un error ne se actualiza
    }

    /**
     * @param event
     */
    @FXML
    private void handleJoinServer(ActionEvent event) {

        this.game = Game.getInstance(new Player(this.textFieldName.getText()), Conextion.CLIENT);

        this.vBoxSelect.setVisible(false);
        this.vBoxSelect.setDisable(true);
        this.vBoxJoin.setVisible(!false);
        this.vBoxJoin.setDisable(!true);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleInitGame(ActionEvent event) {
        JoinServer client = new JoinServer(Integer.parseInt(this.textFieldPuerto.getText()), this.textFieldIp.getText());
        client.connectToServer();
    }

    /** This method is called by the FXMLLoader when initialization is complete
     *
     */
    @FXML
    private void initialize() {
    }

}
