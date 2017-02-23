package Core;

import Controllers.LoginController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import sun.rmi.runtime.Log;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javafx.application.Platform;

/**
 * @author Shohaib Muhammad <S300363>
 * @author Kittimasak Bunrat <s300342>
 * @author Pontus Sköld <s300377>
 * @author Rudi André Dahle <s300373>
 */
public class Client extends Task {

    @FXML ListView client_List;
    @FXML TextArea chatWindow;
    @FXML TextField textField_OutputText;
    @FXML Button sendTxt_button;
    @FXML ChoiceBox<String> choiceBox;

    private String hostName;
    private int portNumber;
    private ClientService clientService;
    private int clientPort;


    public Client(String hostName, int portNumber, ListView client_List, TextArea chatWindow,
                  TextField textField_OutputText, Button sendTxt_button, ChoiceBox<String> choiceBox) {

        this.chatWindow = chatWindow;
        this.client_List = client_List;
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.textField_OutputText = textField_OutputText;
        this.sendTxt_button = sendTxt_button;
        this.choiceBox = choiceBox;

    }

    public int getClientPort(){

        return clientPort;
    }


    public void setClientPort(int clientPort){

        this.clientPort = clientPort;
    }


    @Override
    protected Object call() throws Exception {
        while(true) {
            System.out.println("Client call");
            clientService.start();
        }
    }

    public void start() throws UnknownHostException, IOException{
        Socket clientSocket = new Socket(hostName, portNumber);
        clientService = new ClientService(clientSocket, hostName, portNumber, textField_OutputText, chatWindow,
                sendTxt_button, client_List, choiceBox);
        setClientPort(clientService.getClientPort());
        Thread thread = new Thread(this);
        thread.start();
        System.out.println("Start");
    }

}