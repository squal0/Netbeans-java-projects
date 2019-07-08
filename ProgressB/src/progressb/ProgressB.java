/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progressb;

import java.util.Random;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Squalo
 */
public class ProgressB extends Application {
static Task fileCopier;
final int numOfFiles = 100;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Oracion Copier");
        Group root  = new Group();
        Scene scene  = new Scene (root , 400 , 200, Color.LIGHTSLATEGRAY);
        BorderPane mp = new BorderPane();
        mp.layoutXProperty().bind(scene.widthProperty().subtract(mp.widthProperty()).divide(2));
       root.getChildren().add(mp);
       final Label label = new Label("Files Transfer");
       final ProgressBar pb  = new ProgressBar(0);
       final ProgressIndicator pgi = new ProgressIndicator(0);
       final HBox hb = new HBox();
       hb.setSpacing(5);
       hb.setAlignment(Pos.CENTER);
       hb.getChildren().addAll(label ,pb , pgi);
       mp.setTop(hb);
       final Button start  = new Button ("Start");
       Reflection r = new Reflection();
      r.setFraction(0.8f);
      start.setEffect(r);
       final Button cancel = new Button ("Cancel");
       Reflection r1 = new Reflection();
      r.setFraction(0.8f);
      cancel.setEffect(r1);
       final TextArea txtArea  = new TextArea();
       DropShadow dropS = new DropShadow();
         dropS.setOffsetX(4.0f);
     dropS.setOffsetY(4.0f);
     dropS.setColor(Color.rgb(50, 50, 50 ,.588));
     txtArea.setEffect(dropS);
       txtArea.setEditable(false);
       txtArea.setPrefSize(230, 100);
       final HBox hb1  = new HBox();
       hb1.setSpacing(5);
       hb.setAlignment(Pos.CENTER);
       hb1.getChildren().addAll(start , cancel , txtArea);
       mp.setBottom(hb1);
       
       // code the start button
       start.setOnAction(new EventHandler <ActionEvent>(){
           public void handle (ActionEvent evt){
               start.setDisable(true);
               pb.setProgress(0);
               pgi.setProgress(0);
               txtArea.setText("");
               cancel.setDisable(false);
               fileCopier = createWorker(numOfFiles);
               
              // code progress bar
               pb.progressProperty().unbind();
               pb.progressProperty().bind(fileCopier.progressProperty());
               pgi.progressProperty().unbind();
pgi.progressProperty().bind(fileCopier.progressProperty());
         // append to text area box
fileCopier.messageProperty().addListener(new ChangeListener<String>() {
public void changed(ObservableValue<? extends String> observable, String
oldValue, String newValue) {
txtArea.appendText(newValue + "\n");
}
});
new Thread(fileCopier).start();
}
});
       // cancel button will kill worker and reset.
cancel.setOnAction(new EventHandler<ActionEvent>() {
public void handle(ActionEvent event) {
start.setDisable(false);
cancel.setDisable(true);
fileCopier.cancel(true);
// reset
pb.progressProperty().unbind();
pb.setProgress(0);
pgi.progressProperty().unbind();
pgi.setProgress(0);
txtArea.appendText("File transfer was cancelled.");
}
});
primaryStage.setScene(scene);
primaryStage.show();
        }
   public Task createWorker(final int numOfFiles) {
return new Task() {
@Override
protected Object call() throws Exception {
for (int i = 0; i < numOfFiles; i++) {
long elapsedTime = System.currentTimeMillis();
copyFile("some file", "some dest file");
elapsedTime = System.currentTimeMillis() - elapsedTime;
String status = elapsedTime + " milliseconds";
// queue up status
updateMessage(status);
updateProgress(i + 1, numOfFiles);
}
return true;
}
};
}
public void copyFile(String src, String dest) throws InterruptedException {
// simulate a long time
Random rnd = new Random(System.currentTimeMillis());
long millis = rnd.nextInt(1000);
Thread.sleep(millis);
}
}