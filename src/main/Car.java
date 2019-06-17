package main;

import java.io.Serializable;

public class Car implements Serializable {
    private String brand;
    private String number;
    private String pass;
    private String date;
    private String time;

    public Car(String brand, String number, String pass, String date, String time) {
        this.brand = brand;
        this.number = number;
        this.pass = pass;
        this.date = date;
        this.time = time;
    }

    public String getBrand() {
        return brand;
    }

    public String getNumber() {
        return number;
    }

    public String getPass() {
        return pass;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
