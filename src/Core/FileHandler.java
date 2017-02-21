package Core;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shohaib on 10/02/2017.
 */
public class FileHandler {

    private String hostName;
    private int portNumber;

    public FileHandler(){

    }

    public void saveServerCredentials(String hostname, String portNumber){

        try {
            PrintWriter printWriter = new PrintWriter("HostCredentials.txt");

            if (hostname.isEmpty() && portNumber.isEmpty()){

                portNumber = String.valueOf(5555);
                hostname = "localhost";

                printWriter.println("#H " + hostname);
                printWriter.println("#P " + portNumber);

            }else {

                printWriter.println("#H "+ hostname);
                printWriter.println("#P " + portNumber);
            }

            printWriter.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void saveCredentials(String username, String password){

        try {
            PrintWriter printWriter = new PrintWriter("Credentials.txt");
            printWriter.write(username + ' ' + password);
            printWriter.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error while writing or reading file: Credentials.txt");
        }
    }


    public boolean readCredentials(String username, String password){

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Credentials.txt"));
            String content = bufferedReader.readLine();

            while (content != null){

                if (content.equals(username + ' ' + password)){
                    return true;
                }else {
                    return false;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
