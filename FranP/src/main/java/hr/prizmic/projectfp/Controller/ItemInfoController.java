package hr.prizmic.projectfp.Controller;

import hr.prizmic.projectfp.Class.Item;
import hr.prizmic.projectfp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ItemInfoController {
    public static Item item;

    @FXML
    private Label model;
    @FXML
    private Label manu;
    @FXML
    private Label type;
    @FXML
    private Label price;
    @FXML
    private Label battery;
    @FXML
    private Label os;
    @FXML
    private Label cpu;
    @FXML
    private Label gpu;
    @FXML
    private Label is;
    @FXML
    private Label ex;
    @FXML
    private Label ram;
    @FXML
    private Label time;
    @FXML
    private Label ab;
    @FXML
    private Button button;
    @FXML
    private Button button2;

    @FXML
    public void initialize(){
        model.setText(item.getDevice().getModel());
        manu.setText(item.getDevice().getManufacturer());
        type.setText(item.getType().toString());
        price.setText(item.getDevice().getPrice().toString() + "€");
        battery.setText(item.getDevice().getCharacteristics().getBattery().toString() + " mAh");
        os.setText(item.getDevice().getCharacteristics().getOS());
        cpu.setText(item.getDevice().getCharacteristics().getCPU());
        gpu.setText(item.getDevice().getCharacteristics().getGPU());
        is.setText(item.getDevice().getCharacteristics().getStorage().getStorageInternal().toString() + " GB");
        ex.setText(item.getDevice().getCharacteristics().getStorage().getStorageExternal());
        ram.setText(item.getDevice().getCharacteristics().getStorage().getRAM().toString() + " GB");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
        String date = format.format(item.getTimeAdded());
        time.setText(date);
        ab.setText(item.getUser().getUsername());

        button.setVisible(true);
        button2.setVisible(false);

        for(int i=0;i< Main.cart.size();i++){
            if(item==Main.cart.get(i)){
                button.setVisible(false);
                button2.setVisible(true);
            }
        }
        if(item.getUser().getID()==Main.activeUser.getID()){
            button.setVisible(false);
            button2.setVisible(false);
        }
    }

    @FXML
    public void addToCart(){
        Main.cart.add(item);
        button.setVisible(false);
        button2.setVisible(true);
    }
    @FXML
    public void removeFromCart(){
        List<Item> list = new ArrayList<>();
        for(int i=0;i<Main.cart.size();i++){
            if(Main.cart.get(i).getID()!=item.getID()){
                list.add(Main.cart.get(i));
            }
        }
        Main.cart=list;
        button.setVisible(true);
        button2.setVisible(false);
    }
}
