/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animate.path;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.animation.PathTransition;
import javafx.animation.PathTransitionBuilder;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.SceneBuilder;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Squalo
 * code demonstrates drawing a path for a shape to follow
 * here we work with scene graph
 */
public class AnimatePath extends Application {

    /**
     * @param args the command line arguments
     */
    Path thePath  = new Path();
    Point2D anchor;
    public static void main(String[] args) {
       Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Animating a Path");
        final Group root  = new Group();
        // add the path
        root.getChildren().add(thePath);
        final Scene scene = SceneBuilder.create().root(root).width(400).height(300).fill(Color.WHITESMOKE).build();
        RadialGradient grd1 = new RadialGradient(0,.1,100,100,20,false,CycleMethod.NO_CYCLE,new Stop(0, Color.RED),new Stop(1, Color.BLACK));
        // create a sphere
        final Circle sphere = CircleBuilder.create().centerX(100).centerY(100).radius(20).fill(grd1).build();
        // add the sphere
        root.getChildren().addAll(sphere);
        
        // animate the sphere by following the path
        final PathTransition ptr = PathTransitionBuilder.create().duration(Duration.millis(6000)).cycleCount(1).node(sphere).path(thePath).orientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT).build();
        // once finished clear the path
        ptr.onFinishedProperty().set(new EventHandler <ActionEvent>(){
            @Override
        public void handle (ActionEvent evt){
          thePath.getElements().clear();  
        }
    });
     // start initial path
        scene.onMousePressedProperty().set(new EventHandler <MouseEvent> (){
            @Override
            public void handle (MouseEvent evt){
                // clear the path
                thePath.getElements().clear();
                // start point in path
                anchor = new Point2D(evt.getX(), evt.getY());
                thePath.setStrokeWidth(3);
                thePath.setStroke(Color.BLACK);
                thePath.getElements().add(new MoveTo(anchor.getX(), anchor.getY()));
            }
        });
        // dragging creates line tos added to the path
        scene.onMouseDraggedProperty().set(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent evt){
                thePath.getElements().add(new LineTo(evt.getX(),evt.getY()));
            }
        });
        // end path when mouse is released event
        scene.onMouseReleasedProperty().set(new EventHandler <MouseEvent>(){
            @Override
            public void handle (MouseEvent evt){
                thePath.setStrokeWidth(0);
                if (thePath.getElements().size() > 1){
                    ptr.stop();
                    ptr.playFromStart();
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
