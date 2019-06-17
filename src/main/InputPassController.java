package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class InputPassController {

    private String truePass = "";

    @FXML
    private Button inputButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void initialize(){
        inputButton.setOnAction(event -> {
            if(passwordField.getText().equals(truePass)){
                Stage stage =(Stage) inputButton.getScene().getWindow();
                stage.close();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/main/cars_table/cars_table.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();

                stage = new Stage();
                stage.initModality(Modality.NONE);
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Неверный пароль!");
                error.showAndWait();
            }
        });
    }
}
