package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayPause {
    @FXML
    public Button play;
    public MediaPlayer mediaPlayer;
    PlayPause (Button p,MediaPlayer mp){
        this.play=p;
        this.mediaPlayer=mp;
    }
    @FXML
    public void play() {
        mediaPlayer.play();
        try {
            play.setGraphic(new ImageView(new Image(new FileInputStream("src/images/pause.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void pause()  {
        mediaPlayer.pause();
        try {
            play.setGraphic(new ImageView(new Image(new FileInputStream("src/images/play.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
