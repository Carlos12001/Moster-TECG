package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;

/**
 *
 */
public class MonsterTECGApp extends Application {

//    public final static Logger logger = Logger.getLogger(MonsterTECGApp.class);

    /**
     * Es la Ventana del programa, cuando inicie el programa hace un set en start.
     */
    private Stage window;

    public static void main(String[] args) {
//        BasicConfigurator.configure();
        //PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\test\\configlog.properties" );
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

//        logger.info("Inizable Game");
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
            window.getIcons().add(new Image("/images/icon.png"));
            window.setResizable(false);
            window.show();
        } catch (IOException e) {
//            logger.error(e.getMessage());
        }
    }

    /**Retorna la ventana de la ejecución
     * @return Ventana del programa
     */
    public Stage getWindow() {
        return window;
    }
}
