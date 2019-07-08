/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;


import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author Squalo
 */
public class UI extends Application {
     StringProperty title  =  new SimpleStringProperty();
    Text tsX;
    Text tsY;
    Text tsW;
    Text tsH;
    Text tsF;
    CheckBox cbr;
    CheckBox fullscreen;
    double dragX;
    double dragY;
    public static void main (String[]args){
        Application.launch(args);
    }
    @Override
        public void start(Stage stage){
       StageStyle st = StageStyle.DECORATED;
       List<String>unnamedParams = getParameters().getUnnamed();
       if (unnamedParams.size()> 0){
           String StageStyleParam = unnamedParams.get(0);
           if (StageStyleParam.equalsIgnoreCase("Transparent")){
               st = StageStyle.TRANSPARENT;    
           }
           else if(StageStyleParam.equalsIgnoreCase("Undecorated")){
               st = StageStyle.UNDECORATED;
           }
           else if (StageStyleParam.equalsIgnoreCase("Utility")){
               st = StageStyle.UTILITY;
           } 
       }
       final Stage stf = stage;
       Group rg;
       TextField ttf;
       Scene scene = SceneBuilder.create().width(270).height(370).fill(Color.TRANSPARENT).root( rg = GroupBuilder.create().children(RectangleBuilder.create().width(250).height(350).arcWidth(50).arcHeight(50).fill(Color.SKYBLUE).build(),
                 VBoxBuilder.create().layoutX(30).layoutY(20).spacing(10).children
        (tsX = TextBuilder.create().textOrigin(VPos.TOP).build(),
                         tsY = TextBuilder.create().textOrigin(VPos.TOP).build(),
                         tsH = TextBuilder.create().textOrigin(VPos.TOP).build(),
                         tsF = TextBuilder.create().textOrigin(VPos.TOP).build(),
                         cbr = CheckBoxBuilder.create().text("Resizable").disable(st == StageStyle.TRANSPARENT || st == StageStyle.UNDECORATED ).build(),
                         fullscreen = CheckBoxBuilder.create().text("FullScreen").build(),
                         HBoxBuilder.create().spacing(10).children(new Label ("Title:"),
                                 ttf = TextFieldBuilder.create().text("Stage Coach").prefColumnCount(15).build()).build(),
                        ButtonBuilder.create()
.text("toBack()")
.onAction((javafx.event.ActionEvent e) -> {
    stf.toBack();
       })
.build(),
ButtonBuilder.create()
.text("toFront()")
.onAction((javafx.event.ActionEvent e) -> {
    stf.toFront();
       })
.build(),
ButtonBuilder.create()
.text("close()")
.onAction((javafx.event.ActionEvent e) -> {
    stf.close();
       })
.build()
)
.build()
)
.build()
)
.build();
       // when mouse button is pressed, save the initial position of screen
    rg.setOnMousePressed((MouseEvent me) -> {
        dragX = me.getScreenX() - stf.getX();
        dragY = me.getScreenY() - stf.getY();
       });
// When screen is dragged, translate it accordingly
rg.setOnMouseDragged((MouseEvent me) -> {
    stf.setX(me.getScreenX() - dragX);
    stf.setY(me.getScreenY() - dragY);
       });
       tsX.textProperty().bind(new SimpleStringProperty("X: ").concat(stf.xProperty().asString()));
     tsY.textProperty().bind(new SimpleStringProperty("Y: ").concat(stf.yProperty().asString()));
      tsW.textProperty().bind(new SimpleStringProperty("Width: ").concat(stf.widthProperty().asString()));
       tsH.textProperty().bind(new SimpleStringProperty("Height: ").concat(stf.heightProperty().asString()));
        tsF.textProperty().bind(new SimpleStringProperty("Focused: ").concat(stf.focusedProperty().asString()));
        stage.setResizable(true);
        cbr.selectedProperty().bindBidirectional(stage.resizableProperty());
        fullscreen.selectedProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue ov, Object oldVal, Object newVal){
             stf.setFullScreen(fullscreen.selectedProperty().getValue());
            }
        });
        title.bind(ttf.textProperty());
        stage.setScene(scene);
        stage.titleProperty().bind(title);
        stage.initStyle(st);
       stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
public void handle(WindowEvent we) {
System.out.println("Stage is closing");
}
});
        stage.show();
        Rectangle2D psb = Screen.getPrimary().getVisualBounds();
        stage.setX((psb.getWidth()- stage.getWidth())/2);
         stage.setY((psb.getHeight()- stage.getHeight())/4);
    }

    /**
     * @param args the command line arguments
     */
    
    }