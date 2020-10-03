package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {
    boolean getcount=false;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("AkAAD Media");
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(500);
        Scene scene = new Scene(root, 600, 400);
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent doubleclicked) {
                if(doubleclicked.getClickCount()==2 && getcount==false){
                    getcount=true;
                    primaryStage.setFullScreen(true);
                }
                else if(doubleclicked.getClickCount()==2 && getcount==true){
                    primaryStage.setFullScreen(false);
                    getcount=false;
                }
            }
        });
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
