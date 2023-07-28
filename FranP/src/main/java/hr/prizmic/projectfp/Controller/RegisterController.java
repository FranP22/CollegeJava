package hr.prizmic.projectfp.Controller;

import hr.prizmic.projectfp.File.DatFile;
import hr.prizmic.projectfp.File.LoginInfo;
import hr.prizmic.projectfp.Controller.Scene.LoadScene;
import hr.prizmic.projectfp.Exceptions.FileReadException;
import hr.prizmic.projectfp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class RegisterController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;

    @FXML
    public void methodRegister() throws FileReadException,ClassNotFoundException, NoSuchAlgorithmException {
        String a = username.getText();
        String b = password1.getText();
        String c = password2.getText();

        if(a.isEmpty() || b.isEmpty() || c.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Field empty");
            alert.setHeaderText("One or more of the following fields is empty");
            String error = "";
            if(a.isEmpty()){
                error += "Username is empty\n";
            }
            if(b.isEmpty() || c.isEmpty()){
                error += "Password is empty\n";
            }
            alert.setContentText(error);
            alert.showAndWait();
            return;
        }

        if(!b.equals(c)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password doesn't match");
            alert.showAndWait();
            return;
        }

        try{
            Map<String, String> list;
            list = DatFile.readFile("dat/login.ser");

            if(list.containsKey(a)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("That username already exists");
                alert.showAndWait();
                return;
            }

            b= Main.encrypt(b);
            DatFile.writeFile("dat/login.ser", new LoginInfo(a, b));

            InfoController.uname=a;
            InfoController.pword=b;
            LoadScene.showInfo();
        }
        catch(FileReadException e){
            String msg = "Couldn't load file";
            throw new FileReadException(msg, e);
        }
    }
}
