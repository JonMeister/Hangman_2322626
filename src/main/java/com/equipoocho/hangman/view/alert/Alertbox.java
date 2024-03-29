package com.equipoocho.hangman.view.alert;
import javafx.scene.control.Alert;
public class Alertbox implements IAlertBox{
    @Override
    public void showError(String title, String header, String content){
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
