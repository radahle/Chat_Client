package Core;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class Client
 * provides the data for declaring a Client
 * and also starts a thread.
 *
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


    /**
     * Client constructor includes and sets the parameters listet below.
     * @param hostName is the ip-adress specified by user.
     * @param portNumber is the portnumber specified by user.
     * @param client_List is a list that shows active clients.
     * @param chatWindow is a textarea that shows messages.
     * @param textField_OutputText is the textarea that user writes messages.
     * @param sendTxt_button is the button used for sending messages
     */
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

    /**
     * Get-method for clientPort.
     * @return returns clientPort.
     */
    public int getClientPort(){

        return clientPort;
    }

    /**
     * Set-method for clientPort.
     * @param clientPort is the given clientPort set by user.
     */
    public void setClientPort(int clientPort){

        this.clientPort = clientPort;
    }

    /**
     * This method is part of task threading, it starts each thread.
     * @return
     * @throws Exception if thread error occur.
     */
    @Override
    protected Object call() throws Exception {
        while(true) {
            clientService.start();
        }
    }

    /**
     * This method will initialize client socket when its called. This method will also
     * start the thread.
     *
     * @throws UnknownHostException
     * @throws IOException
     */
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