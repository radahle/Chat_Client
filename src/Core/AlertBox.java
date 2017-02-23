package Core;

import javafx.scene.control.Alert;



/**
 * Class AlertBox
 * provides warning messages for the Client in different scenarios.
 *
 * @author Shohaib Muhammad <S300363>
 * @author Kittimasak Bunrat <s300342>
 * @author Pontus Sköld <s300377>
 * @author Rudi André Dahle <s300373>
 */
public class AlertBox {

    /**
     * This method shows a warning message
     * if username is not registered or written wrongly.
     */
    public static void badCredentials(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bad credentials");
        alert.setContentText("Wrong Credentials, please try again or create an Account, please try again");
        alert.showAndWait();
    }

    /**
     * This method shows a warning message
     * if the password and repeted password is not written identically.
     */
    public static void differentPasswords(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bad Credentials");
        alert.setContentText("Passwords are different, please try again");
        alert.showAndWait();
    }

    /**
     * This method shows a warning message
     * if the host is not reached.
     */
    public static void badHost(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Host unreachable");
        alert.setContentText("Host cannot be reached, shutting down, please try again later");
        alert.showAndWait();
    }

    /**
     * This method shows a warning message
     * if specified host along with portnumber is not reached.
     * @param hostname is the hostname specified by user.
     * @param portnumber is the portnumber specified by user.
     */
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
