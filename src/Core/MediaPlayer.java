package Core;

import javafx.scene.media.AudioClip;
import java.io.File;

/**
 * Created by pontusskold on 22.02.2017.
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
