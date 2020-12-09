package sample;

import com.jfoenix.controls.JFXSlider;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileOpener {
    public Button play;
    public Button replay;
    public Button stop;
    public Button slow;
    public Button fast;
    public Button exit;
    public Button info;
    public Button vol;
    public JFXSlider volumeSlider;
    public JumpTime timeSlider;
    public Label timeComplete;
    public Label timeTotal;
    public MediaView mediaView;
    public MediaPlayer mediaPlayer;
    File file;
    Media m;
    PlayPause PP;
    Replay Rp;
    Speed Spd;
    FileOpener(PlayPause PP, Speed Spd, MediaView mediaView, MediaPlayer mediaPlayer, File file, Media m, Button play, Button replay, Button stop, Button slow, Button fast, Button exit, Button info, Button vol, JFXSlider volumeSlider, JFXSlider timeSlider, Label timeComplete, Label timeTotal){
        this.play=play;
        this.replay=replay;
        this.stop=stop;
        this.slow=slow;
        this.replay=replay;
        this.exit=exit;
        this.fast=fast;
        this.info=info;
        this.vol=vol;
        this.volumeSlider=volumeSlider;
        this.timeSlider = new JumpTime(timeSlider,mediaPlayer, timeComplete);
        this.timeComplete=timeComplete;
        this.timeTotal=timeTotal;
        this.m=m;
        this.mediaView=mediaView;
        this.mediaPlayer=mediaPlayer;
        this.file=file;
        this.PP=PP;
        this.Spd=Spd;
    }
    public void handleButtonAction(ActionEvent event){
        try {
            FileChooser chooser = new FileChooser();
            file = chooser.showOpenDialog(null);
            m = new Media(file.toURI().toURL().toString());
            if (mediaPlayer != null) {
                mediaPlayer.dispose();
            }
            mediaPlayer = new MediaPlayer(m);
            mediaView.setMediaPlayer(mediaPlayer);
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));
            PP = new PlayPause(play,mediaPlayer);
            Spd = new Speed(mediaPlayer);
            mediaPlayer.setOnReady(() -> {
                timeSlider.setMinValue(0);
                timeSlider.setMaxValue(mediaPlayer.getMedia().getDuration().toMinutes());
                timeSlider.setTimeSliderValue(0);
            });
            play.setGraphic(new ImageView(new Image(new FileInputStream("src/images/play.png"))));
            mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                Duration d = mediaPlayer.getCurrentTime();
                timeSlider.setTimeSliderValue(d.toMinutes());
            });
            timeSlider.setMediaPlayer(mediaPlayer);
            timeSlider.addListener();
            mediaPlayer= timeSlider.mediaPlayer;
            volumeSlider.setValue(mediaPlayer.getVolume()*100);
            volumeSlider.valueProperty().addListener(observable -> {
                mediaPlayer.setVolume(volumeSlider.getValue()/100);
                if(volumeSlider.getValue()==0){
                    try {
                        vol.setGraphic(new ImageView(new Image(new FileInputStream("src/images/mute.png"))));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        vol.setGraphic(new ImageView(new Image(new FileInputStream("src/images/volume.png"))));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            });
            mediaPlayer.currentTimeProperty().addListener(observable -> {
                Duration tC = mediaPlayer.getCurrentTime();
                Duration tT = mediaPlayer.getTotalDuration();
                String C= FormatTime.formatTime(tC,tC);
                int index_C=C.indexOf('/');
                String TC;
                if(index_C>=0) {
                    TC = C.substring(0, index_C);
                }
                else{
                     TC = "00:00:00";
                }
                String T= FormatTime.formatTime(tT,tT);
                int index_T=T.indexOf('/');
                String TT=T.substring(0,index_T);
                timeComplete.setText(TC);
                timeTotal.setText(TT);
            });

            mediaPlayer.play();
            try {
                play.setGraphic(new ImageView(new Image(new FileInputStream("src/images/pause.png"))));
                vol.setGraphic(new ImageView(new Image(new FileInputStream("src/images/volume.png"))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
