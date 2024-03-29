package com.equipoocho.hangman.controller;

import com.equipoocho.hangman.model.Words;
import com.equipoocho.hangman.view.alert.Alertbox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//import com.equipoocho.hangman.model.Player;
//import com.equipoocho.hangman.view.Alert.AlertBox;
import com.equipoocho.hangman.view.GameStage;
import com.equipoocho.hangman.view.WelcomeStage;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private Button welcomeInstructions;

    @FXML
    private Button welcomePlay;

    @FXML
    private ToggleButton welcomeShowWord;

    @FXML
    private PasswordField keywordHidden;

    @FXML
    private TextField keywordVisible;

    @FXML
    void onClickButtonInstructions(ActionEvent event) {

    }

    @FXML
    void onClickButtonPlay(ActionEvent event) {
        String keyword= keywordHidden.getText();
        Words words= new Words(keyword);
        if(!words.checkWord(keyword)){
        new Alertbox().showError("Hangman","Error en palabra clave","Por favor introduzca una palabra sin caracteres especiales y que contenga entre 4 y 12 letras.");
        }

    }

    @FXML
    void onMousePressed(MouseEvent event) {
        keywordVisible.setText(keywordHidden.getText());
        keywordHidden.setVisible(false);
        keywordVisible.setVisible(true);

    }
    @FXML
    void onMouseReleased(MouseEvent event){
        keywordHidden.setText(keywordVisible.getText());
        keywordHidden.setVisible(true);
        keywordVisible.setVisible(false);
    }


}