package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.game.ConnectionType;
import model.game.Game;
import model.game.Player;

/**
 *This class controller all the MenuView of the game
 * @author Carlos Andrés Mata Calderón
 * @version 1.0
 */
public class MenuController {


    /**
     * The isinstance for the game.
     */
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

        this.game = Game.getInstance(new Player(this.textFieldName.getText()), ConnectionType.SERVER);

        this.vBoxSelect.setVisible(false);
        this.vBoxSelect.setDisable(true);
        this.vBoxCreate.setVisible(true);
        this.vBoxCreate.setDisable(false);

        this.game.createConnection();
        this.labelIPServer.setText(this.game.getServer().getIp()+"");
        this.labelPortServer.setText(this.game.getServer().getPort()+"");



    }

    /**
     * @param event
     */
    @FXML
    private void handleJoinServer(ActionEvent event) {

        this.game = Game.getInstance(new Player(this.textFieldName.getText()), ConnectionType.CLIENT);

        this.vBoxSelect.setVisible(false);
        this.vBoxSelect.setDisable(true);
        this.vBoxJoin.setVisible(true);
        this.vBoxJoin.setDisable(false);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleInitGame(ActionEvent event) {
        int port = 0;
                try {
                    port = Integer.parseInt(this.textFieldPuerto.getText());
                    this.game.createConnection(port,this.textFieldIp.getText());
                }catch (NumberFormatException ex){
                    System.out.println(ex.getMessage());
                }

    }

    /** This method is called by the FXMLLoader when initialization is complete
     *
     */
    @FXML
    private void initialize() {
    }

}
