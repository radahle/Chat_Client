package Core;

import Controllers.LoginController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    @FXML
    Button sendTxt_button;

    private String hostName;
    private int portNumber;
    private String serverFeedBack;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private ClientService cs;


    public Client(String hostName, int portNumber, ListView client_List, TextArea chatWindow,
                  TextField textField_OutputText, Button sendTxt_button, String selectedClientPort) {

        this.chatWindow = chatWindow;
        this.client_List = client_List;
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.textField_OutputText = textField_OutputText;
        this.sendTxt_button = sendTxt_button;



    }

    public String getServerFeedBack(){

        return serverFeedBack;
    }

    public void setServerFeedBack(String serverFeedBack){

        this.serverFeedBack = serverFeedBack;

    }


    public void connectToServer() throws Exception{

    }


    public void sendToServer(String message) throws IOException{

        if (!message.isEmpty()){
            printWriter.println(message);
            printWriter.flush();
        }
    }

    public String readMessage() throws IOException {

        String text = bufferedReader.readLine();
        if (text.isEmpty()) text = "empty";

        return text;
    }



    @Override
    protected Object call() throws Exception {
        while(true) {
            System.out.println("Client call");
            cs.start();
        }
    }

    public void start() throws UnknownHostException, IOException{
        Socket clientSocket = new Socket(hostName, portNumber);
        cs = new ClientService(clientSocket, hostName, portNumber, textField_OutputText, chatWindow, sendTxt_button, client_List);
        //cs.setSelectedClient(selectedClient);
        //cs.setSelectedClientPort(selectedPortClient);
        Thread thread = new Thread(this);
        thread.start();
        System.out.println("Start");
    }

}