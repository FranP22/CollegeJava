package hr.prizmic.projectfp.Controller;

import hr.prizmic.projectfp.Connection.FilterClass;
import hr.prizmic.projectfp.Exceptions.DatabaseException;
import hr.prizmic.projectfp.Exceptions.FileReadException;
import hr.prizmic.projectfp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class FilterController {
    @FXML
    private TextField model;
    @FXML
    private TextField manufacturer;
    @FXML
    private TextField price;
    @FXML
    private TextField battery;
    @FXML
    private TextField os;
    @FXML
    private TextField cpu;
    @FXML
    private TextField gpu;
    @FXML
    private TextField in;
    @FXML
    private TextField ex;
    @FXML
    private TextField ram;
    @FXML
    private TextField user;

    @FXML
    public void methodFilter() throws FileReadException, DatabaseException {
        FilterClass c = new FilterClass.FilterBuilder(
                model.getText(),manufacturer.getText(), price.getText(),
                battery.getText(),os.getText(),cpu.getText(),
                gpu.getText(),in.getText(),ex.getText(),
                ram.getText(),user.getText()).build();

        Main.filter = c;
    }
}
