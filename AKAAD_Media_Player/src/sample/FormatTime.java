package sample;

import javafx.util.Duration;

import static java.lang.Math.floor;
import static java.lang.String.format;

public class FormatTime {
    public static String formatTime(Duration elapsed, Duration duration) {
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
                return format("%d:%02d:%02d/%d:%02d:%02d",elapsedHours, elapsedMinutes, elapsedSeconds,durationHours, durationMinutes, durationSeconds);
            }
            else {
                return format("%02d:%02d/%02d:%02d",elapsedMinutes, elapsedSeconds, durationMinutes,durationSeconds);
            }
        }
        else {
            if (elapsedHours > 0) {
                return format("%d:%02d:%02d", elapsedHours,elapsedMinutes, elapsedSeconds);
            } else {
                return format("%02d:%02d", elapsedMinutes,elapsedSeconds);
            }
        }
    }
}