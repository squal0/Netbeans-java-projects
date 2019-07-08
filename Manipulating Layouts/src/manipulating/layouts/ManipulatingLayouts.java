/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulating.layouts;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Squalo
 */
public class ManipulatingLayouts extends Application {

    /**
     * @param args the command line arguments
     * the following program manipulates layouts via the grid
     * it allows the user to be able to change features on the gridPane
     */
    public static void main(String[] args) {
        launch(args);
    }

   
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Grid Manipulation Enhanced with CSS Styling");
        Group root = new Group();
        Scene scene = new Scene(root, 700, 520, Color.DARKSLATEGRAY);
       MenuBar menuBar = new MenuBar();
       Menu menu = new Menu("Look 'N' Feel");
       menuBar.getMenus().add(menu);
        // stretch menu
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        // left and right split pane
        SplitPane sp = new SplitPane();
        sp.prefWidthProperty().bind(scene.widthProperty());
        sp.prefHeightProperty().bind(scene.heightProperty());
        
        // form on the right
        GridPane rgp = new MyForm();
        GridPane lgp = new GridPanelControlPanel(rgp);
        VBox leftArea = new VBox(10);
        leftArea.getChildren().add(lgp);
        HBox hbox = new HBox();
        hbox.getChildren().add(sp);
        VBox vbox = new VBox();
        vbox.getChildren().add(menuBar);
        vbox.getChildren().add(hbox);
        root.getChildren().add(hbox);
        sp.getItems().addAll(leftArea, rgp);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    
}

