/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animation.generate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.util.Duration;

/**
 *
 * @author Squalo
 */
public class AnimationGenerate extends Application {

    /**
     * @param args the command line arguments
     * photo viewer with controls that fade in and out
     */
    private List<String> imageFiles = new ArrayList<>();
    private int currentIndex = -1;
    public enum buttonMove {NEXT, PREV};
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Image Viewer");
        Group root = new Group();
        final Scene scene = new Scene (root , 550 , 450, Color.DARKSLATEGRAY);
         final ImageView  currentImageView = new ImageView();
         
          // maintain aspect ratio
        currentImageView.setPreserveRatio(true);
        // resize based on the scene
        currentImageView.fitWidthProperty().bind(scene.widthProperty());
        final HBox picRegion = new HBox();
        picRegion.getChildren().add(currentImageView);
        root.getChildren().add(picRegion);
        // Dragging over surface
        scene.setOnDragOver(new EventHandler <DragEvent>(){
         @Override
         public void handle (DragEvent event){
           Dragboard db = event.getDragboard();
           boolean success = false;
           if(db.hasFiles()){
               success = true;
               String fp = null;
               for(File file: db.getFiles()){
                   fp = file.getAbsolutePath();
                   currentIndex += 1;
                   imageFiles.add(currentIndex, fp);
                   // absolute file name
                   System.out.println("file :" + file);
                   // the index in the list of the file names
                   System.out.println("CurrentImageFileIndex =" + currentIndex);
               }
               // set new image as image to show
               Image image  = new Image(fp);
               currentImageView.setImage(image);
           }
           event.setDropCompleted(success);
           event.consume();
         }
        });
        // create slide control
        Group buttonG = new Group();
        // rounded rect
        Rectangle buttonRect = RectangleBuilder.create().arcWidth(15).arcHeight(20).fill(new Color(0,0,0,.55)).x(0).y(0).width(60).height(30).stroke(Color.rgb(255, 255, 255, .70)).build();
        buttonG.getChildren().add(buttonRect);
        
        // create the ticker area
        final Group tA = new Group();
        final Rectangle tR = RectangleBuilder.create().arcWidth(20).arcHeight(25).fill(new Color(0,0,0,.55)).x(0).y(0).width(scene.getWidth()- 6).height(35).stroke(Color.rgb(255, 255, 255, .70)).build();
        Rectangle clipReg = RectangleBuilder.create().arcWidth(20).arcHeight(25).x(0).y(0).width(scene.getWidth()- 6).height(35).stroke(Color.rgb(255, 255, 255, .70)).build();
        tA.setClip(clipReg);
        
        // resize the ticker area when the window is resized
        tA.setTranslateX(8);
        tA.translateYProperty().bind(scene.heightProperty().subtract(tR.getHeight()+ 6));
        tR.widthProperty().bind(scene.widthProperty().subtract(buttonRect.getWidth() + 16));
        clipReg.widthProperty().bind(scene.widthProperty().subtract(buttonRect.getWidth() + 16));
        
        tA.getChildren().add(tR);
        root.getChildren().add(tA);
        
        // add news text
        Text news = TextBuilder.create().text("Bleach News | chapter images | :").translateY(20).fill(Color.DARKOLIVEGREEN).build();
        tA.getChildren().add(news);
        final TranslateTransition ticker = TranslateTransitionBuilder.create().node(news).duration(Duration.millis((scene.getWidth()/ 300)* 15000)).fromX(scene.widthProperty().doubleValue()).toX(- scene.widthProperty().doubleValue()).fromY(19).interpolator(Interpolator.LINEAR).cycleCount(1).build();
        
        // when ticker has finished reset and replay ticker animation
        ticker.setOnFinished(new EventHandler <ActionEvent>(){ 
            public void handle (ActionEvent event){
                ticker.stop();
                ticker.setFromX(scene.getWidth());
                ticker.setDuration(new Duration ((scene.getWidth()/ 300) *15000));
                ticker.playFromStart();
            }
        });
        ticker.play();
        
        // the following code fades out the current picture and fades in the next picture
        // previous button 
        Arc prevButton =  ArcBuilder.create().type(ArcType.ROUND).centerX(12).centerY(16).radiusX(15).radiusY(15).startAngle(180-30).length(60).fill(new Color(1,1,1, .90)).translateX(40).build();
        prevButton.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler <MouseEvent>(){
        public void handle (MouseEvent mouse){
              int index = gotoImageIndex(buttonMove.PREV);
                if(index > -1){
                    String namePicture = imageFiles.get(index);
                    final Image nextImage = new Image (new File (namePicture).getAbsolutePath());
                    SequentialTransition sqt = transitionByFading(nextImage , currentImageView);
                    sqt.play();
        }    
        }
        });
        buttonG.getChildren().add(prevButton);
    // previous button 
        Arc nextButton =  ArcBuilder.create().type(ArcType.ROUND).centerX(12).centerY(16).radiusX(15).radiusY(15).startAngle(180-30).length(60).fill(new Color(1,1,1, .90)).translateX(40).build();
        nextButton.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler <MouseEvent>(){
        public void handle (MouseEvent mouse){
              int index = gotoImageIndex(buttonMove.NEXT);
                if(index > -1){
                    String namePicture = imageFiles.get(index);
                    final Image nextImage = new Image (new File (namePicture).getAbsolutePath());
                    SequentialTransition sqt = transitionByFading(nextImage , currentImageView);
                    sqt.play();
        }    
        }
        });
      buttonG.translateXProperty().bind(scene.widthProperty().subtract(buttonRect.getWidth() + 6));
         buttonG.translateYProperty().bind(scene.heightProperty().subtract(buttonRect.getHeight() + 6));
         root.getChildren().add(buttonG);
         primaryStage.setScene(scene);
         primaryStage.show();           
}

            public int gotoImageIndex(buttonMove direction) {
                int size = imageFiles.size();
                if(size == 0){
                    currentIndex = - 1;
                    }
                else if (direction == buttonMove.NEXT && size > 1 && currentIndex < size - 1){
                    currentIndex += 1;
                }
                     else if (direction == buttonMove.PREV && size > 1 && currentIndex < size - 1){
                    currentIndex -= 1;
                }
                return currentIndex;
            }


public SequentialTransition transitionByFading(final Image nextImage, final ImageView
imageView) {
FadeTransition fadeOut = new FadeTransition(Duration.millis(500), imageView);
fadeOut.setFromValue(1.0);
fadeOut.setToValue(0.0);
fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
public void handle(ActionEvent ae) {
imageView.setImage(nextImage);
}
});
FadeTransition fadeIn = new FadeTransition(Duration.millis(500), imageView);
fadeIn.setFromValue(0.0);
fadeIn.setToValue(1.0);
SequentialTransition seqTransition = SequentialTransitionBuilder.create()
.children(fadeOut, fadeIn)
.build();
return seqTransition;
}
}