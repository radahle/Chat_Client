package Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shohaib on 13/02/2017.
 */
public class HostCredentials {

    private String hostName;
    private int portNumber;

    public HostCredentials(){

    }

    public String getHostName(){

        return hostName;
    }

    public int getPortNumber(){

        return portNumber;
    }

    public void setHostName(String hostName){

        this.hostName = hostName;
    }

    public void setPortNumber(int portNumber){

        this.portNumber = portNumber;
    }

    public void getServerCredentials(){

        File file = new File("HostCredentials.txt");
        try {
            Scanner scanner = new Scanner(file);
            String line = "";

            String hostName;
            int portNumber;

            while (scanner.hasNext(Pattern.compile("#[HP].*"))) {

                line = scanner.nextLine();
                Pattern p = Pattern.compile("#[HP].*");
                Matcher m = p.matcher(line);

                while (m.find()) {

                    if (m.group().matches("#H.*")){

                        hostName = m.group().substring(3);
                        setHostName(hostName);
                    }

                    if (m.group().matches("#P.*")){

                        portNumber = Integer.parseInt(m.group().substring(3));
                        setPortNumber(portNumber);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }

    }
}
