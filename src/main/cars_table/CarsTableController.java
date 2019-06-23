package main.cars_table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class CarsTableController {

    private ObservableList<Car> listCars;

    private VehicleInformation vi = new VehicleInformation();

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

        listCars = FXCollections.observableArrayList(vi.readObj());

        tableCars.setItems(listCars);

        //searchNewRecord();

        Alert warn = new Alert(Alert.AlertType.WARNING);
        warn.setContentText("Внимание! Если не измененить значения новой записи во всех колонках, то она не будет сохранена");
        warn.showAndWait();

        addRecord.setOnAction(event -> {
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

    private void searchNewRecord(){
        for(Car car1 : listCars){
            for(Object object: vi.readObj()){
                if(car1.equals(object)){
                    System.out.println("record: true");
                } else
                    System.out.println("false");
            }
        }
    }

    private boolean checkRecord(Car car){
               if(!car.getBrand().equals("null") & !car.getNumber().equals("null") & !car.getPass().equals("null") & !car.getDate().equals("0000.00.00")){
                   return true;
               }
               return false;
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

            // switching on a listExistingRecords of columns
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
}
