package main.cars_table;

import main.Car;

import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
        Car car1 = new Car("null", "null", "null", "00.00.0000", "00:00");
        Car car2 = new Car("null", "null", "null", "00.00.0000", "00:00");

        System.out.println(car1.equals(car1));

    }
}
