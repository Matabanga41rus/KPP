package main.cars_table;

import main.Car;

import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
        VehicleInformation vehicleInformation = new VehicleInformation();

        Car user1 = new Car("toyota hice", "d546ad", "ghj4534hgv", "12.06.19", "12:36");
        Car user2 = new Car("mitsubushi lancer", "b453xa", "4g43k5gf34fg","12.06.19", "14:10");
        Car user3 = new Car("lexus lx570", "n967sg", "g527fdh47sdg7","13.06.19", "15:08");

        ArrayList<Car> listExistingRecords = new ArrayList<>();

        listExistingRecords.add(user1);
        listExistingRecords.add(user2);
        listExistingRecords.add(user3);

        vehicleInformation.writeObj(listExistingRecords);


        listExistingRecords = new ArrayList<>(vehicleInformation.readObj());
        System.out.println(listExistingRecords.size());

    }
}
