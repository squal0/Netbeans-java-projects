/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulating.layouts;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Squalo
 * the grid control panel represents the left area of the split pane
 * allowing the user to manipulate elements on the right with the controls on the left
 */
public class GridPanelControlPanel extends GridPane {

    public GridPanelControlPanel(final GridPane tgp) {
        super();
        setPadding(new Insets(5));
        setHgap(5);
        setVgap(5);
        
        // setting the grid lines
        Label gridLines = new Label("Grid Lines");
        final ToggleButton gridLinesToggle = new ToggleButton("Off");
        gridLinesToggle.selectedProperty().addListener(new ChangeListener <Boolean>(){
            @Override
            public void changed (ObservableValue <? extends Boolean> ov,Boolean oldValue, Boolean newValue ){
             tgp.setGridLinesVisible(newValue);  
             gridLinesToggle.setText(newValue ? "On" : "Off");
            }
        });
        
        // toggle grid lines label
        GridPane.setHalignment(gridLines, HPos.RIGHT);
        add(gridLines ,0,0);
        
        // toggle grid lines
        GridPane.setHalignment(gridLinesToggle, HPos.LEFT);
        add(gridLinesToggle, 1,0);
        
        // setting padding [Top]
        Label gridPadding = new Label ("Top Padding");
        final Slider gridSlider = SliderBuilder.create().min(0).max(100).value(5).showTickLabels(true).showTickMarks(true).minorTickCount(1).blockIncrement(5).build();
        gridSlider.valueProperty().addListener(new ChangeListener <Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue){
             double top = tgp.getInsets().getTop();
             double right = tgp.getInsets().getRight();
             double bottom = tgp.getInsets().getBottom();
             double left = tgp.getInsets().getLeft();
             Insets newInsets = new Insets((double) newValue, right, bottom, left);
             tgp.setPadding(newInsets);
            }
        });
        // padding adjustment label
        GridPane.setHalignment(gridPadding, HPos.RIGHT);
        add(gridPadding ,0,1);
        // padding slider adjustment
        GridPane.setHalignment(gridSlider, HPos.LEFT);
        add(gridSlider ,1,1);
        
         // setting padding [Top]
        Label gridPadding1 = new Label ("Left Padding");
        final Slider gridSlider1 = SliderBuilder.create().min(0).max(100).value(5).showTickLabels(true).showTickMarks(true).minorTickCount(1).blockIncrement(5).build();
        gridSlider1.valueProperty().addListener(new ChangeListener <Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue){
             double top = tgp.getInsets().getTop();
             double right = tgp.getInsets().getRight();
             double bottom = tgp.getInsets().getBottom();
             double left = tgp.getInsets().getLeft();
             Insets newInsets = new Insets(top, right, bottom,(double) newValue);
             tgp.setPadding(newInsets);
            }
        });
        // padding adjustment label
        GridPane.setHalignment(gridPadding1, HPos.RIGHT);
        add(gridPadding1 ,0,2);
        // padding slider adjustment
        GridPane.setHalignment(gridSlider1, HPos.LEFT);
        add(gridSlider1 ,1,2);
        
        //Horizontal gap
         Label gridHPadding = new Label ("Horizontal Gap");
        final Slider gridHSlider = SliderBuilder.create().min(0).max(100).value(5).showTickLabels(true).showTickMarks(true).minorTickCount(1).blockIncrement(5).build();
        gridHSlider.valueProperty().addListener(new ChangeListener <Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue){
             tgp.setHgap((double) newValue);
            }
        });
        // hgap label
        GridPane.setHalignment(gridHPadding, HPos.RIGHT);
        add(gridHPadding ,0,3);
        // hgap slider
        GridPane.setHalignment(gridHSlider, HPos.LEFT);
        add(gridHSlider ,1,3);
        
         //Vertical gap
         Label gridVPadding = new Label ("Vertical Gap");
        final Slider gridVSlider = SliderBuilder.create().min(0).max(100).value(5).showTickLabels(true).showTickMarks(true).minorTickCount(1).blockIncrement(5).build();
        gridVSlider.valueProperty().addListener(new ChangeListener <Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue){
             tgp.setVgap((double) newValue);
            }
        });
        // vgap label
        GridPane.setHalignment(gridVPadding, HPos.RIGHT);
        add(gridVPadding ,0,4);
        // vgap slider
        GridPane.setHalignment(gridVSlider, HPos.LEFT);
        add(gridVSlider ,1,4);
        
        // cell column
        final Label cellCol = new Label ("Cell Column");
        final TextField cellColField  = new TextField("0");
        // cell column label
        GridPane.setHalignment(cellCol, HPos.RIGHT);
        add(cellCol, 0,5);
        // cell column field
        GridPane.setHalignment(cellColField, HPos.LEFT);
        add(cellColField, 1,5);
        // cell row
        final Label cellRow = new Label("Cell Row");
        final TextField cellRowField  = new TextField("0");
        // cell row label
        GridPane.setHalignment(cellRow, HPos.RIGHT);
        add(cellRow, 0,6);
        // cell row field
        GridPane.setHalignment(cellRowField, HPos.LEFT);
        add(cellRowField , 1,6);
        // Horizontal allignment
        Label hAlign = new Label ("Horizontal Allignment");
        final ChoiceBox hAlignField = new ChoiceBox(FXCollections.observableArrayList("CENTER", "LEFT", "RIGHT"));
        hAlignField.getSelectionModel().select("LEFT");
        //cell row label
        GridPane.setHalignment(hAlign, HPos.RIGHT);
        add(hAlign , 0,7);
        // cell row field
        GridPane.setHalignment(hAlignField, HPos.LEFT);
        add(hAlignField, 1,7);
        
         // Vertical allignment
        Label vAlign = new Label ("Vertical Allignment");
        final ChoiceBox vAlignField = new ChoiceBox(FXCollections.observableArrayList("BASELINE","BOTTOM","CENTER","TOP"));
        vAlignField.getSelectionModel().select("TOP");
        //cell row label
        GridPane.setHalignment(vAlign, HPos.RIGHT);
        add(vAlign , 0,8);
        // cell row field
        GridPane.setHalignment(vAlignField, HPos.LEFT);
        add(vAlignField, 1,8);
        
        Label cellApply = new Label("Cell Constraint");
        final Button apply = new Button("Apply");
        apply.setOnAction( new EventHandler <ActionEvent>(){
            @Override
        public void handle (ActionEvent evt){
          for(Node child : tgp.getChildren()){
           int tgtCol = 0;
           int tgtRow = 0;
           try{
               tgtCol = Integer.parseInt(cellCol.getText());
               tgtRow = Integer.parseInt(cellRow.getText());
           }catch (Exception e){   
           }
           System.out.println("Child = " + child.getClass().getSimpleName());
           int col = GridPane.getColumnIndex(child);
           int row  = GridPane.getRowIndex(child);
           if (col == tgtCol && row == tgtRow){
               GridPane.setHalignment(child, HPos.valueOf(hAlignField.getSelectionModel().getSelectedItem().toString()));
            GridPane.setValignment(child, VPos.valueOf(vAlignField.getSelectionModel().getSelectedItem().toString()));
           }
          }  
        }
    });
        // cell row label
        GridPane.setHalignment(cellApply, HPos.RIGHT);
        add(cellApply, 0,9);
        GridPane.setHalignment(apply, HPos.LEFT);
        add(apply, 1,9);    }
    
}
