package Core;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientService extends Service {

    @FXML
    ListView client_List;
    @FXML
    TextArea chatWindow;
    @FXML
    TextField textField_OutputText;
    @FXML
    Button sendTxt_button;

    Socket clientSocket;
    private String hostName;
    private int portNumber;
    private String serverFeedBack;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private String contactPort;
    MediaPlayer mediaPlayer;

    public ClientService(Socket s, String host, int port, TextField textField_OutPutText,
                         TextArea textArea_ChatWindow, Button sendTxt_button, ListView client_List) {
        this.clientSocket = s;
        this.portNumber = port;
        this.hostName = host;
        this.textField_OutputText = textField_OutPutText;
        this.chatWindow = textArea_ChatWindow;
        this.sendTxt_button = sendTxt_button;
        this.client_List = client_List;
        mediaPlayer = new MediaPlayer("receivedMessage.wav");

        Platform.runLater(() -> {

            client_List.setOnMouseClicked(event -> {



                try {


                String selectedClient = (String) client_List.getSelectionModel().getSelectedItem();

                Pattern clientPortPattern = Pattern.compile("(?<=\\:)(.*)(?=\\])");
                Matcher clientPortMatcher = clientPortPattern.matcher(selectedClient);
                if (clientPortMatcher.find()) {
                    String selectedClientPort = clientPortMatcher.group(1);

                    System.out.println("Selectedclientport från mouseclick " +selectedClientPort);

                    contactPort = "@" + selectedClientPort;


                }

                }catch (Exception e){

                }

            });

        });

    }

    @Override
    protected Task createTask() {
        Task task = new Task() {

            @Override
            protected Object call() throws Exception {
                try {
                    System.out.println("ClientService call");
                    printWriter = new PrintWriter((clientSocket.getOutputStream()));
                    bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String receivedText;
                    String recv;

                    sendTxt_button.setOnAction(event -> {

                        try {

                            sendToServer(textField_OutputText.getText());
                            chatWindow.appendText("Me: " + textField_OutputText.getText() + "\n");
                            textField_OutputText.clear();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });

                    textField_OutputText.setOnKeyPressed(event -> {
                        if (event.getCode() == KeyCode.ENTER) {
                            try {

                                sendToServer(textField_OutputText.getText());
                                chatWindow.appendText("Me: " + textField_OutputText.getText() + "\n");
                                textField_OutputText.clear();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    while (true) {
                        receivedText = bufferedReader.readLine();

                        mediaPlayer.playSound();

                        Pattern clientPattern = Pattern.compile("(?<=\\#\\@\\$)(.*)(?=\\$\\@\\#)");
                        Matcher clientMatcher = clientPattern.matcher(receivedText);
                        if (clientMatcher.find()) {
                            String finalClientInfo = clientMatcher.group(1);

                            Platform.runLater(() -> {
								client_List.getItems().removeAll(finalClientInfo);
								client_List.getItems().add(finalClientInfo);
									});
                        } else {
                            chatWindow.appendText(receivedText + "\n");
                            System.out.println("Mottar fra server: " + receivedText);
                        }
                    }

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    AlertBox.hostUnreachable(hostName, portNumber);
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                    AlertBox.badHost();
                    System.exit(0);
                }
                return null;
            }

        };
        return task;
    }


    public void sendToServer(String message) throws IOException {
        if (!message.isEmpty()) {

            printWriter.println(clientSocket.getLocalPort() + ":" + message + contactPort);
            printWriter.flush();

        }
    }

    ////// /+ "@" + "1234" <- mottakerport*/

    public String readMessage() throws IOException {

        String text = bufferedReader.readLine();
        if (text.isEmpty()) text = "empty";

        return text;
    }



}


