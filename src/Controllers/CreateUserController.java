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
 * @author Shohaib Muhammad <S300363>
 * @author Kittimasak Bunrat <s300342>
 * @author Pontus Sköld <s300377>
 * @author Rudi André Dahle <s300373>
 */
public class CreateUserController implements Initializable {


    //GUI related objects
    @FXML
    TextField create_username;
    @FXML
    PasswordField create_Password, confirm_Password;
    @FXML
    Button confirm_button;
    @FXML Label create_Label;

    //class objects
    FileHandler fileHandler;
    Stage stage;

    /**
     * Initialize method.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileHandler = new FileHandler();
        stage = new Stage();
    }

    /**
     * onCreateAccountClick checks that the same password has been written twice and creates an account.
     * Information is stored localy inside the server.
     * @param actionEvent actionevent is used when user clicks on button.
     */
    public void onCreateAccountClick(ActionEvent actionEvent) {

        if (!confirm_Password.getText().equals(create_Password.getText())){
            AlertBox.differentPasswords();
        }else if (confirm_Password.getText().equals(create_Password.getText())){
        fileHandler.saveCredentials(create_username.getText(), create_Password.getText());
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        }
    }
}
