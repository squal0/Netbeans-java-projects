/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package onthescene;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceBoxBuilder;
import javafx.scene.control.HyperlinkBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioButtonBuilder;
import javafx.scene.control.Slider;
import javafx.scene.control.SliderBuilder;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
/**
 *
 * @author Squalo
 */
public class OnTheScene extends Application {
    DoubleProperty fillVals = new SimpleDoubleProperty(255.0);
    Scene sceneRef;
    ObservableList cursors = FXCollections.observableArrayList(
    Cursor.DEFAULT,
            Cursor.CROSSHAIR,
            Cursor.WAIT,
            Cursor.TEXT,
            Cursor.HAND,
            Cursor.MOVE,
            Cursor.N_RESIZE,
            Cursor.NE_RESIZE,
            Cursor.E_RESIZE,
            Cursor.SE_RESIZE,
            Cursor.SW_RESIZE,
            Cursor.W_RESIZE,
            Cursor.NW_RESIZE,
            Cursor.NONE);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   @Override
    public void start(Stage primaryStage) {
     Slider sliderR;
     ChoiceBox choiceBoxR;
     Text tsX;
     Text tsY;
     Text tsW;
     Text tsH;
     Label lsX;
     Label lsY;
     Label lsW;
     Label lsH;
     final ToggleGroup tgp = new ToggleGroup();
     FlowPane sRoot = FlowPaneBuilder.create()
             .layoutX(20)
             .layoutY(40)
             .padding(new Insets(0,20,40,0))
             .orientation(Orientation.VERTICAL)
             .vgap(10)
             .hgap(20)
             .columnHalignment(HPos.LEFT)
             .children(HBoxBuilder.create().spacing(10).children(sliderR = SliderBuilder.create()
             .min(0)
             .max(255)
             .value(255)
             .orientation(Orientation.VERTICAL)
             .build(),
                     choiceBoxR = ChoiceBoxBuilder.create()
             .items(cursors)
             .build())
             .build(),
                    tsX = TextBuilder.create()
                             .styleClass("Emphasized Text")
                             .build(),
                    tsY = TextBuilder.create()
                             .styleClass("Emphasized Text")
                             .build(),
                    tsW = TextBuilder.create()
                             .styleClass("Emphasized Text")
                             .build(),
                    tsH = TextBuilder.create()
                             .styleClass("Emphasized Text")
                             .id("SceneHeightText")
                     .build(),
                    HyperlinkBuilder.create()
                     .text("Lookup()")
                     .onAction((javafx.event.ActionEvent e) -> {
                         System.out.println("SceneRef: "+sceneRef);
                         Text textRef  = (Text)sceneRef.lookup("#SceneHeightText");
                         System.out.println(textRef.getText());
     })
                     .build(),
                    RadioButtonBuilder.create()
                     .text("onTheScene.css")
                     .toggleGroup(tgp)
                     .selected(true)
                     .build(),
                    RadioButtonBuilder.create()
                     .text("onTheScene.css")
                     .toggleGroup(tgp)
                     .selected(true)
                     .build(),
                    lsX = LabelBuilder.create()
                     .id("StageX")
                     .build(),
                    lsY = LabelBuilder.create()
                     .id("StageY")
                     .build(),
                    lsW = new Label(),
                    lsH = new Label()
                      )
             .build();
     sceneRef = SceneBuilder.create()
             .width(600)
             .height(250)
             .root(sRoot)
             .build();
     sceneRef.getStylesheets().addAll(OnTheScene.class.getResource("onTheScene.css").toExternalForm());
     primaryStage.setScene(sceneRef);
     primaryStage.setResizable(true);
     choiceBoxR.getSelectionModel().selectFirst();
     // set up various property binding
       tsX.textProperty().bind(new SimpleStringProperty("X: ").concat(sceneRef.xProperty().asString()));
     tsY.textProperty().bind(new SimpleStringProperty("Y: ").concat(sceneRef.yProperty().asString()));
      tsW.textProperty().bind(new SimpleStringProperty("Width: ").concat(sceneRef.widthProperty().asString()));
       tsH.textProperty().bind(new SimpleStringProperty("Height: ").concat(sceneRef.heightProperty().asString()));
     lsX.textProperty().bind(new SimpleStringProperty("X: ").concat(sceneRef.xProperty().asString()));
      lsY.textProperty().bind(new SimpleStringProperty("Y: ").concat(sceneRef.yProperty().asString()));
      lsW.textProperty().bind(new SimpleStringProperty("Width: ").concat(sceneRef.widthProperty().asString()));
      lsH.textProperty().bind(new SimpleStringProperty("Height: ").concat(sceneRef.widthProperty().asString()));
      sceneRef.cursorProperty().bind(choiceBoxR.getSelectionModel().selectedItemProperty());
      fillVals.addListener(new ChangeListener(){
          public void changed (ObservableValue ov, Object oldVal, Object newVal){
              Double fillValue = fillVals.getValue()/256.0;
              sceneRef.setFill(new Color(fillValue,fillValue,fillValue,1.0));
          }
      });
      primaryStage.setTitle("On the Scene");
      primaryStage.show();
      
      // define an unmanaged node that will display the text
      Text addedTRef= TextBuilder.create()
              .layoutX(0)
              .layoutY(-30)
              .textOrigin(VPos.TOP)
              .fill(Color.BLUE)
              .font(Font.font("Matura M7 Script Capitals", FontWeight.BOLD,18))
              .managed(false)
              .build();
      // bind the text of the added text node to the fill property of the scene
      addedTRef.textProperty().bind(new SimpleStringProperty("Scene fill:")
      .concat(sceneRef.fillProperty()));
      // add to the text node to the flow pane
      ((FlowPane)sceneRef.getRoot()).getChildren().add(addedTRef);
    } 
}
