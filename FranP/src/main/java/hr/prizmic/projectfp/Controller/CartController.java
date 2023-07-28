package hr.prizmic.projectfp.Controller;

import hr.prizmic.projectfp.Class.Item;
import hr.prizmic.projectfp.Connection.Database;
import hr.prizmic.projectfp.Controller.Scene.LoadScene;
import hr.prizmic.projectfp.Exceptions.DatabaseException;
import hr.prizmic.projectfp.Exceptions.FileReadException;
import hr.prizmic.projectfp.File.Log;
import hr.prizmic.projectfp.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartController {
    @FXML
    private TableView<Item> table = new TableView<>();
    @FXML
    private TableColumn<Item, String> priceTableColumn;
    @FXML
    private TableColumn<Item, String> modelTableColumn;
    @FXML
    private TableColumn<Item, String> manufacturerTableColumn;
    @FXML
    private Label price;

    /*public static void addToCart(Item item){
        Main.cart.add(item);
    }
    public void removeFromCart(long id){
        List<Item> list = new ArrayList<>();
        for(int i=0;i<Main.cart.size();i++){
            if(Main.cart.get(i).getID()!=id){
                list.add(Main.cart.get(i));
            }
        }
        Main.cart=list;
    }*/

    @FXML
    public void initialize(){
        table.setRowFactory(t -> {
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    Item item = row.getItem();
                    ItemInfoController.item=item;
                    try{
                        LoadScene.showItem();
                    }
                    catch(IOException e){
                        throw new UncheckedIOException("Cannot show item",e);
                    }
                }
            });
            return row;
        });

        priceTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDevice().getPrice().toString() + "€");
        });
        modelTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDevice().getModel());
        });
        manufacturerTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDevice().getManufacturer());
        });

        table.setItems(FXCollections.observableList(Main.cart));

        BigDecimal pr=BigDecimal.valueOf(0);

        for(int i=0;i<Main.cart.size();i++){
            pr = pr.add(Main.cart.get(i).getDevice().getPrice());
        }

        price.setText(pr.toString() + "€");
    }

    @FXML
    public void methodBuy() throws DatabaseException, FileReadException {
        long k=0;
        for(int i=0;i<Main.cart.size();i++){
            k += Main.cart.get(i).getDevice().getPrice().longValue();
            Database.removeItem(Main.cart.get(i).getID());
        }
        Main.cart.clear();
        Log.itemsBought(k);
        price.setText("0€");
        table.setItems(FXCollections.observableList(Main.cart));
    }
    @FXML
    public void showMain(){
        LoadScene.showMain();
    }
}
