/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author Oracion Seis
 */
 import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
 import javafx.scene.layout.GridPane;
import javafx.scene.Group;
import javafx.scene.Scene;
 import javafx.stage.Stage; 
 import javafx.stage.Stage;
 import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public class Components extends Application {

    /**
     * @param args the command line arguments
     * adding components to a window
     */
   
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Components:");
        Group root  = new Group();
         Scene scene = new Scene(root,400,150, Color.DARKOLIVEGREEN);
       // GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5));
        grid.setHgap(5); 
        grid.setVgap(5);
        
        // components
        Label name  = new Label ("User Name");
        Font  mq  = Font.font("Marquis M7 Script Capitals", 13);
        name.setFont(mq);
        TextField tName = new TextField ();
        Label password  = new Label ("Password");
         Font  m  = Font.font("Marquis M7 Script Capitals", 13);
        password.setFont(m);
        PasswordField  tPass =  new PasswordField();
        Button login  = new Button ("Login");
         Font  b = Font.font("Marquis M7 Script Capitals", 14);
         login.setFont(b);
        Reflection r = new Reflection();
      r.setFraction(0.8f);
      login.setEffect(r);
        
        // name label allignment
        GridPane.setHalignment(name, HPos.RIGHT);
        grid.add(name, 0, 0);
        
        // password label allignment
        GridPane.setHalignment(name, HPos.RIGHT);
        grid.add(password, 0, 1);
        
        // name text field
        GridPane.setHalignment(tName, HPos.LEFT);
        grid.add(tName, 1, 0);
        
        // pass word text field
        GridPane.setHalignment(tPass, HPos.LEFT);
        grid.add(tPass, 1, 1);
        
        //  button
        // name text field
        GridPane.setHalignment(login, HPos.RIGHT);
        grid.add(login, 1, 2);
        
        root.getChildren().add(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}
