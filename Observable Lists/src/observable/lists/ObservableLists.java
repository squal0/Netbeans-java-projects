/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package observable.lists;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Squalo
 */
public class ObservableLists extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("List Of Shonen Anime");
   Group root = new Group();
   Scene scene = new Scene (root , 655, 450 , Color.SILVER);
   
   // create the grid pane
   GridPane grid  = new GridPane();
   grid.setPadding(new Insets(5));
   grid.setHgap(10);
   grid.setVgap(10);
   
   // labels for all the available anime
   Label anime  = new Label("Anime");
   Font fAnime = Font.font("Marquis M7 Script Capitals", 16);
   anime.setFont(fAnime);
   GridPane.setHalignment(anime, HPos.CENTER);
   grid.add(anime, 0, 0);
   
   // Shonen label
   Label sAnime  = new Label("Shonen Anime");
    Font shAnime = Font.font("Marquis M7 Script Capitals", 16);
   sAnime.setFont(shAnime);
   GridPane.setHalignment(sAnime, HPos.CENTER);
   grid.add(sAnime, 2, 0);
   
   // all the available anime
   final ObservableList<String> allAnime= FXCollections.observableArrayList("Naruto",
           "Kami Sama Hajimemashita",
           "Elfen Lied",
           "Deadman Wonderland",
           "Bleach",
           "One Piece",
           "Gin No Saji",
           "Hataraku no Maou Sama",
           "Clannad",
           "Yondemasu Azazel San",
           "Witch Blade",
           "Another",
           "Mirai Nikki",
           "Fairy Tail",
           "Fate Stay Night",
           "Clannad After Story",
           "Fate Zero",
           "Magi",
           "Kyoukai No Kanata",
           "ZetMan",
           "Death Note",
           "Monster",
           "A certain Magical Index",
           "A certain Scientific Railgun",
           "Prince Of Tennis",
           "Zetsu no Tempest",
           "kings Blade",
           "Stein's Gate",
           "Soul Eater",
           "Yosuga No Sora",
           "Shingeki No Kyojin",
           "Samurai 7",
           "Pyscho pass",
           "Moribito Guardian of The Spirit",
           "Makai Ouji",
           "Kuroshitsugi");
   final ListView<String> animeList  = new ListView<String> (allAnime);
   animeList.setPrefWidth(300);
   animeList.setPrefHeight(200);
   grid.add(animeList, 0, 1);
   
   // shonen anime
   final ObservableList<String> shonen = FXCollections.observableArrayList();
   final ListView<String> shonenList = new ListView<String> (shonen);
   shonenList.setPrefWidth(300);
   shonenList.setPrefHeight(200);
   grid.add(shonenList, 2, 1);
   
   // selected Shonen Anime
   Button sRight = new Button (">");
   sRight.setOnAction(new EventHandler <ActionEvent>(){
       public void handle(ActionEvent event){
           String potential = animeList.getSelectionModel().getSelectedItem();
           if (potential != null){
               animeList.getSelectionModel().clearSelection();
               allAnime.remove(potential);
               shonen.add(potential);
           }
       }
   });
   // deselected anime
   Button sLeft  = new Button("<");
    sLeft.setOnAction(new EventHandler <ActionEvent>(){
       public void handle(ActionEvent event){
           String notShonen = shonenList.getSelectionModel().getSelectedItem();
           if (notShonen != null){
               shonenList.getSelectionModel().clearSelection();
               shonen.remove(notShonen);
               allAnime.add(notShonen);
    }
       }
       });
    VBox vbox = new VBox(10);
    vbox.getChildren().addAll(sRight,sLeft);
    grid.add(vbox, 1, 1);
    GridPane.setConstraints(vbox, 1, 1, 1, 2,HPos.CENTER, VPos.CENTER);
    root.getChildren().add(grid);
    primaryStage.setScene(scene);
    primaryStage.show();
}
}