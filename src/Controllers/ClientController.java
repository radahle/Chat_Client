package Controllers;

import Core.Client;
import Core.ClientService;
import Core.HostCredentials;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by RudiAndre & pontusskold on 03.02.2017.
 */
public class ClientController implements Initializable {

    // GUI related objects
    @FXML TextArea chat_window;
    @FXML ListView clients_list;
    @FXML TextField textField_OutputText;
    @FXML MenuButton status_button;
    @FXML Button sendTxt_button;

    // Data field
    private String hostName;
    private int portNumber;

    // Class related objects
    ClientService cs;
    Client client;
    HostCredentials hostCredentials;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hostCredentials = new HostCredentials();
        hostCredentials.getServerCredentials();
        hostName = hostCredentials.getHostName();
        portNumber = hostCredentials.getPortNumber();

        client = new Client(hostName, portNumber, clients_list, chat_window, textField_OutputText);
        try {
			client.start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

/*
        textField_OutputText.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.ENTER){

                try {
                    cs.sendToServer(textField_OutputText.getText());
                    if (!textField_OutputText.getText().isEmpty()) {

                    }
                        chat_window.appendText("Me: " + textField_OutputText.getText() + "\n");
                    textField_OutputText.clear();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    public void sendMessageButton(ActionEvent actionEvent) throws IOException {

        //cs.sendToServer(textField_OutputText.getText());
        Platform.runLater(() -> {
			try {
				cs.sendToServer(textField_OutputText.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        if (!textField_OutputText.getText().isEmpty()){
            chat_window.appendText("Me: " + textField_OutputText.getText() + "\n");
            textField_OutputText.clear();
        }

    }
}