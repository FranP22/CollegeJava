package hr.prizmic.projectfp.Controller;

import hr.prizmic.projectfp.Class.User.UserClass;
import hr.prizmic.projectfp.Connection.Database;
import hr.prizmic.projectfp.Controller.Scene.LoadScene;
import hr.prizmic.projectfp.Exceptions.DatabaseException;
import hr.prizmic.projectfp.Exceptions.FileReadException;
import hr.prizmic.projectfp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class InfoController {
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private DatePicker date;
    public static String uname;
    public static String pword;

    @FXML
    public void methodRegister() throws DatabaseException, FileReadException {
        String f = fname.getText();
        String l = lname.getText();
        LocalDate d = date.getValue();

        if(f.isEmpty() || l.isEmpty() || Optional.ofNullable(d).isEmpty() == true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Field empty");
            alert.setHeaderText("One or more of the following fields is empty");
            String error = "";
            if(f.isEmpty()){
                error += "First name is empty\n";
            }
            if(l.isEmpty()){
                error += "Last name is empty\n";
            }
            if(Optional.ofNullable(d).isEmpty() == true){
                error += "Date is empty\n";
            }
            alert.setContentText(error);
            alert.showAndWait();
            return;
        }

        /*List<UserClass> users = Database.getAllUsers();
        long MaxID=0;
        for(int i=0;i<users.size();i++){
            if(users.get(i).getID()>MaxID){
                MaxID=users.get(i).getID();
            }
        }
        MaxID++;*/

        UserClass user = new UserClass(null,f,l,uname,pword,d);

        Database.addUser(user);

        List<UserClass> users = Database.getAll(UserClass.class);
        for(int i=0;i<users.size();i++){
            if(uname==users.get(i).getUsername()){
                user=new UserClass(users.get(i).getID(),f,l,uname,pword,d);
            }
        }

        Main.activeUser=user;
        LoadScene.showMain();
    }
}
