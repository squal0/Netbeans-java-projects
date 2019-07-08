/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package suspectedthreats;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Squalo
 */
public class SuspectedThreats extends Application {

    
/**
     * @param args the command line arguments
     * program below shows a table view of data 
     * it uses the observable lists classes and FX Collections to make this happen
     */
    public class PersonDetails{
    private StringProperty FirstName;
    private StringProperty LastName;
    private StringProperty Alias;
    private StringProperty emailAddress;
    private StringProperty Location;
    
    private ObservableList <PersonDetails> person  = FXCollections.observableArrayList();
    public final void setFirstName(String value){
       fNameProperty().set(value);
    }
    public final String getFName(){
        return fNameProperty().get();
    }
    public StringProperty fNameProperty(){
        if(FirstName == null){
            FirstName = new SimpleStringProperty();
        }
        return FirstName;
    }
     public final void setLastName(String value){
       LNameProperty().set(value);
    }
    public final String getLName(){
        return LNameProperty().get();
    }
    public StringProperty LNameProperty(){
        if(LastName == null){
            LastName = new SimpleStringProperty();
        }
        return LastName;
    }
     public final void setAlias(String value){
       AliasNameProperty().set(value);
    }
    public final String getAlias(){
        return AliasNameProperty().get();
    }
    public StringProperty AliasNameProperty(){
        if(Alias == null){
            Alias = new SimpleStringProperty();
        }
        return Alias;
    }
     public final void setEmail(String value){
       emailProperty().set(value);
    }
    public final String getEmail(){
        return emailProperty().get();
    }
    public StringProperty emailProperty(){
        if(emailAddress == null){
            emailAddress = new SimpleStringProperty();
        }
        return emailAddress;
    }
     public final void setLocation(String value){
       locationProperty().set(value);
    }
    public final String getLocation(){
        return locationProperty().get();
    }
    public StringProperty locationProperty(){
        if(Location == null){
            Location = new SimpleStringProperty();
        }
        return Location;
    } 
    public ObservableList<PersonDetails> suspectedProperty(){
        return person;
    }
    public PersonDetails(String FirstName, String LastName, String Alias, String emailAddress, String Location){
      setFirstName(FirstName);
      setLastName (LastName);
      setAlias (Alias);
      setEmail(emailAddress);
      setLocation(Location);
    }
    }
    public static void main(String[] args) {
        launch(args);
         // main application code that displays the threat a person posses to national security
    }
    

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Potential Threats to National Security");
        Group root  = new Group();
        Scene scene = new Scene (root ,750 ,500, Color.LIGHTSLATEGRAY );
        
        //create a grid pane
        
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10));
        gp.setHgap(20);
        gp.setVgap(20);
        
        Label lbl1  = new Label ("Threat Risk");
        GridPane.setHalignment(lbl1, HPos.CENTER);
        gp.add(lbl1, 0, 0);
        
        // Threat Risk Gauge
        ObservableList<PersonDetails> risk = getriskLevel();
        final ListView <PersonDetails> riskListView  = new ListView<>(risk);
        riskListView.setPrefWidth(180);
        riskListView.setPrefHeight(120);
        
        // display first and last name with tool tip using alias
       riskListView.setCellFactory(new Callback<ListView<PersonDetails>, ListCell<PersonDetails>>() {
public ListCell<PersonDetails> call(ListView<PersonDetails> param) {
         final Label riskLbl = new Label();
         final Tooltip tp = new Tooltip();
         final ListCell <PersonDetails> cell = new ListCell <PersonDetails>(){
            @Override
            
            public void updateItem(PersonDetails item , boolean empty){
                super.updateItem(item, empty);
                if(item != null){
                    riskLbl.setText(item.getAlias());
                    setText(item.getFName()+ " " + item.getLName()+" "+ item.getEmail()+" "+ item.getLocation());
                    tp.setText(item.getAlias());
                    setTooltip(tp);
                }
            }
         }; // 
         return cell;
        }
    }); // setCell factory
        
       gp.add(riskListView, 0, 1);
       Label lbl2 = new Label("Suspected Persons");
       GridPane.setHalignment(lbl2, HPos.CENTER);
       final TableView<PersonDetails> suspectTableView = new TableView<>();
