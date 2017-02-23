package Core;

import javafx.scene.media.AudioClip;
import java.io.File;

/**
 * Class MediaPlayer
 * provides audio in the program.
 *
 * @author Shohaib Muhammad <S300363>
 * @author Kittimasak Bunrat <s300342>
 * @author Pontus Sköld <s300377>
 * @author Rudi André Dahle <s300373>
 */
public class MediaPlayer {

    private AudioClip audioClip;

    /**
     * Constructor initializing Mediaplayer Class and calls the playsound method
     * @param url This method takes url in parameter which should provide location of the media file.
     */
    public MediaPlayer(String url){

        playSound(url);

    }

    /**
     * This method will be called automatically inside the constructor when this class is instantiated.
     * This method will also initialize the audioclip and convert to the required format (URL).
     * @param url This method takes url in parameter which should provide location of the media file.
     */
    public void playSound(String url){

        audioClip = new AudioClip(new File(url).toURI().toString());
    }

    /**
     * This method will play the media file when its called.
     */
    public void playSound(){

        audioClip.play();
    }
}
