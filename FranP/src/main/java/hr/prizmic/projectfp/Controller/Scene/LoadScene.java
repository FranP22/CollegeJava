package hr.prizmic.projectfp.Controller.Scene;

import hr.prizmic.projectfp.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LoadScene {
    public static boolean open=false;
    private static void Load(String file, String title){
        BorderPane root;
        try {
            root = (BorderPane) FXMLLoader.load(Main.class.getResource(file));
            Main.setMainPage(root, title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void Popup(String file, String title){
        BorderPane root;
        try {
            root = (BorderPane) FXMLLoader.load(Main.class.getResource(file));
            Main.setPopup(root, title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showMain() {
        Load("Resources/main.fxml", "Main");
    }
    public static void showInfo() {
        Load("Resources/infoinput.fxml", "Info");
    }
    public static void showLogin() {
        Load("Resources/login.fxml", "Login");
    }
    public static void showRegister() {
        Load("Resources/register.fxml", "Register");
    }
    public static void showCart() {
        Load("Resources/cart.fxml", "Cart");
    }
    public static void showItem() throws IOException{
        if(!open){
            Main.popup("Resources/iteminfo.fxml", "Item");
        }else{
            Popup("Resources/iteminfo.fxml", "Item");
        }
    }
    public static void showAdd() throws IOException{
        if(!open){
            Main.popup("Resources/add.fxml", "Add Item");
        }else{
            Popup("Resources/add.fxml", "Add Item");
        }
    }
    public static void showRemove() throws IOException{
        if(!open){
            Main.popup("Resources/remove.fxml", "Remove Item");
        }else{
            Popup("Resources/remove.fxml", "Remove Item");
        }
    }
    public static void showFilter() throws IOException{
        if(!open){
            Main.popup("Resources/filter.fxml", "Filter");
        }else{
            Popup("Resources/filter.fxml", "Filter");
        }
    }
}
