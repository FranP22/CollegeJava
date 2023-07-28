module hr.prizmic.projectfp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;

    opens hr.prizmic.projectfp to javafx.fxml;
    exports hr.prizmic.projectfp;
    exports hr.prizmic.projectfp.Controller;
    opens hr.prizmic.projectfp.Controller to javafx.fxml;
    exports hr.prizmic.projectfp.Class;
    opens hr.prizmic.projectfp.Class to javafx.fxml;
    exports hr.prizmic.projectfp.Class.User;
    opens hr.prizmic.projectfp.Class.User to javafx.fxml;
    exports hr.prizmic.projectfp.Class.Phone;
    opens hr.prizmic.projectfp.Class.Phone to javafx.fxml;
    exports hr.prizmic.projectfp.File;
    opens hr.prizmic.projectfp.File to javafx.fxml;
}