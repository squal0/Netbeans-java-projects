/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generating.borders;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Oracion Seis
 * // generates borders around an image
 */
public class GeneratingBorders extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    primaryStage.setTitle("Generating Borders around an Image");  
    Group root = new Group();
    Scene scene  = new Scene(root , 700 , 405, Color.IVORY);
    
    // creating the Grid Pane
    GridPane grid  = new GridPane();
    grid.setPadding(new Insets(6));
    grid.setHgap(15);
    grid.setVgap(15);
    
    // label CSS editor
    Label editor  = new Label ("CSS Editor");
    Font cs = Font.font("Elephant", 18);
    editor.setFont(cs);
    GridPane.setHalignment(editor, HPos.CENTER);
    grid.add(editor, 0, 0);
    
    // label border view
     Label border  = new Label ("Border View");
    Font bs = Font.font("Elephant", 16);
    border.setFont(bs);
    GridPane.setHalignment(editor, HPos.CENTER);
    grid.add(border, 1, 0);
    
    // Text area for CSS editor
    final TextArea  editorT  = new TextArea();
    editorT.setPrefRowCount(15);
    editorT.setPrefColumnCount(115);
    editorT.setWrapText(true);
    editorT.setPrefWidth(150);
    GridPane.setHalignment(editorT, HPos.CENTER);
    grid.add(editorT, 0, 1);
    
    String cssDefault = "-fx-border-color: blue;\n"
+ "-fx-border-insets: 5;\n"
+ "-fx-border-width: 3;\n"
+ "-fx-border-style: dashed;\n";
    editorT.setText(cssDefault);
    
    // border decorate the picture
    final ImageView imv = new ImageView();
    final Image image = new Image(GeneratingBorders.class.getResourceAsStream("020.jpg"));
    imv.setImage(image);
    final HBox pictureRegion  = new HBox();
    pictureRegion.setStyle(cssDefault);
    pictureRegion.getChildren().add(imv);
    grid.add(pictureRegion, 1, 1);
    
    Button apply  = new Button("Bling!");
    GridPane.setHalignment(apply, HPos.RIGHT);
    grid.add(apply, 0,2);
    
    apply.setOnAction(new EventHandler< ActionEvent>(){ 
        public void handle (ActionEvent event){
            pictureRegion.setStyle(editorT.getText());
        }
    });
    
     root.getChildren().add(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
