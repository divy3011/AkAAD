package sample;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class About {
    void show(){
        String information;
        information="Developed By:\n\tDivy Agrawal\n\tAmbika Kaushik\n\tAditya Aggarwal\n";
        information+="\u00A9 Akaad Media Project\n";
        Text textArea=new Text();
        textArea.setText(information);
        BorderPane pane=new BorderPane();
        textArea.setFont(new Font("Sans-Serif",20));
        pane.setCenter(textArea);
        Stage stage = new Stage();
        Scene scene = new Scene(pane,650,250);
        stage.setTitle("About");
        stage.setScene(scene);
        stage.show();
    }
}
