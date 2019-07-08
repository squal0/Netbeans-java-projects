/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package effects;


/**
 *
 * @author Squalo
 */
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class Effects extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
     primaryStage.setTitle("Changing Fonts and Adding effects");
      Group root = new Group();
     Scene scene  = new Scene(root, 500, 450, Color.WHITE);
     
     // serif with dropShadow effect
     Text text2 = new Text (50, 50, "Superbi Squalo");
     Font seriff = Font.font("Serif", 30);
     text2.setFill(Color.CYAN);
     DropShadow dropS = new DropShadow();
     dropS.setOffsetX(2.0f);
     dropS.setOffsetY(2.0f);
     dropS.setColor(Color.rgb(50, 50, 50 ,.588));
     text2.setEffect(dropS);
     text2.setFont(seriff);
     root.getChildren().add(text2);
     
     // san serif
     Text text3 = new Text (50, 100, "Superbi Squalo");
     Font sanserif  = Font.font("SanSerif", 30);
     text3.setFont(sanserif);
     text3.setFill(Color.BLUE);
     root.getChildren().add(text3);
     
     // Dialog
      Text text4 = new Text (50, 150, "Superbi Squalo");
      Font dialog  = Font.font("Dialog", 30);
      text4.setFont(dialog);
      text4.setFill(Color.rgb(0, 255, 0));
      root.getChildren().add(text4);
      
      // monospaced
       Text text5 = new Text (50, 200, "Superbi Squalo");
      Font monoFont  = Font.font("Monospaced", 30);
      text4.setFont(monoFont);
      text4.setFill(Color.BLACK);
      root.getChildren().add(text5);
      Reflection r = new Reflection();
      r.setFraction(0.8f);
      text5.setEffect(r);
      primaryStage.setScene(scene);
      primaryStage.show();
    }
}
