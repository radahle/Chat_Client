package Core;

import javafx.scene.media.AudioClip;
import java.io.File;

/**
 * @author Shohaib Muhammad <S300363>
 * @author Kittimasak Bunrat <s300342>
 * @author Pontus Sköld <s300377>
 * @author Rudi André Dahle <s300373>
 */
public class MediaPlayer {

    private AudioClip audioClip;


    public MediaPlayer(String url){

        playSound(url);

    }

    public void playSound(String url){

        audioClip = new AudioClip(new File(url).toURI().toString());
    }

    public void playSound(){

        audioClip.play();
    }
}
