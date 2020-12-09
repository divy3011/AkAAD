package sample;

import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Info {
    File file;
    Media m;
    String information;
    Info(File f,Media m){
        this.file=f;
        this.m=m;
    }
    public void info(){
        ObservableMap<String,Object> objectObservableMap=m.getMetadata();
        String hight = Integer.toString(m.getHeight());
        String width = Integer.toString(m.getWidth());
        String absolute_path=file.getAbsolutePath();
        Duration duration = m.getDuration();
        String or= FormatTime.formatTime(duration,duration);
        int index=or.indexOf('/');
        String dura=or.substring(0,index);
        Object obj=(objectObservableMap.get("composer")==null)?"NA":objectObservableMap.get("composer");
        String composer= obj.toString();
        Object obj1=(objectObservableMap.get("year")==null)?"NA":objectObservableMap.get("year");
        String year= obj1.toString();
        Object obj2=(objectObservableMap.get("album")==null)?"NA":objectObservableMap.get("album");
        String album= obj2.toString();
        Object obj3=(objectObservableMap.get("genre")==null)?"NA":objectObservableMap.get("genre");
        String genre= obj3.toString();
        Object obj4=(objectObservableMap.get("artist")==null)?"NA":objectObservableMap.get("artist");
        String artist= obj4.toString();
        Object obj5=(objectObservableMap.get("title")==null)?"NA":objectObservableMap.get("title");
        String title= obj5.toString();
        if(!title.equals("NA")){
            information="Title:\t\t\t"+title+"\n";
        }
        else{
            int flag1=0,flag2=0;
            for(int i=0;i<absolute_path.length();i++) {
                if (absolute_path.charAt(i) == 92) flag1 = i+1;
                if (absolute_path.charAt(i) == 46) flag2 = i;
            }
            information="Title:\t\t\t"+absolute_path.substring(flag1,flag2)+"\n";
        }
        information+="Absolute Path:\t"+absolute_path+"\n";
        information+="Duration:\t\t"+dura+"\n";
        if(m.getHeight()!=0){
            information+="Hight:\t\t"+hight+"\n";
        }
        if(m.getWidth()!=0){
            information+="Width:\t\t"+width+"\n";
        }
        if(!composer.equals("NA")){
            information+="Composer:\t"+composer+"\n";
        }
        if(!year.equals("NA")){
            information+="Year:\t\t\t"+year+"\n";
        }
        if(!album.equals("NA")){
            information+="Album:\t\t"+album+"\n";
        }
        if(!genre.equals("NA")){
            information+="Genre:\t\t"+genre+"\n";
        }
        if(!artist.equals("NA")){
            information+="Artist:\t\t"+artist+"\n";
        }
        Label label = new Label();
        label.setText("Media Information");
        Text textArea=new Text();
        textArea.setText(information);
        BorderPane pane=new BorderPane();
        BorderPane pane1=new BorderPane();
        pane1.setCenter(label);
        pane.setTop(pane1);
        textArea.setFont(new Font("Sans-Serif",10));
        pane.setCenter(textArea);
        Stage stage = new Stage();
        Scene scene = new Scene(pane,650,250);
        stage.setTitle("Media Information");
        stage.setScene(scene);
        stage.show();
    }
}