/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulating.layouts;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Squalo
 * // my form is a for to be manipulated by the user
 */
class MyForm extends GridPane {

    public MyForm() {
         setPadding(new Insets(5));
        setHgap (5);
        setVgap(5);
        Label fName = new Label("First Name");
        TextField fNameField = new TextField();
        Label lName = new Label("Last Name");
        TextField lNameField = new TextField();
        Label age  = new Label ("Age");
         TextField ageField = new TextField();
        Label password = new Label("Password");
        PasswordField pass = new PasswordField();
        Button save = new Button("Save");
        
        // first name label
        GridPane.setHalignment(fName, HPos.RIGHT);
        add(fName ,0,0);
        
        // last name label 
        GridPane.setHalignment(lName, HPos.RIGHT);
        add(lName ,0,1);
        
        // age label
        GridPane.setHalignment(age, HPos.RIGHT);
        add(age ,0,2);
        
        // password label
        GridPane.setHalignment(password, HPos.RIGHT);
        add(password,0,3);
        
        // First name field
        GridPane.setHalignment(fNameField, HPos.LEFT);
        add(fNameField ,1,0);
        
        // Last Name field
        GridPane.setHalignment(lNameField, HPos.LEFT);
        add(lNameField , 1,1);
        
        // Age field
        GridPane.setHalignment(ageField, HPos.RIGHT);
        add(ageField , 1,2);
        
        //password field
        GridPane.setHalignment(pass, HPos.LEFT);
        add(pass,1,3);
        
        // save button
        GridPane.setHalignment(save, HPos.RIGHT);
        add(save,1,4);
    }
    
}
