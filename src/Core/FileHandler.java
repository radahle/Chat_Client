package Core;

import java.io.*;

/**
 * Class FileHandler
 * provides function of storing client-information.
 * @author Shohaib Muhammad <S300363>
 * @author Kittimasak Bunrat <s300342>
 * @author Pontus Sköld <s300377>
 * @author Rudi André Dahle <s300373>
 */
public class FileHandler {

    private String hostName;
    private int portNumber;

    public FileHandler(){

    }

    /**
     * This method saves the hostname and portnumber in a txt.file.
     * @param hostname is hostname given by user.
     * @param portNumber is portnumber given by user.
     */
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


    /**
     * This method saves username and password in a txt.file.
     * @param username
     * @param password
     */
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

    /**
     * This method reads information form Credentials.txt.
     * @param username is the usernamen given by user.
     * @param password is the password given by user.
     * @return
     */
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
