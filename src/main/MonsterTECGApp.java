package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MonsterTECGApp extends Application {

    /**
     * Es la Ventana del programa, cuando inicie el programa hace un set en start.
     */
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

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
            window.setScene(scene);
            window.setTitle("Monster TECG");
            window.getIcons().add(new Image("/resource/icon.png"));
            window.setResizable(false);
            window.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Retorna la ventana de la ejecución
     * @return Ventana del programa
     */
    public Stage getWindow() {
        return window;
    }
}
