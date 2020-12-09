package sample;

import com.jfoenix.controls.JFXSlider;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Replay {
    public JumpTime timeSlider;
    public Button play;
    public MediaPlayer mediaPlayer;
    PlayPause PP;
    Label timestarted;
    Replay(JFXSlider ts, Button p, MediaPlayer mp, PlayPause pp,Label timestarted){
        timeSlider = new JumpTime(ts,mp,timestarted);
        this.play=p;
        this.PP=pp;
        this.mediaPlayer=mp;
        this.timeSlider=timeSlider;
    }
    public void replay(){
        mediaPlayer.seek(mediaPlayer.getStartTime());
        PP.play();
        timeSlider.setTimeSliderValue(0);
        mediaPlayer.play();
        try {
            play.setGraphic(new ImageView(new Image(new FileInputStream("src/images/pause.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
