package ssrscenter;

import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author jamesb 20/03/18 
 */
//Written for JDK 1.4.2 - 1.8.0_161

public class SSRScenter extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button calcBtn = new Button();
        TextField pageSize = new TextField();       //Defining basic elements 
        TextField elementSize = new TextField();        
        Label title = new Label("SSRS Centering");
        
        Label result = new Label();
        Label resultPointer = new Label("Result: ");    //Probably a bit misleading to call it a pointer...
        HBox resultBox = new HBox();
            resultBox.getChildren().addAll(resultPointer,result);   //HBox to align the result portion of GUI
            resultBox.setAlignment(Pos.CENTER);
        
        Label pageSizePrompt = new Label("Enter page size");
        Label elementSizePrompt = new Label("Enter element size");
        
        /* ----- Creating 2 drop down lists of units and formatting input layout ----- */
        
        ObservableList<String> options = FXCollections.observableArrayList("cm","inches"); 
        ComboBox unit = new ComboBox(options);
        unit.getSelectionModel().selectFirst();
        
        ObservableList<String> options2 = FXCollections.observableArrayList("cm","inches");
        ComboBox unit2 = new ComboBox(options2);
        unit2.getSelectionModel().selectFirst();
        
        HBox pageInAndUnit = new HBox();
            pageInAndUnit.getChildren().addAll(pageSize, unit);
            pageInAndUnit.setAlignment(Pos.CENTER);
            unit.setPrefWidth(85);
            pageSize.setPrefWidth(200);
            
        HBox elementInAndUnit = new HBox();
            elementInAndUnit.getChildren().addAll(elementSize, unit2);
            elementInAndUnit.setAlignment(Pos.CENTER);
            unit2.setPrefWidth(85);
            elementSize.setPrefWidth(200);            
       
        /* ----- Adding the label and the HBox of input and drop down together into 1 box ----- */    
        
        VBox pageSizeBox = new VBox();
            pageSizeBox.getChildren().addAll(pageSizePrompt,pageInAndUnit);
            pageSizeBox.setSpacing(5);
            pageSizeBox.setAlignment(Pos.BASELINE_LEFT);
        VBox elementSizeBox = new VBox();
            elementSizeBox.getChildren().addAll(elementSizePrompt,elementInAndUnit);
            elementSizeBox.setSpacing(5);
            elementSizeBox.setAlignment(Pos.BASELINE_LEFT);
            
        /* ----- BUTTON EVENT ----- */    
        
        calcBtn.setText("Calculate");
        calcBtn.setOnAction((ActionEvent e) -> {    
            double pageDouble=0;
            double elementDouble=0; //Initialising doubles
            boolean success = false;    //Set to fail initially 
            
            try{
                pageDouble = Double.parseDouble(pageSize.getText());        //Try-Catch to sanatize inputs
                elementDouble = Double.parseDouble(elementSize.getText());
                success = true;} //It worked!
            catch (NumberFormatException error) {  
                result.setText("Input must be a number."); //Display error message in the label
            }
            
            if(success){
                double calcResultcm = calc(pageDouble,elementDouble,unit,unit2); //CALC RESULT ALWAYS RETURNED AS CM
                double calcResultinch = calcResultcm / 2.54;
                DecimalFormat format = new DecimalFormat("#.000");  //3dp limiter
                result.setText(format.format(calcResultcm) + "cm  -  " + format.format(calcResultinch) + " inches "); //Formatting a good looking final answer
            }
        }); /* END OF BUTTON EVENT */
        
        /* ----- COMPONENT STYLING AND GUI STUFF ----- */
               
        VBox root = new VBox(); //The final VBox which will hold the entire scene
        root.setSpacing(15);    //the tasteful thickness of it
        root.setPadding(new Insets(0, 20, 10, 20)); //No top padding, 20px left right, 10px bottom
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title,pageSizeBox, elementSizeBox,calcBtn,resultBox);    //ADDING IT ALL TOGETHER
              
        Scene scene = new Scene(root, 300, 250);    //Finalizing GUI settings
        primaryStage.setTitle("SSRS Centering");    
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);   //Do not allow resize
    }

    public static void main(String[] args) { 
        launch(args);
    }
    
    public static double calc(double pageSize, double elementSize, ComboBox unit, ComboBox unit2){  //Return CM ONLY calculated value
        String unitType1 = (String) unit.getSelectionModel().getSelectedItem();
        String unitType2 = (String) unit2.getSelectionModel().getSelectedItem();    //Reading combo box to see selection choice
        double conversion = 2.54; //Conversion ratio between Inch to Cm
        double pos; //Creaeting variable to return once instead of inside each 'if'

        if(unitType1.equals(unitType2)){
            pos = (pageSize / 2) - (elementSize / 2);  
            if(unitType1.equals("inches")){ //If they are both inches, convert to cm
                pos=pos*conversion;
            }
            return pos;
        }
        else if(unitType1.equals("cm") && unitType2.equals("inches")){  //
            pos = (pageSize / 2) - ((elementSize*conversion) / 2);      //
            return pos;                                                 //  Cases for mixed units
        }                                                               //
        else if(unitType1.equals("inches") && unitType2.equals("cm")){  //
            pos = ((pageSize*conversion) / 2) - (elementSize / 2);     //
            return pos;
        }
    return 0;   //Fail safe, should never actually return 
    }
}