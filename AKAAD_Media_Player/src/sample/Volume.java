package sample;

import com.jfoenix.controls.JFXSlider;
import javafx.scene.media.MediaPlayer;

public class Volume {
    public MediaPlayer mediaPlayer;
    public JFXSlider volumeSlider;
    Volume(MediaPlayer mp, JFXSlider vS){
        this.mediaPlayer = mp;
        this.volumeSlider = vS;
    }
    public void vol(){
        if(mediaPlayer.getVolume()==0){
            volumeSlider.setValue(100);
        }
        else {
            volumeSlider.setValue(mediaPlayer.getVolume()*0);
        }
    }
}
