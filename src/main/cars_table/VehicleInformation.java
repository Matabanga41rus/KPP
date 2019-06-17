package main.cars_table;

import main.Car;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleInformation {
    private final String fileName = "cars.txt";

    private FileWriter writer;
    private FileReader reader;

    private ArrayList<Car> listExistingRecords = new ArrayList<>();

    public VehicleInformation(){

    }

    public void selectInfo(){
        try {
            reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);

            while(scanner.hasNextLine()){
                String[] s = scanner.nextLine().split("\\s");
                listExistingRecords.add(new Car(s[0], s[1], s[2], s[3], s[4]));
            }

            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateFile(Car car){
        try {
            writer = new FileWriter(fileName);

            String strCarInfo = car.getBrand() + " " + car.getNumber() + " " + car.getPass() + " " + car.getDate() + " " + car.getTime();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
