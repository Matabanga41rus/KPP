package main.table_cars;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.Car;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class CarsTableController {

    private ObservableList<Car> listCars;

    @FXML
    private TableView<Car> tableCars;

    @FXML
    private TableColumn<Car, String> brand;

    @FXML
    private TableColumn<Car, String> number;

    @FXML
    private TableColumn<Car, String> pass;

    @FXML
    private TableColumn<Car, String> date;

    @FXML
    private TableColumn<Car, String> time;

    @FXML
    private Button addRecord;

    @FXML
    private Button deleteRecord;

    @FXML
    public void initialize(){
        tableCars.setEditable(true);

        initCellTableColAsTextField(brand, "brand");
        initCellTableColAsTextField(number, "number");
        initCellTableColAsTextField(pass, "pass");
        initCellTableColAsTextField(date, "date");
        initCellTableColAsTextField(time, "time");

        updateCell(brand);
        updateCell(number);
        updateCell(pass);
        updateCell(date);
        updateCell(time);

        listCars = getListData();

        tableCars.setItems(listCars);

        addRecord.setOnAction(event -> {
            Date d = new Date();

            listCars.add(new Car("null", "null", "null", "00.00.0000", "00:00"));
        });

        deleteRecord.setOnAction(event -> {
            int row = tableCars.getSelectionModel().getSelectedIndex();
            if(row >= 0){
                Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
                conf.setContentText("Вы уверены, что хотите удалить выделенную запись?");

                Optional<ButtonType> option = conf.showAndWait();

                if (option.get() == ButtonType.OK) {
                    tableCars.getItems().remove(row);
                }
            }
        });

    }

    private void initCellTableColAsTextField (TableColumn<Car, String> tableColumn ,String nameFieldUser){
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(nameFieldUser));

        tableColumn.setCellFactory(TextFieldTableCell.<Car> forTableColumn());
    }

    private void updateCell(TableColumn<Car, String> tableColumn){
        tableColumn.setOnEditCommit((TableColumn.CellEditEvent<Car, String> event) -> {
            TablePosition<Car, String> pos = event.getTablePosition();

            String newCell = event.getNewValue();

            int row = pos.getRow();

            // switching on a list of columns
            switch (tableColumn.getText()){
                case "Car brand":{
                    Car car = event.getTableView().getItems().get(row);
                    car.setBrand(newCell);
                }break;

                case "Car number":{
                    Car car = event.getTableView().getItems().get(row);
                    car.setNumber(newCell);
                }break;

                case "Pass number":{
                    Car car = event.getTableView().getItems().get(row);
                    car.setPass(newCell);
                }break;

                case "Date":{
                    Car car = event.getTableView().getItems().get(row);
                    car.setDate(newCell);
                }break;

                case "Time":{
                    Car car = event.getTableView().getItems().get(row);
                    car.setTime(newCell);
                }break;
            }
        });
    }

    private ObservableList<Car> getListData() {
        Car user1 = new Car("toyota hice", "d546ad", "ghj4534hgv", "dgfsd", "sf");
        Car user2 = new Car("mitsubushi lancer", "b453xa", "4g43k5gf34fg","dgfsd", "sf");
        Car user3 = new Car("lexus lx570", "n967sg", "g527fdh47sdg7","dgfsd", "sf");

        ArrayList<Car> sd = new ArrayList<>();
        sd.add(user1);
        sd.add(user2);
        sd.add(user3);

        return FXCollections.observableArrayList(sd);
    }

    public void setListData(ObservableList<Car> listData) {
        this.listCars = listData;
    }

}
