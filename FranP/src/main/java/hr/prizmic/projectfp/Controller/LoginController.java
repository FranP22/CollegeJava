package hr.prizmic.projectfp.Controller;

import hr.prizmic.projectfp.Class.User.UserClass;
import hr.prizmic.projectfp.File.DatFile;
import hr.prizmic.projectfp.Connection.Database;
import hr.prizmic.projectfp.Controller.Scene.LoadScene;
import hr.prizmic.projectfp.Exceptions.DatabaseException;
import hr.prizmic.projectfp.Exceptions.FileReadException;
import hr.prizmic.projectfp.File.Log;
import hr.prizmic.projectfp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private Map<String, String> list = new HashMap<>();

    @FXML
    public void initialize(){
        try{
            list = DatFile.readFile("dat/login.ser");
        }
        catch(FileReadException e){
            Log.fileReadException();
        }
        catch(ClassNotFoundException e){
            Log.classNFException();
        }
    }

    private void loginSuccess(String a, String b) throws DatabaseException, FileReadException {
        List<UserClass> users = Database.getAll(UserClass.class);
        for(int i=0;i<users.size();i++){
            if(a.equals(users.get(i).getUsername()) && b.equals(users.get(i).getPassword())){
                Main.activeUser = users.get(i);
                LoadScene.showMain();
                return;
            }
        }
        InfoController.uname=a;
        InfoController.pword=b;
        LoadScene.showInfo();
    }

    @FXML
    public void methodLogin() throws DatabaseException, FileReadException {
        String a = username.getText();
        String b = password.getText();

        if(a.isEmpty() || b.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Field empty");
            alert.setHeaderText("One or more of the following fields is empty");
            String error = "";
            if(a.isEmpty()){
                error += "Username is empty\n";
            }
            if(b.isEmpty()){
                error += "Password is empty\n";
            }
            alert.setContentText(error);
            alert.showAndWait();
            return;
        }

        b=Main.encrypt(b);

        if(list.containsKey(a)){
            String p = list.get(a);
            if(p.equals(b)){
                Log.login(true);
                loginSuccess(a, b);
                return;
            }
        }

        Log.login(false);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Failed to sign in");
        alert.setHeaderText("Username or password is incorrect");
        alert.showAndWait();
    }

    @FXML
    public void methodRegister(){
        LoadScene.showRegister();
    }
}