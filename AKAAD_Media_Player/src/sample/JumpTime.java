package sample;

import com.jfoenix.controls.JFXSlider;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class JumpTime {
    JFXSlider timeSlider;
    MediaPlayer mediaPlayer;
    Label timeComplete;
    JumpTime(JFXSlider timeSlider, MediaPlayer mediaPlayer, Label timeComplete){
        this.mediaPlayer=mediaPlayer;
        this.timeSlider=timeSlider;
        this.timeComplete=timeComplete;
    }
    public void setMinValue(double value){
        timeSlider.setMin(value);
    }
    public void setMaxValue(double value){
        timeSlider.setMax(value);
    }
    public void setTimeSliderValue(double value){
        timeSlider.setValue(value);
    }
    public void setMediaPlayer(MediaPlayer mediaPlayer){
        this.mediaPlayer=mediaPlayer;
    }
    public void addListener(){
        timeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (timeSlider.isPressed()) {
                double val = timeSlider.getValue();
                mediaPlayer.seek(new Duration(val * 60 * 1000));
            }
        });
        timeSlider.setValueFactory(arg0 -> Bindings.createStringBinding(() ->
                timeComplete.toString().substring(timeComplete.toString().indexOf('\'') + 1,
                        timeComplete.toString().length() - 1), timeSlider.valueProperty()));
    }
}
