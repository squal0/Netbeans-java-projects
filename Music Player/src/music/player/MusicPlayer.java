/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package music.player;

import java.io.File;
import java.util.Random;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.media.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.stage.Stage;

/**
 *
 * @author Oracion Seis
 */
public class MusicPlayer extends Application {

    /**
     * @param args the command line arguments
     * the following program creates an mp3 player using javaFX
     */
    private MediaPlayer mp;
    private Point2D anchor;
    private Point2D previousLocation;
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start( final Stage primaryStage) {
        primaryStage.setTitle("Squalo Audio Player");
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Group root  = new Group();
        Scene scene = new Scene (root , 600, 300, Color.rgb(0, 0, 0, 0));
        
        // application area
        Rectangle appArea = RectangleBuilder.create().arcWidth(25).arcHeight(25).fill(Color.rgb(0, 0, 0, .80)).x(0).y(0).strokeWidth(2).stroke(Color.rgb(225, 225, 225, .70)).build();
        root.getChildren().add(appArea);
        appArea.widthProperty().bind(scene.widthProperty());
        appArea.heightProperty().bind(scene.heightProperty());
        final Group phaseNodes = new Group();
        root.getChildren().add(phaseNodes);
        
        // starting at initial anchor point
        scene.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle (MouseEvent evt){
                if (anchor != null && previousLocation != null){
                    primaryStage.setX(previousLocation.getX() + evt.getScreenX() - anchor.getX() );
                    primaryStage.setY(previousLocation.getY() + evt.getScreenY()- anchor.getY());
                }
            }
        });
        // dragging the entire stage
        scene.setOnMouseDragged(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent evt){
              if (anchor != null && previousLocation != null){
                    primaryStage.setX(previousLocation.getX() + evt.getScreenX() - anchor.getX() );
                    primaryStage.setY(previousLocation.getY() + evt.getScreenY()- anchor.getY());
                }  
            }
        });
        // set the current location
        scene.setOnMouseReleased(new EventHandler <MouseEvent>(){
            @Override
            public void handle (MouseEvent evt){
             previousLocation = new Point2D(primaryStage.getX(),primaryStage.getY());
            }
        });
        // Dragging over surface
scene.setOnDragOver(new EventHandler<DragEvent>() {
@Override
public void handle(DragEvent event) {
Dragboard db = event.getDragboard();
if (db.hasFiles()) {
event.acceptTransferModes(TransferMode.COPY);
} else {
event.consume();
}
}
});
        //Dropping over a surface
        scene.setOnDragDropped(new EventHandler <DragEvent>(){
            @Override
            public void handle(DragEvent evt){
                Dragboard db = evt.getDragboard();
                boolean success = false;
                if(db.hasFiles()){
                    
                    success = true;
                    String fp = null;
                    for(File file:db.getFiles()){
                        fp =file.getAbsolutePath();
                        System.out.println(fp);
                    }
                    // play the file
                    Media media = new Media (new File(fp).toURI().toString());
                    if (mp != null){
                        mp.stop();
                    }
                    mp = MediaPlayerBuilder.create().media(media).audioSpectrumListener(new AudioSpectrumListener(){
                        @Override
                        public void spectrumDataUpdate (double timeStamp, double duration, float [] magnitudes, float [] phases){
                         phaseNodes.getChildren().clear();
                         int i = 0;
                         int x = 10;
                         int y = 150;
                         final Random rand = new Random (System.currentTimeMillis());
                         for (float phase : phases){
                             int red = rand.nextInt(255);
                             int green = rand.nextInt(255);
                             int blue = rand.nextInt(255);
                             Circle circle = new Circle (20);
                             circle.setCenterX(x + i);
                             circle.setCenterY(y + (phase * 100));
                             circle.setFill(Color.rgb(red, green, blue, .70));
                             phaseNodes.getChildren().add(circle);
                             i+=5;
                         }
                        }
                    }).build();
                    mp.setOnReady(new Runnable(){
                        @Override
                        public void run(){
                           mp.play(); 
                        }
                    });
                }
                evt.setDropCompleted(success);
                evt.consume();
            }
        }); // end of ot setonDragDropped
        
        // creating the slide controls
        final Group bgroup = new Group();
        // rounded rectangle
        Rectangle bArea = RectangleBuilder.create().arcWidth(15).arcHeight(20).fill(new Color(0,0,0, .55)).x(0).y(0).width(60).height(30).stroke(Color.rgb(255, 255, 255, .70)).build();
        bgroup.getChildren().add(bArea);
        
        // stop audio control button
        Node stopButton = RectangleBuilder.create().arcWidth(5).arcHeight(5).fill(Color.rgb(255, 255, 255, .80)).x(0).y(0).width(10).height(10).translateX(15).translateY(10).stroke(Color.rgb(255, 255, 255, .70)).build();
        stopButton.setOnMousePressed(new EventHandler <MouseEvent>(){
            @Override
            public void handle (MouseEvent evt){
                if(mp != null){
                  mp.stop();  
                }
            }
        });
        bgroup.getChildren().add(stopButton);
        
        // play button
        final Node play = ArcBuilder.create().type(ArcType.ROUND).centerX(12).centerY(16).radiusX(15).radiusY(15).startAngle(180-30).length(60).fill(new Color(1,1,1, .90)).translateX(40).build();
        play.setOnMousePressed(new EventHandler <MouseEvent>(){
            @Override
            public void handle (MouseEvent evt){
               mp.play(); 
            }
        });
        // pause control
final Group pause = new Group();
final Node pauseButton = CircleBuilder.create().centerX(12).centerY(16).radius(10).stroke(new Color(1,1,1, .90)).translateX(30).build();
final Node firstLine = LineBuilder.create().startX(6).startY(16 - 10).endX(6).endY(16 - 2).strokeWidth(3).translateX(34).translateY(6).stroke(new Color(1,1,1, .90)).build();
final Node secondLine = LineBuilder.create().startX(6).startY(16 - 10).endX(6).endY(16 - 2).strokeWidth(3).translateX(38).translateY(6).stroke(new Color(1,1,1, .90)).build();
pause.getChildren().addAll(pauseButton, firstLine, secondLine);
pause.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
public void handle(MouseEvent evt) {
if (mp !=null) {
bgroup.getChildren().remove(pause);
bgroup.getChildren().add(play);
mp.pause();
}
}
});
play.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
public void handle(MouseEvent evt) {
if (mp != null) {
bgroup.getChildren().remove(play);
bgroup.getChildren().add(pause);
mp.play();
}
}
});
bgroup.getChildren().add(pause);
// move button group when scene is resized
bgroup.translateXProperty().bind(scene.widthProperty().subtract(bArea.getWidth() +6));
bgroup.translateYProperty().bind(scene.heightProperty().subtract(bArea.getHeight() +6));
root.getChildren().add(bgroup);
// close button
final Group closeApp = new Group();
Node closeButton = CircleBuilder.create().centerX(5).centerY(0).radius(7).fill(Color.rgb(255, 255, 255, .80)).build();
Node closeXmark = new Text(2, 4, "X");
closeApp.translateXProperty().bind(scene.widthProperty().subtract(15));
closeApp.setTranslateY(10);
closeApp.getChildren().addAll(closeButton, closeXmark);
closeApp.setOnMouseClicked(new EventHandler<MouseEvent>() {
@Override
public void handle(MouseEvent event) {
Platform.exit();
}
});
root.getChildren().add(closeApp);
        primaryStage.setScene(scene);
        primaryStage.show();
        previousLocation  = new Point2D(primaryStage.getX(),primaryStage.getY());
    }
}
