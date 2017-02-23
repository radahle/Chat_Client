package Controllers;

import Core.AlertBox;
import Core.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Shohaib Muhammad <S300363>
 * @author Kittimasak Bunrat <s300342>
 * @author Pontus Sköld <s300377>
 * @author Rudi André Dahle <s300373>
 */
public class LoginController implements Initializable {

    // GUI related objects
    @FXML TextField hostname_TextField, port_Number_TextField, username_TextField;
    @FXML PasswordField passwordField;
    @FXML Button login_Button;
    @FXML Label create_User;
    @FXML Label login_Label;

    //Class objects
    Stage createAccountStage;
    Stage messenger;
    FileHandler fileHandler;

    /**
     * Initialize method.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        createAccountStage = new Stage();
        messenger = new Stage();
        fileHandler = new FileHandler();
        port_Number_TextField.setOnKeyTyped(event -> {
            if (event.getCharacter().matches("[0-9]")){

            }
            else event.consume();
        });
    }

    /**
     * OnLoginClick takes all credentials written by user and checks if they are valid. If hostname and portnumber is not given
     * then default values are used. If username and/or password is not correct user will receive an error-message.
     * If all requirements are met a new stage are opened.
     * @param actionEvent is used when user clicks on button.
     * @throws IOException
     */
    public void onLoginClick(ActionEvent actionEvent) throws IOException {

        if (fileHandler.readCredentials(username_TextField.getText(), passwordField.getText())){

            fileHandler.saveServerCredentials(hostname_TextField.getText(), port_Number_TextField.getText());
            messenger.setTitle("Chat Application");
            Parent root = FXMLLoader.load(getClass().getResource("../Layouts/ClientLayout.fxml"));
            root.getStylesheets().add(LoginController.class.getResource("/StyleSheets/Messenger.css").toExternalForm());
            messenger.setScene(new Scene(root, 930, 587));
            messenger.show();
            messenger.setResizable(false);
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        }else {

            AlertBox.badCredentials();
        }
    }

    /**
     * OnCreateClick opens a new stage for creation of new user
     * @param mouseEvent is used when user clicks on create account.
     * @throws IOException
     */

    public void onCreateClick(MouseEvent mouseEvent) throws IOException {

        createAccountStage.setTitle("Create Account");
        Parent root = FXMLLoader.load(getClass().getResource("../Layouts/CreateUserLayout.fxml"));
        root.getStylesheets().add(LoginController.class.getResource("/StyleSheets/CreateUser.css").toExternalForm());
        createAccountStage.setScene(new Scene(root ,600, 400));
        createAccountStage.show();
        createAccountStage.setResizable(false);


    }



}
