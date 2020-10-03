package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.naming.Binding;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.floor;
import static java.lang.String.format;

public class Controller implements Initializable {
    @FXML
    private Slider volumeSlider;
    @FXML
    private Slider timeSlider;
    @FXML
    private Label timeLabel;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private String filepath;
    boolean repeat=false;
    @FXML
    public void handleButtonAction(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("open a new file","*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filepath = file.toURI().toString();
        if(filepath!=null){
            Media media = new Media(filepath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));
            mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    timeSlider.setValue( (mediaPlayer.getCurrentTime().toMillis() / mediaPlayer.getTotalDuration().toMillis()));
                    timeLabel.setText(formatTime(mediaPlayer.getCurrentTime() ,  mediaPlayer.getTotalDuration()));
                }
            });
            volumeSlider.setValue(mediaPlayer.getVolume()*100);
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumeSlider.getValue()/100);
                }
            });
            timeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
                }
            });
            mediaPlayer.play();
        }
    }
    private void play(){
        mediaPlayer.play();
    }
    private void pause(){
        mediaPlayer.pause();
    }
    @FXML
    public void playpause(ActionEvent event){
        MediaPlayer.Status status = mediaPlayer.getStatus();
        if(status==MediaPlayer.Status.PAUSED ||
                status==MediaPlayer.Status.HALTED ||
                status==MediaPlayer.Status.STOPPED){
            play();
        }
        else{
            pause();
        }
    }
    @FXML
    public void stop(ActionEvent event){
        mediaPlayer.stop();
    }
    @FXML
    public void fast(ActionEvent event){
        double rate=mediaPlayer.getRate();
        if(rate<3){
            mediaPlayer.setRate(rate+0.1);
        }
    }
    @FXML
    public void faster(ActionEvent event){
        double rate=mediaPlayer.getRate();
        if(rate<3){
            mediaPlayer.setRate(rate+0.3);
        }
    }
    @FXML
    public void slow(ActionEvent event){
        double rate=mediaPlayer.getRate();
        if(rate>0.1){
            mediaPlayer.setRate(rate-0.1);
        }
    }
    @FXML
    public void slower(ActionEvent event){
        double rate=mediaPlayer.getRate();
        if(rate>0.1){
            mediaPlayer.setRate(rate-0.3);
        }
    }
    @FXML
    public void exit(ActionEvent event){
        System.exit(0);
    }
    @FXML
    public void replay(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getStartTime());
        playpause(event);
        timeSlider.setValue(0);
    }
    private static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        if(elapsedMinutes>0){
            intElapsed-=elapsedMinutes*60;
        }
        int elapsedSeconds = intElapsed;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationMinutes * 60;
            if (durationHours > 0) {
                return format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            }
            else {
                return format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds, durationMinutes,
                        durationSeconds);
            }
        }
        else {
            if (elapsedHours > 0) {
                return format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return format("%02d:%02d", elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(filepath!=null) {
            timeSlider.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(timeSlider.getValue()));
                }
            });
            volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    mediaPlayer.setVolume(volumeSlider.getValue());
                }
            });
            // This method of updating time slider dynamically using listener terribly failed
            timeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    if (timeSlider.isValueChanging())
                        mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(timeSlider.getValue()));
                }
            });

            mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    timeSlider.setValue((mediaPlayer.getCurrentTime().toMillis() / mediaPlayer.getTotalDuration().toMillis()));
                    timeLabel.setText(formatTime(mediaPlayer.getCurrentTime(), mediaPlayer.getTotalDuration()));
                }
            });
        }
    }
}
