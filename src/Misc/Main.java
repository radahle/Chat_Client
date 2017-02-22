package Misc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Shohaib Muhammad <S300363>
 * @author Kittimasak Bunrat <s300342>
 * @author Pontus Sköld <s300377>
 * @author Rudi André Dahle <s300373>
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Layouts/LoginLayout.fxml"));
        root.getStylesheets().add(Main.class.getResource("/StyleSheets/Messenger.css").toExternalForm());
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 800, 550));
        primaryStage.show();
    }
}
