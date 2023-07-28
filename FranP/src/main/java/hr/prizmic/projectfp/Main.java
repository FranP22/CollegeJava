package hr.prizmic.projectfp;

import hr.prizmic.projectfp.Class.Item;
import hr.prizmic.projectfp.Class.User.UserClass;
import hr.prizmic.projectfp.Connection.FilterClass;
import hr.prizmic.projectfp.Controller.Scene.LoadScene;
import hr.prizmic.projectfp.Exceptions.FileReadException;
import hr.prizmic.projectfp.File.Log;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static UserClass activeUser = null;
    public static FilterClass filter = new FilterClass.FilterBuilder(
            new String(),new String(),new String(),new String(),
            new String(),new String(),new String(),new String(),
            new String(),new String(),new String()).build();
    public static List<Item> cart = new ArrayList<>();

    public static Stage mainStage;
    private static Stage popup;

    @Override
    public void start(Stage stage) {
        try{
            mainStage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Resources/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
            stage.setTitle("Main");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e){
            Log.IOException();
        }
    }

    public static void popup(String path, String title){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
            Scene scene = new Scene(fxmlLoader.load(), 400, 700);
            popup = new Stage();
            popup.setResizable(false);
            popup.setTitle(title);
            popup.setScene(scene);
            LoadScene.open=true;
            popup.showAndWait();
            LoadScene.open=false;
        }
        catch (IOException e){
            Log.IOException();
        }
    }

    public static void setMainPage(BorderPane root, String title) {
        Scene scene = new Scene(root,1280,800);
        mainStage.setTitle(title);
        mainStage.setScene(scene);
        mainStage.show();
    }
    public static void setPopup(BorderPane root, String title) {
        Scene scene = new Scene(root,400,700);
        popup.setTitle(title);
        popup.setScene(scene);
        popup.show();
    }

    //MD5 Hashing
    public static String encrypt(String password){
        String encrypted;

        try{
            MessageDigest m = MessageDigest.getInstance("MD5");

            m.update(password.getBytes());
            byte[] bytes = m.digest();

            StringBuilder s = new StringBuilder();
            for(int i=0;i<bytes.length;i++){
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            encrypted=s.toString();

            return encrypted;
        }
        catch(NoSuchAlgorithmException e){
            Log.noAlgorithmExeption();
            return null;
        }
    }


    public static void main(String[] args) {

        launch();

        //MainController.executor.shutdown();
    }
}