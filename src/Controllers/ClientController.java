package Controllers;

import Core.Client;
import Core.ClientService;
import Core.HostCredentials;
import Core.MediaPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Shohaib Muhammad <S300363>
 * @author Kittimasak Bunrat <s300342>
 * @author Pontus Sköld <s300377>
 * @author Rudi André Dahle <s300373>
 */
public class ClientController implements Initializable {

    // GUI related objects
    @FXML TextArea chat_window;
    @FXML ListView clients_list;
    @FXML TextField textField_OutputText;
    @FXML MenuButton status_button;
    @FXML Button sendTxt_button;
    @FXML Label clientName_Label;


    // Data field
    private String hostName;
    private int portNumber;


    // Class related objects
    Client client;
    HostCredentials hostCredentials;

    /**
     * Initialize method.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hostCredentials = new HostCredentials();
        hostCredentials.getServerCredentials();
        hostName = hostCredentials.getHostName();
        portNumber = hostCredentials.getPortNumber();


        client = new Client(hostName, portNumber, clients_list, chat_window, textField_OutputText, sendTxt_button);
        try {
            client.start();
            clientName_Label.setText("You are: " + String.valueOf(client.getClientPort()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


}
