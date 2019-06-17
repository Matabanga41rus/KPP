package main.table_cars;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.Car;

public class CarsTableController {

    private ObservableList<Car> listCars;

    @FXML
    private TableView<Car> carsTable;

    @FXML
    private TableColumn<Car, String> number;

    @FXML
    private TableColumn<Car, String> pass;

    @FXML
    private TableColumn<Car, String> brand;

    @FXML
    private Button addRecord;

    @FXML
    private Button deleteRecord;

    @FXML
    public void initialize(){

    }

}
