/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package keyboard.shortcuts;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;

import javafx.stage.Stage;

/**
 *program displays keyboard shortcuts mainly found on a menu of any program
 * @author Squalo
 */
public class KeyBoardShortcuts extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Keyboard Shortcuts");
        Group root  = new Group();
        Scene scene  = new Scene(root, 500, 270, Color.LIGHTSTEELBLUE);
        
        final StringProperty statusProperty = new SimpleStringProperty();
        InnerShadow  iS = InnerShadowBuilder.create().offsetX(3.5f).offsetY(3.5f).build();
        final Text status  = TextBuilder.create().effect(iS).x(100).y(50).fill(Color.LIME).font(Font.font(null, FontWeight.BOLD,30)).translateY(50).build();
       status.textProperty().bind(statusProperty);
       statusProperty.set("Keyboard Shortcuts \nCtrl-N, \nCtrl-S, \nCtrl-X");
       root.getChildren().add(status);
       
       // Menu Bar
       MenuBar mb = new MenuBar();
       mb.prefWidthProperty().bind(primaryStage.widthProperty());
       root.getChildren().add(mb);
       
       //Menu
       Menu mn = new Menu("File");
       mb.getMenus().add(mn);
      MenuItem nI = MenuItemBuilder.create()
.text("New")
.accelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN))
.onAction(new EventHandler<ActionEvent>() {
public void handle(ActionEvent event) {
statusProperty.set("Ctrl-N");
}
})
.build();
    mn.getItems().add(nI);  
    
    MenuItem sI = MenuItemBuilder.create()
.text("Save")
.accelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN))
.onAction(new EventHandler<ActionEvent>() {
public void handle(ActionEvent event) {
statusProperty.set("Ctrl-S");
}
})
.build();
    mn.getItems().add(sI);
    mn.getItems().add(new SeparatorMenuItem());
    
     MenuItem eI = MenuItemBuilder.create()
.text("Exit")
.accelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN))
.onAction(new EventHandler<ActionEvent>() {
public void handle(ActionEvent event) {
statusProperty.set("Ctrl-X");
}
})
.build();
    mn.getItems().add(eI);
    
    primaryStage.setScene(scene); 
    primaryStage.show();
    }
}
