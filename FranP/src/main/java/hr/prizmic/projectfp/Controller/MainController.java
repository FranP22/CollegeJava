package hr.prizmic.projectfp.Controller;

import hr.prizmic.projectfp.Class.Item;
import hr.prizmic.projectfp.Connection.Database;
import hr.prizmic.projectfp.Controller.Scene.LoadScene;
import hr.prizmic.projectfp.Exceptions.DatabaseException;
import hr.prizmic.projectfp.Exceptions.FileReadException;
import hr.prizmic.projectfp.File.Log;
import hr.prizmic.projectfp.Main;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainController {
    private ScheduledExecutorService executor;
    @FXML
    private Pane m2;
    @FXML
    private Button m1;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private TableView<Item> table = new TableView<>();
    @FXML
    private TableColumn<Item, String> priceTableColumn;
    @FXML
    private TableColumn<Item, String> typeTableColumn;
    @FXML
    private TableColumn<Item, String> modelTableColumn;
    @FXML
    private TableColumn<Item, String> manufacturerTableColumn;
    @FXML
    private TableColumn<Item, String> batteryTableColumn;
    @FXML
    private TableColumn<Item, String> storageTableColumn;
    @FXML
    private TableColumn<Item, String> ramTableColumn;
    @FXML
    private TableColumn<Item, String> osTableColumn;
    @FXML
    private TableColumn<Item, String> addedbyTableColumn;
    @FXML
    private TableColumn<Item, String> dateTableColumn;
    List<Item> items = new ArrayList<>();

    public void switchVisibility(){
        if(m1.isVisible()){
            m1.setVisible(false);
            m2.setVisible(true);
        }else{
            m1.setVisible(true);
            m2.setVisible(false);
        }
    }
    private void stopThread(WindowEvent event){
        executor.shutdown();
        Log.threadEnded();
    }

    @FXML
    public void initialize() throws DatabaseException, FileReadException {
        firstName.setText(Main.activeUser.getFirstName());
        lastName.setText(Main.activeUser.getLastName());

        items=Database.sortItem(Main.filter);

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
        typeTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDevice().getType().toString());
        });
        modelTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDevice().getModel());
        });
        manufacturerTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDevice().getManufacturer());
        });
        batteryTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDevice().getCharacteristics().getBattery().toString()+"mAh");
        });
        storageTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDevice().getCharacteristics().getStorage().getStorageInternal().toString() + " GB");
        });
        ramTableColumn.setCellValueFactory(cellData -> {
            String str = cellData.getValue().getDevice().getCharacteristics().getStorage().getRAM().toString() + " GB";
            return new SimpleStringProperty(str);
        });
        osTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDevice().getCharacteristics().getOS());
        });
        addedbyTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getUser().getUsername());
        });
        dateTableColumn.setCellValueFactory(cellData -> {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
            String date = format.format(cellData.getValue().getTimeAdded());
            return new SimpleStringProperty(date);
        });

        table.setItems(FXCollections.observableList(items));

        Runnable r = new Runnable() {
            //Log.threadStarted();
            @Override
            public void run() {
                try{
                    tableRefresh();
                }
                catch(FileReadException e1){
                    Log.fileReadException();
                }
                catch(DatabaseException e2){
                    Log.dataBaseException();
                }
            }
        };
        Log.threadStarted();

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(r,0,2,TimeUnit.SECONDS);

        Main.mainStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,this::stopThread);
    }

    @FXML
    public void showMenu(){
        switchVisibility();
    }

    @FXML
    public void hideMenu(){
        switchVisibility();
    }

    @FXML
    public void methodLogout(){
        Main.activeUser = null;
        executor.shutdown();
        Log.threadEnded();
        Log.logout();
        LoadScene.showLogin();
    }
    @FXML
    public void showCart(){
        LoadScene.showCart();
        executor.shutdown();
        Log.threadEnded();
    }
    @FXML
    public void showAdd() throws IOException{
        LoadScene.showAdd();
    }
    @FXML
    public void showRemove() throws IOException {
        LoadScene.showRemove();
    }
    @FXML
    public void showFilter() throws IOException {
        LoadScene.showFilter();
    }

    public void tableRefresh() throws FileReadException,DatabaseException{
        table.setItems(FXCollections.observableList(Database.sortItem(Main.filter)));
    }
}