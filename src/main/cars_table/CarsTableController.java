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
    
    private String fileDataCars = "cars.txt";

    private ArrayList<Car> listExistingRecords = new ArrayList<>();

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

    // ВНИМАНИЕ!!! ГОВНОКОД!!!
    private void addNewFilledRecordsInFile(){
        try {
            FileWriter fw = new FileWriter(fileDataCars);

        for(Car car: listCars){
            if( !car.getBrand().equals("null")& !car.getNumber().equals("null")& !car.getPass().equals("null")& !car.getDate().equals("00.00.0000")& !car.getBrand().equals("00:00")){
                    String str = car.getBrand() + " " + car.getNumber() + " " + car.getPass() + " " +car.getDate() + " " +car.getTime() + "\n";
                    
                    fw.write(str);
            }
        }

        fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    // ВНИМАНИЕ!!! ГОВНОКОД!!!
    private ObservableList<Car> getListData() {
        try {
            FileReader fr = new FileReader(fileDataCars);
            Scanner scanner = new Scanner(fr);

            while(scanner.hasNextLine()){
                String[] s = scanner.nextLine().split("\\s");
                listExistingRecords.add(new Car(s[0], s[1], s[2], s[3], s[4]));
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Car user1 = new Car("toyota hice", "d546ad", "ghj4534hgv", "12.06.19", "12:36");
        Car user2 = new Car("mitsubushi lancer", "b453xa", "4g43k5gf34fg","12.06.19", "14:10");
        Car user3 = new Car("lexus lx570", "n967sg", "g527fdh47sdg7","13.06.19", "15:08");

        listExistingRecords.add(user1);
        listExistingRecords.add(user2);
        listExistingRecords.add(user3);

        return FXCollections.observableArrayList(listExistingRecords);
    }
}
