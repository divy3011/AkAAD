package sample;

import com.jfoenix.controls.JFXSlider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button play;
    @FXML
    private Button replay;
    @FXML
    private Button stop;
    @FXML
    private Button slow;
    @FXML
    private Button fast;
    @FXML
    private Button exit;
    @FXML
    private Button info;
    @FXML
    private Button vol;
    @FXML
    private JFXSlider volumeSlider;
    @FXML
    private JFXSlider timeSlider;
    @FXML
    private Label timeComplete;
    @FXML
    private Label timeTotal;
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private String filepath;
    File file;
    Media m;
    PlayPause PP;
    Stop St;
    Info In;
    Replay Rp;
    Speed Spd;
    Volume Vl;
    Exit Ex;
    @FXML
    public void handleButtonAction(ActionEvent event){
        FileOpener fo=new FileOpener(PP,Spd,mediaView,mediaPlayer,file,m,play,replay,stop,slow,fast,exit,info,vol,volumeSlider,timeSlider,timeComplete,timeTotal);
        fo.handleButtonAction(event);
        PP= fo.PP;
        Spd= fo.Spd;
        mediaView= fo.mediaView;
        mediaPlayer= fo.mediaPlayer;
        m= fo.m;
        file= fo.file;
    }
    @FXML
    public void info(ActionEvent event) {
        In = new Info(file, m);
        In.info();
    }
    @FXML
    public void playpause(ActionEvent event){
        MediaPlayer.Status status = mediaPlayer.getStatus();
        if(status==MediaPlayer.Status.PAUSED||status==MediaPlayer.Status.HALTED||status==MediaPlayer.Status.STOPPED){
            PP.play();
        }
        else{
            PP.pause();
        }
    }
    @FXML
    public void stop(ActionEvent event){
        St = new Stop(stop,play,mediaPlayer,timeComplete);
        St.stop();
    }
    @FXML
    public void fast(ActionEvent event){
        Spd.fast();
    }
    @FXML
    public void slow(ActionEvent event){
        Spd.slow();
    }
    @FXML
    public void exit(ActionEvent event){
        Ex = new Exit();
        Ex.exit();
    }
    @FXML
    public void vol(ActionEvent event){
        Vl = new Volume(mediaPlayer,volumeSlider);
        Vl.vol();
    }
    @FXML
    public void replay(ActionEvent event){
        Rp = new Replay(timeSlider,play,mediaPlayer,PP,timeComplete);
        Rp.replay();
    }
    @FXML
    public void show(ActionEvent event){
        About about = new About();
        about.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            play.setGraphic(new ImageView(new Image(new FileInputStream("src/images/play.png"))));
            replay.setGraphic(new ImageView(new Image(new FileInputStream("src/images/replay.png"))));
            stop.setGraphic(new ImageView(new Image(new FileInputStream("src/images/stop.png"))));
            info.setGraphic(new ImageView(new Image(new FileInputStream("src/images/info.png"))));
            fast.setGraphic(new ImageView(new Image(new FileInputStream("src/images/fast.png"))));
            slow.setGraphic(new ImageView(new Image(new FileInputStream("src/images/slow.png"))));
            exit.setGraphic(new ImageView(new Image(new FileInputStream("src/images/exit.png"))));
            vol.setGraphic(new ImageView(new Image(new FileInputStream("src/images/mute.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}