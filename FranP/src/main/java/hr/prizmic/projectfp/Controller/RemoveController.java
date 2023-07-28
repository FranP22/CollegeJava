package hr.prizmic.projectfp.Controller;

import hr.prizmic.projectfp.Class.Item;
import hr.prizmic.projectfp.Class.User.UserClass;
import hr.prizmic.projectfp.Connection.Database;
import hr.prizmic.projectfp.Exceptions.DatabaseException;
import hr.prizmic.projectfp.Exceptions.FileReadException;
import hr.prizmic.projectfp.File.Log;
import hr.prizmic.projectfp.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveController {
    @FXML
    private TextField removeID;
    @FXML
    private TableView<Item> table = new TableView<>();
    @FXML
    private TableColumn<Item, String> IDTableColumn;
    @FXML
    private TableColumn<Item, String> modelTableColumn;
    @FXML
    private TableColumn<Item, String> manufacturerTableColumn;
    @FXML
    private TableColumn<Item, String> dateTableColumn;

    List<Item> items;

    private List<Item> getItems(List<Item> i){
        List<Item> it = new ArrayList<>();
        for(int k=0;k<i.size();k++){
            if(i.get(k).getUser().getID()==Main.activeUser.getID()){
                it.add(i.get(k));
            }
        }
        return it;
    }

    @FXML
    public void initialize() throws DatabaseException,FileReadException{
        List<Item> allItems = Database.getAllItems();
        //List<UserClass> allUsers = Database.getAllUsers();
        items = getItems(allItems);

        IDTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getID().toString());
        });
        modelTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDevice().getModel());
        });
        manufacturerTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDevice().getManufacturer());
        });
        dateTableColumn.setCellValueFactory(cellData -> {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
            String date = format.format(cellData.getValue().getTimeAdded());
            return new SimpleStringProperty(date);
        });

        table.setItems(FXCollections.observableList(items));
    }

    private void notValidUser(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid User");
        alert.setHeaderText("That article is not by you");
        alert.showAndWait();
    }
    private void notValidID(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid ID");
        alert.setHeaderText("The inserted ID is no valid");
        alert.showAndWait();
    }
    private boolean removeEmpty(){
        if(removeID.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Field empty");
            alert.setHeaderText("ID field is empty");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    @FXML
    public void methodRemove() throws FileReadException, DatabaseException {
        try{
            if(removeEmpty()){
                return;
            }

            long id = Long.parseLong(removeID.getText());
            List<Item> list = Database.getAll(Item.class);
            for(int i=0;i<list.size();i++){
                if(list.get(i).getID()==id){
                    if(list.get(i).getUser().getUsername() == Main.activeUser.getUsername()){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Do you want to delete that item?");

                        Optional<ButtonType> result = alert.showAndWait();
                        if(!result.isPresent() || result.get() != ButtonType.OK){
                            return;
                        }else{
                            Database.removeItem(id);
                            List<Item> allItems = Database.getAll(Item.class);
                            items=getItems(allItems);
                            table.setItems(FXCollections.observableList(items));

                            Log.itemRemoved(Long.parseLong(removeID.getText()));
                            return;
                        }
                    }else{
                        notValidUser();
                    }
                }
            }
            notValidID();
            return;
        }
        catch(NullPointerException e){
            Log.nullpointerException();
        }
    }
}
