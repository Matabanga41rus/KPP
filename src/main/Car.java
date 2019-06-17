package main;

public class Car {
    private String brand;
    private String number;
    private String pass;

    public Car(String brand, String number, String pass) {
        this.brand = brand;
        this.number = number;
        this.pass = pass;
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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