suspectTableView.setPrefWidth(500);
final ObservableList<PersonDetails> suspects  = FXCollections.observableArrayList();
suspectTableView.setItems(suspects);
TableColumn<PersonDetails, String> aliasNameCol = new TableColumn("Alias");
aliasNameCol.setEditable(true);
aliasNameCol.setCellValueFactory(new PropertyValueFactory("Alias Name"));
aliasNameCol.setPrefWidth(suspectTableView.getPrefWidth() /3);

TableColumn<PersonDetails, String> firstNameCol = new TableColumn("First Name");
firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
firstNameCol.setPrefWidth(suspectTableView.getPrefWidth() /3); 

TableColumn<PersonDetails, String> lastNameCol = new TableColumn("Last Name");
lastNameCol.setCellValueFactory(new PropertyValueFactory("lasttName"));
lastNameCol.setPrefWidth(suspectTableView.getPrefWidth() /3); 

TableColumn<PersonDetails, String> emailCol = new TableColumn("Email Address");
emailCol.setCellValueFactory(new PropertyValueFactory("email"));
emailCol.setPrefWidth(suspectTableView.getPrefWidth() /3); 

TableColumn<PersonDetails, String> locationCol = new TableColumn("Location");
locationCol.setCellValueFactory(new PropertyValueFactory("location"));
locationCol.setPrefWidth(suspectTableView.getPrefWidth() /3); 

gp.add(suspectTableView, 2, 1);

//selection listening
riskListView.getSelectionModel().selectedItemProperty().addListener(new
ChangeListener<PersonDetails>() {
    public void changed(ObservableValue<? extends PersonDetails> observable  , PersonDetails oldValue, PersonDetails newValue){
     if(observable != null && observable.getValue() != null){
         suspects.clear();
         suspects.addAll(observable.getValue().suspectedProperty());
     }
    }
});
        root.getChildren().add(gp);       
        primaryStage.setScene(scene);
        primaryStage.show();
    }

        /*The following code is the getPeople() method contained in the WorkingWithTables main
application class. This method helps to populate the UI TableView control shown previously:
*/
  private ObservableList<PersonDetails> getriskLevel() {
      ObservableList<PersonDetails> people = FXCollections.<PersonDetails>observableArrayList();
     
      // Bio terrorists
      PersonDetails bio  = new PersonDetails("Bio Terrorist", "Level 10"," Dangerous viruses"," Human Experiments "," Drug Trafficking");
      bio.suspectedProperty().add(new PersonDetails("Bosconovitch", "James " ,"Bosconovitch", "No Know email address", "Moscow, Russia"));
       bio.suspectedProperty().add(new PersonDetails("Renegade", "Rayson " ,"Allie", "No Know email address", "Nairobi, Kenya"));
        bio.suspectedProperty().add(new PersonDetails("Doctorine", "Dr Anne " ,"Rajesh", "No Know email address", "Mumbai, India"));
         bio.suspectedProperty().add(new PersonDetails("Black Widow", "Samantha " ,"Smith", "sm@gmail.com", "London, United Kingdom"));
          bio.suspectedProperty().add(new PersonDetails("Papatende", "Olubane" ,"Osikichebe", "No Know email address", "Accra , Ghana"));
     
          //Cyber terrorists
          PersonDetails cyber = new PersonDetails("Cyber Terrorist","Level 6 ", "Black Hat", "Online Extortionist", " Suicide Hackers");
          cyber.suspectedProperty().add(new PersonDetails("Xavier", "Lee", "Young", "No available email address", "Shanghai , China"));
          cyber.suspectedProperty().add(new PersonDetails("AngelDust", "Christine", "HeideMarie", "No available email address", "Berlin , Germany"));
          
          
          people.add(bio);
          people.add(cyber);
      return people ;
        
    }
}