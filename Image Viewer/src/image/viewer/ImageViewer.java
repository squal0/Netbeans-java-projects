/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.viewer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcBuilder;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;
/**
 *
 * @author Squalo
 * // creating an image viewer
 */
public class ImageViewer extends Application {

    /**
     * @param args the command line arguments
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
        Scene scene = new Scene (root , 550 , 450, Color.DARKSLATEGRAY);
        // image view
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
        Rectangle buttonArea = RectangleBuilder.create().arcWidth(15).arcHeight(20).fill(new Color(0,0,0,.55)).x(0).y(0).width(60).height(30).stroke(Color.rgb(255, 255, 255, .70)).build();
        buttonG.getChildren().add(buttonArea);
        // left control
        Arc leftButton = ArcBuilder.create().type(ArcType.ROUND).centerX(12).centerY(16).radiusX(15).radiusY(15).startAngle(-30).length(60).fill(new Color(1,1,1, .90)).build();
        leftButton.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler <MouseEvent>(){
            public void handle (MouseEvent mouse){
                int index = gotoImageIndex(buttonMove.PREV);
                if(index > -1){
                    String namePicture = imageFiles.get(index);
                    final Image image = new Image (new File (namePicture).getAbsolutePath());
                    currentImageView.setImage(image);
                }
            }
        });
        buttonG.getChildren().add(leftButton);
         Arc rightButton = ArcBuilder.create().type(ArcType.ROUND).centerX(12).centerY(16).radiusX(15).radiusY(15).startAngle(180-30).length(60).fill(new Color(1,1,1, .90)).translateX(40).build();
           buttonG.getChildren().add(rightButton);
         rightButton.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler <MouseEvent>(){
            public void handle (MouseEvent mouse){
                int index = gotoImageIndex(buttonMove.NEXT);
                if(index > -1){
                    String namePicture = imageFiles.get(index);
                    final Image image = new Image (new File (namePicture).getAbsolutePath());
                    currentImageView.setImage(image);
                }           
            }
        });
        // move button group when scene is resized
        buttonG.translateXProperty().bind(scene.widthProperty().subtract(buttonArea.getWidth() + 6));
         buttonG.translateYProperty().bind(scene.heightProperty().subtract(buttonArea.getHeight() + 6));
         root.getChildren().add(buttonG);
         primaryStage.setScene(scene);
         primaryStage.show();
    }
    /**
     * returns the index in the list of files to go to next
     */
    public int gotoImageIndex(buttonMove direction){
        int size = imageFiles.size();
        if(size == 0){
            currentIndex = -1;
        }
        else if(direction == buttonMove.NEXT && size > 1 && currentIndex < size - 1){
            currentIndex += 1;
        }
        else if(direction == buttonMove.PREV && size > 1 && currentIndex < size - 1){
            currentIndex -= 1;
        }
            
            return currentIndex;
    }
}
