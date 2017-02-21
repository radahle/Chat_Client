package Core;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class ClientService extends Service {

	@FXML ListView client_List;
	@FXML TextArea chatWindow;
	@FXML TextField textField_OutputText;
	@FXML Button sendTxt_button;
	@FXML TextArea chat_window;

	private String hostName;
	private int portNumber;
	private String serverFeedBack;
	private PrintWriter printWriter;
	private BufferedReader bufferedReader;
	Socket clientSocket;

	public ClientService(Socket s, String host, int port, TextField textField_OutPutText,
						 TextArea textArea_ChatWindow, Button sendTxt_button) {
		this.clientSocket = s;
		this.portNumber = port;
		this.hostName = host;
		this.textField_OutputText = textField_OutPutText;
		this.chatWindow = textArea_ChatWindow;
		this.sendTxt_button = sendTxt_button;
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


					textField_OutputText.setOnKeyPressed(event ->{
						if (event.getCode() == KeyCode.ENTER){
							try {

								sendToServer(textField_OutputText.getText());
								chatWindow.appendText("Me: " + textField_OutputText.getText() + "\n");
								textField_OutputText.clear();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});

					while(true) {
						receivedText = bufferedReader.readLine();

						//regex som finner clientList kode
						if (true /*regex er true*/) {
							//legg inn i listview clientList

						} else {
						chatWindow.appendText(receivedText + "\n");
						}

						System.out.println("Mottar fra server: " + receivedText);

					}
					//if (chatWindow.getText().equals("close")) clientSocket.close();

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

	public void sendToServer(String message) throws IOException{
		if (!message.isEmpty()){
			printWriter.println(clientSocket.getLocalPort() + ":" + message /*
        	+ "@" + "1234" <- mottakerport*/);
			printWriter.flush();
		}
	}

	public String readMessage() throws IOException {

		String text = bufferedReader.readLine();
		if (text.isEmpty()) text = "empty";

		return text;
	}




}


