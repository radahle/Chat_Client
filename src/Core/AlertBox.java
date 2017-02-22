package Core;

import javafx.scene.control.Alert;

/**
 * Created by shohaib on 10/02/2017.
 */
public class AlertBox {

    public static void badCredentials(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bad credentials");
        alert.setContentText("Wrong Credentials, please try again or create an Account");
        alert.showAndWait();
    }

    public static void differentPasswords(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bad Credentials");
        alert.setContentText("Passwords are different, please try again");
        alert.showAndWait();
    }

    public static void badHost(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Host unreachable");
        alert.setContentText("Host cannot be reached, shutting down, please try again later");
        alert.showAndWait();
    }

    public static void hostUnreachable(String hostname, int portnumber){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Host unreachable");
        alert.setContentText("Cant find host: " + "[" + hostname + ":" + portnumber + "] > shutting down");
        alert.showAndWait();
    }

    public static void noSuchElement(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Such Element");
        alert.setContentText("You cant click on an empty Cell");
        alert.showAndWait();
    }
}
