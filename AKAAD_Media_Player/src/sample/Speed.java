package sample;

import javafx.scene.media.MediaPlayer;

public class Speed {
    public MediaPlayer mediaPlayer;
    Speed(MediaPlayer mp){
        this.mediaPlayer = mp;
    }
    public void slow(){
        double rate=mediaPlayer.getRate();
        if(rate>0.1){
            mediaPlayer.setRate(rate-0.1);
        }
    }
    public void fast(){
        double rate=mediaPlayer.getRate();
        if(rate<3){
            mediaPlayer.setRate(rate+0.1);
        }
    }
}
