package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 *
 */
public class MenuController {


    /**Value injected by FXMLLoader
     * fx:id="vBoxselect"
     */
    @FXML
    private VBox vBoxSelect;

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
        this.vBoxSelect.setVisible(false);
        this.vBoxSelect.setDisable(true);
        this.vBoxCreate.setVisible(!false);
        this.vBoxCreate.setDisable(!true);
    }

    /**
     * @param event
     */
    @FXML
    private void handleJoinServer(ActionEvent event) {
        this.vBoxSelect.setVisible(false);
        this.vBoxSelect.setDisable(true);
        this.vBoxJoin.setVisible(!false);
        this.vBoxJoin.setDisable(!true);
    }

    /** This method is called by the FXMLLoader when initialization is complete
     *
     */
    @FXML
    private void initialize() {
    }
}
