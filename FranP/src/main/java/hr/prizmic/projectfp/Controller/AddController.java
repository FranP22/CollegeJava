package hr.prizmic.projectfp.Controller;

import hr.prizmic.projectfp.Class.Item;
import hr.prizmic.projectfp.Class.Phone.*;
import hr.prizmic.projectfp.Connection.Database;
import hr.prizmic.projectfp.Exceptions.DatabaseException;
import hr.prizmic.projectfp.Exceptions.FileReadException;
import hr.prizmic.projectfp.File.Log;
import hr.prizmic.projectfp.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class AddController {
    @FXML
    private ChoiceBox<String> type = new ChoiceBox();
    @FXML
    private TextField manuF;
    @FXML
    private TextField modelF;
    @FXML
    private TextField priceF;
    @FXML
    private TextField batteryF;
    @FXML
    private TextField OSF;
    @FXML
    private TextField CPUF;
    @FXML
    private TextField GPUF;
    @FXML
    private TextField inF;
    @FXML
    private TextField exF;
    @FXML
    private TextField RAMF;

    @FXML
    public void initialize(){
        String[] options = {"Phone","Tablet"};
        type.setItems(FXCollections.observableArrayList(options));
    }

    private boolean addEmpty(String t, String manu, String model, String price, String battery,
                             String OS, String CPU, String GPU, String internal, String external, String RAM){
        if(t.isEmpty() || manu.isEmpty() || model.isEmpty() || price.isEmpty() || battery.isEmpty() ||
                OS.isEmpty() || CPU.isEmpty() || GPU.isEmpty() || internal.isEmpty() || external.isEmpty() || RAM.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Field empty");
            alert.setHeaderText("One or more fields are empty");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    @FXML
    public void methodAdd() throws DatabaseException, FileReadException {
        try{
            String manu = manuF.getText();
            String model = modelF.getText();
            String OS = OSF.getText();
            String CPU = CPUF.getText();
            String GPU = GPUF.getText();
            String external = exF.getText();

            if(addEmpty(String.valueOf(type.getValue()),manu,model,priceF.getText(),batteryF.getText(),OS,CPU,GPU,inF.getText(),external,RAMF.getText())){
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Do you want to add item?");

            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent() || result.get() != ButtonType.OK){
                return;
            }

            Integer RAM = Integer.valueOf(RAMF.getText());
            Integer internal = Integer.valueOf(inF.getText());
            Integer battery = Integer.valueOf(batteryF.getText());
            BigDecimal price = BigDecimal.valueOf(Long.parseLong(priceF.getText()));

            Storage s = new Storage(internal,external,RAM);
            Characteristics c = new Characteristics(battery,OS,CPU,GPU,s);
            MobileDevice m;
            if(type.getValue()=="Phone"){
                m=new Phone(manu,model,price,c);
            }else{
                m=new Tablet(manu,model,price,c);
            }

            Item item = new Item(null,m,m.getType(), Main.activeUser, LocalDateTime.now());

            Log.itemAdded();
            Database.addItem(item);
        }
        catch(NullPointerException e){
            Log.nullpointerException();
        }
    }
}
