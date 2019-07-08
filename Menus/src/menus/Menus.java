/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;


/**
 *
 * @author Squalo
 * creating menus
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class Menus extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Creating Menus");
        Group root  = new Group();
      Scene scene = new Scene(root, 300,250, Color.DARKSLATEGREY);
      MenuBar  mb = new MenuBar();
      
      // file menu - new save exit
      Menu mn = new Menu("File");
      mn.getItems().add(new MenuItem("New"));
       mn.getItems().add(new MenuItem("Save"));
       mn.getItems().add(new SeparatorMenuItem());
        mn.getItems().add(new MenuItem("Exit"));
        mb.getMenus().add(mn);
        
        // menu items
        Menu sort = new Menu("Sorting");
        sort.getItems().add(CheckMenuItemBuilder.create().text("Merge Sort").selected(true).build());
        sort.getItems().add(CheckMenuItemBuilder.create().text("Shell Sort").selected(true).build());
        sort.getItems().add(CheckMenuItemBuilder.create().text("Quick Sort").selected(true).build());
        mb.getMenus().add(sort);
        
        Menu sec = new Menu("Encryption");
        ToggleGroup tGroup = new ToggleGroup();
RadioMenuItem encrypt = RadioMenuItemBuilder.create().toggleGroup(tGroup).text("Encrypt").build();
         RadioMenuItem decrypt = RadioMenuItemBuilder.create().toggleGroup(tGroup).text("Decrypt").build();
        sec.getItems().add(encrypt);
        sec.getItems().add(decrypt);
mb.getMenus().add(sec);

 Menu about = new Menu("Help");
     about.getItems().add(new CheckMenuItem("About"));
about.getItems().add(new CheckMenuItem("Check For Updates "));
about.getItems().add(new CheckMenuItem("Contact Us "));

mb.getMenus().add(about);
        
mb.prefWidthProperty().bind(primaryStage.widthProperty());
root.getChildren().add(mb);
          primaryStage.setScene(scene);
        primaryStage.show();
    }
}
