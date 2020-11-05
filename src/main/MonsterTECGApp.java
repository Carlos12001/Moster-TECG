package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the main class which extends from javaFX application,
 * this is the first class that is executed.
 */
public class MonsterTECGApp extends Application {



    /**
     * This is the main stage.
     */
    private Stage window;

    public static void main(String[] args) {

        launch(args);
    }

    /**
     * This method creates the stage and scene of the menu.
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        this.window = primaryStage;
        try {
            //Cargo la vista
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MonsterTECGApp.class.getResource("/view/MenuView.fxml"));

            // Cargo la ventana
            Pane ventana = (Pane) loader.load();

            // Cargo el scene
            Scene scene = new Scene(ventana);

            // Seteo la scene y la muestro
            this.window.setScene(scene);
            this.window.setTitle("Monster TECG");
            this.window.getIcons().add(new Image("/images/icon.png"));
            this.window.setResizable(false);
            this.window.show();


        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * Gets the window.
     *
     * @return window
     */
    public Stage getWindow() {
        return this.window;
    }
}
