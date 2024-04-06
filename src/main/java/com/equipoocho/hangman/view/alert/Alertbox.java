package com.equipoocho.hangman.view.alert;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Alertbox implements IAlertBox{
    @Override
    public void showError(String title, String header, String content){
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/com/equipoocho/hangman/images/hangman.png")))); // To add an icon
        alert.showAndWait();
    }

    @Override
    public void showInfo(String title, String header, String content) {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/com/equipoocho/hangman/images/hangman.png")))); // To add an icon
        alert.showAndWait();

    }

   /* private void setIcon(Alert alert) {
        Image image = new Image(String.valueOf(getClass().getResource("/com/equipoocho/hangman/images/hangman.png")));
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);

    }*/
}
