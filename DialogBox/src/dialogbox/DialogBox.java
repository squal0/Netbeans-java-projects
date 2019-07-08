/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogbox;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Oracion Seis
 */
public class DialogBox extends Application {

    /**
     * @param args the command line arguments
     */
    static Stage LOGIN_DIALOG;
    static int dx = 1;
    static int dy =1;
    public static void main(String[] args) {
        Application.launch(args);
    }
    private static Stage createLoginDialog(Stage parent ,boolean modal ){
        if(LOGIN_DIALOG != null){
            LOGIN_DIALOG.close();
        }
        return new MyDialog(parent, modal ,"Welcome to Oracion Seis");
    }
 @Override
 public void start (final Stage primaryStage){
     primaryStage.setTitle("Oracion Seis");
     Group root  = new Group();
     Scene scene  = new Scene (root , 450 , 315 , Color.LIGHTSLATEGREY);
     MenuBar mb = new MenuBar();
     mb.prefWidthProperty().bind(primaryStage.widthProperty());
     Menu menu  = new Menu("Home");
     
     // add password change menu item
     MenuItem mn = new MenuItem("Change Password", null);
     mn.setOnAction(new EventHandler <ActionEvent> (){
         public void handle (ActionEvent event){
             if(LOGIN_DIALOG  == null){
                 LOGIN_DIALOG  = createLoginDialog(primaryStage, true);
             }
             LOGIN_DIALOG.sizeToScene();
             LOGIN_DIALOG.show();
         }
     });
     menu.getItems().add(mn);
     // add separator
     menu.getItems().add(new SeparatorMenuItem());
     
     // add non modal menu item
     ToggleGroup mG = new ToggleGroup();
     RadioMenuItem nmd = RadioMenuItemBuilder.create().toggleGroup(mG).text("Non Modal").selected(true).build();
 nmd.setOnAction(new EventHandler <ActionEvent>(){
     public void handle (ActionEvent event){
         LOGIN_DIALOG = createLoginDialog(primaryStage, false);
     }
 });
 menu.getItems().add(nmd);
 
 // add modal selection
  RadioMenuItem md = RadioMenuItemBuilder.create().toggleGroup(mG).text("Modal").selected(true).build();
 md.setOnAction(new EventHandler <ActionEvent>(){
     public void handle (ActionEvent event){
         LOGIN_DIALOG = createLoginDialog(primaryStage, true);
     }
 });
 menu.getItems().add(md);
 //add a separator
  menu.getItems().add(new SeparatorMenuItem());
  
  // add exit
  MenuItem exit  = new MenuItem("Exit", null);
  exit.setMnemonicParsing(true);
  exit.setAccelerator(new KeyCodeCombination (KeyCode.X, KeyCombination.CONTROL_DOWN));
  exit.setOnAction(new EventHandler <ActionEvent>(){
      public void handle (ActionEvent event){
         Platform.exit(); 
      }
  });
  menu.getItems().add(exit);
  // add menu
  mb.getMenus().add(menu);
  // add menu bar to window
  root.getChildren().add(mb);
  primaryStage.setScene(scene);
  primaryStage.show();
  addBBall(scene);
 }

 private void addBBall(final Scene scene){
  final Circle ball= new Circle (100, 100, 20);
  RadialGradient gd1 = new RadialGradient(0, 1,100,100,20,false,CycleMethod.NO_CYCLE, new Stop(0, Color.CYAN), new Stop(1, Color.BLACK));
  ball.setFill(gd1);
  final Group root = (Group) scene.getRoot();
  root.getChildren().add(ball);
  Timeline tl = new Timeline();
  tl.setCycleCount(Animation.INDEFINITE);
  KeyFrame moveBall = new KeyFrame (Duration.seconds(.0200), new EventHandler <ActionEvent> (){
      public void handle (ActionEvent event){
          double xMin = ball.getBoundsInParent().getMinX();
          double yMin = ball.getBoundsInParent().getMinY();
          double xMax = ball.getBoundsInParent().getMaxX();
          double yMax = ball.getBoundsInParent().getMaxY();
          
          // collision boundaries
          if(xMin < 0 || xMax > scene.getHeight()){
              dx = dx * -1;
          }
           if(yMin < 0 || yMax > scene.getHeight()){
              dy = dy * -1;
           }
           ball.setTranslateX(ball.getTranslateX()+ dx);
           ball.setTranslateY(ball.getTranslateY()+ dy);
      }
  });
  tl.getKeyFrames().add(moveBall);
  tl.play();
 }
} 
class MyDialog extends Stage {
public MyDialog(Stage owner, boolean modality, String title) {
    super();
initOwner(owner);
Modality m = modality ? Modality.APPLICATION_MODAL : Modality.NONE;
initModality(m);
setOpacity(.90);
setTitle(title);
Group root = new Group();
Scene scene = new Scene(root, 250, 150, Color.WHITE);
setScene(scene);
GridPane gridpane = new GridPane();
gridpane.setPadding(new Insets(5));
gridpane.setHgap(5);
gridpane.setVgap(5);
Label mainLabel = new Label("Enter User Name & Password");
gridpane.add(mainLabel, 1, 0, 2, 1);
Label userNameLbl = new Label("User Name: ");
gridpane.add(userNameLbl, 0, 1);
Label passwordLbl = new Label("Password: ");
gridpane.add(passwordLbl, 0, 2);
// username text field
final TextField userNameFld = new TextField("Admin");
gridpane.add(userNameFld, 1, 1);
// password field
final PasswordField passwordFld = new PasswordField();
passwordFld.setText("kingsLanding");
gridpane.add(passwordFld, 1, 2);
Button login = new Button("Change");
login.setOnAction(new EventHandler<ActionEvent>() {
public void handle(ActionEvent event) {
close();
}
});
gridpane.add(login, 1, 3);
GridPane.setHalignment(login, HPos.RIGHT);
root.getChildren().add(gridpane);
}  
}

