package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.sockets.ConnectionType;
import model.game.*;
import model.sockets.UpdateInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *This class controller all the MenuView of the game.
 *
 * @version 1.0
 */
public class MenuController {


    /**
     * game is the singleton, the game is only created once.
     */
    private  Game game;

    /**
     * This is a FXML VBox of the menuview that contains the firt view of the menu.
     */
    @FXML
    private VBox vBoxSelect;

    /**
     * This is a FXML VBox of the menuview that shows when the player select to create a game.
     */
    @FXML
    private VBox vBoxCreate;

    /**
     * This is a FXML VBox of the menuview that shows when the player select to join a game.
     */
    @FXML
    private VBox vBoxJoin;

    /**
     * This is a FXML label of the menuview that shows the port of the server.
     */
    @FXML
    private Label labelPortServer;

    /**
     * This is a FXML label of the menuview that shows the IP of the server.
     */
    @FXML
    private Label labelIPServer;

    /**
     * This is a FXML TextField of the menuview that is the input of the IP in the client.
     */
    @FXML
    private TextField textFieldIp;

    /**
     * This is a FXML TextField of the menuview that is the input of the playe's name.
     */
    @FXML
    private TextField textFieldName;

    /**
     * This is a FXML TextField of the menuview that is the input of the port number.
     */
    @FXML
    private TextField textFieldPuerto;

    /**
     * This is a FXML Button of the menuview that initiates the game.
     */
    @FXML
    private Button buttonInit;


    /**
     * When the player select to create a game, this method will create the server and wait for a client.
     *
     * @param event type ActionEvent
     */
    @FXML
    private void handleCreateServer(ActionEvent event) {

        this.game = Game.getInstance(new Player(this.textFieldName.getText()), ConnectionType.SERVER);
        this.game.setTypeConexion(ConnectionType.SERVER);

        this.vBoxSelect.setVisible(false);
        this.vBoxSelect.setDisable(true);
        this.vBoxCreate.setVisible(true);
        this.vBoxCreate.setDisable(false);

        this.game.createConnection();
        this.labelIPServer.setText(this.game.getServer().getIp()+"");
        this.labelPortServer.setText(this.game.getServer().getPort()+"");

        this.updateGUIMessage();
    }

    /**
     * When the player select to join a game, this method will connect to the server.
     *
     * @param event The event button for to joinGame
     */
    @FXML
    private void handleJoinServer(ActionEvent event) {

        this.game = Game.getInstance(new Player(this.textFieldName.getText()), ConnectionType.CLIENT);
        this.game.setTypeConexion(ConnectionType.CLIENT);

        this.vBoxSelect.setVisible(false);
        this.vBoxSelect.setDisable(true);
        this.vBoxJoin.setVisible(true);
        this.vBoxJoin.setDisable(false);
    }

    /**
     * When the client press the init button, this method will create the first connection and verifies if the game
     * exist.
     *
     * @param event type ActionEvent
     */
    @FXML
    private void handleInitGame(ActionEvent event) {
        this.buttonInit.setDisable(true);
        int port;
        try {
            port = Integer.parseInt(this.textFieldPuerto.getText());
            try {
                this.game.createConnection(port,this.textFieldIp.getText());
                this.updateGUIMessage();
                this.game.recibeNewInfo();
            }catch (Exception e){
                this.buttonInit.setDisable(false);
                this.vBoxJoin.setDisable(true);
                this.vBoxJoin.setVisible(false);
                this.vBoxSelect.setVisible(true);
                this.vBoxSelect.setDisable(false);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("No existe la partida");
                alert.setContentText("Has tratado unirte a una partida que no existe, vuelve a intentarlo. ");
                alert.showAndWait();
            }

        }catch (NumberFormatException ex) {
            this.buttonInit.setDisable(false);
            this.vBoxJoin.setDisable(true);
            this.vBoxJoin.setVisible(false);
            this.vBoxSelect.setVisible(true);
            this.vBoxSelect.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("No existe la partida");
            alert.setContentText("Has tratado unirte a una partida que no existe, vuelve a intentarlo. ");
            alert.showAndWait();
        }
    }

    /**
     * This method is a thread tha waits for the connection of the client. It compares the old information with the new
     * one.
     */
    private void updateGUIMessage() {
        Thread thread = new Thread(() -> {
            Runnable updater = () -> {
                openGameView();
                if(Game.getInstance().getTypeConexion()==ConnectionType.SERVER){
                    Game.getInstance().sendInfoOtherPlayer("");
                }
            };
            UpdateInfo oldInfo = Game.getInstance().getUpdateInfo();
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
     * This methos opens the gameView.fxml
     */
    private void openGameView() {
        try {
            ((Stage) this.labelIPServer.getScene().getWindow()).
                    setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/GameView.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    private void initialize() {
        try {
            this.textFieldIp.setPromptText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
