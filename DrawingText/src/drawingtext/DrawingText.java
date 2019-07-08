/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingtext;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Squalo
 */
public class DrawingText extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Oracion Seis Training");
        Group root  = new Group();
        Scene scene  = new Scene(root, 500, 450, Color.WHITE);
        Random rand  = new Random(System.currentTimeMillis());
        for(int i =0; i < 100; i++){
            int x = rand.nextInt((int) scene.getWidth());
            int y = rand.nextInt((int) scene.getHeight());
            int red = rand.nextInt(255);
            int green = rand.nextInt(255);
            int blue = rand.nextInt(255);
            
            Text text = new Text(x,y, "Oracion Seis");
            int rot  = rand.nextInt(360);
            text.setFill(Color.rgb(red, green, blue, .99));
            text.setRotate(rot);
            root.getChildren().add(text);
        }
        primaryStage.setScene(scene);
        primaryStage.show();
        } 
    }

