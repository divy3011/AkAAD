package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Stop {
    public Button stop;
    public Button play;
    public MediaPlayer mediaPlayer;
    public Label timeComplete;
    Stop(Button s, Button p, MediaPlayer mp, Label timeComplete){
        this.stop=s;
        this.play=p;
        this.mediaPlayer=mp;
        this.timeComplete=timeComplete;
    }
    @FXML
    public void stop(){
        mediaPlayer.stop();
        timeComplete.setText("00:00:00");
        try {
            play.setGraphic(new ImageView(new Image(new FileInputStream("src/images/play.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
