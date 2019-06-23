package main.cars_table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Car;

import java.io.*;
import java.util.ArrayList;


public class VehicleInformation {
    private final String fileName = "cars.txt";
    private final String numFile = "num_cars.txt";
    private File file = new File(numFile);


    public void writeObj(ObservableList<Car> listCars){
        try {
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for(Car car: listCars)
                oos.writeObject(car);

            write(listCars.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList readObj(){
        try {
            FileInputStream fis = new FileInputStream(new File(fileName));
            ObjectInputStream ois = new ObjectInputStream(fis);

            ObservableList list = FXCollections.observableArrayList();
            for(int i = 0 ; i < read(); i++)
                list.add((Car) ois.readObject());

            return list;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void write(int text) {
        //Определяем файл
        File file = new File(numFile);

        try {
            //проверяем, что если файл не существует то создаем его
            if(!file.exists()){
                file.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                //Записываем текст у файл
                out.print(text);
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int read() throws FileNotFoundException {
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();

        exists(numFile);

        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        //Возвращаем полученный текст с файла
        return Integer.parseInt(sb.substring(0,1));
    }

    private void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }

}
