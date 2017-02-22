package Controllers;

import Core.Client;
import Core.ClientService;
import Core.HostCredentials;
import Core.MediaPlayer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private String selectedClientPort;


    // Class related objects
    ClientService cs;
    Client client;
    HostCredentials hostCredentials;
    MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hostCredentials = new HostCredentials();
        hostCredentials.getServerCredentials();
        hostName = hostCredentials.getHostName();
        portNumber = hostCredentials.getPortNumber();
        mediaPlayer = new MediaPlayer("login_Sound.wav");

        //mediaPlayer.playSound();

        client = new Client(hostName, portNumber, clients_list, chat_window, textField_OutputText, sendTxt_button,
                             selectedClientPort);
        try {
            client.start();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }



    public void handle(MouseEvent event) {
        System.out.println("clicked on " + clients_list.getSelectionModel().getSelectedItem());
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


