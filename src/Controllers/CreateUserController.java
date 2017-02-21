package Controllers;

import Core.AlertBox;
import Core.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by RudiAndre & pontusskoldon 03.02.2017.
 */
public class CreateUserController implements Initializable {


    //GUI related objects
    @FXML
    TextField create_username;
    @FXML
    PasswordField create_Password, confirm_Password;
    @FXML
    Button confirm_button;

    //class objects
    FileHandler fileHandler;
    Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileHandler = new FileHandler();
        stage = new Stage();
    }

    public void onCreateAccountClick(ActionEvent actionEvent) {

        if (!confirm_Password.getText().equals(create_Password.getText())){
            AlertBox.differentPasswords();
        }else if (confirm_Password.getText().equals(create_Password.getText())){
        fileHandler.saveCredentials(create_username.getText(), create_Password.getText());
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        }
    }
}
