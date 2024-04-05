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
import com.equipoocho.hangman.controller.GameController;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Words words;
    //Botón para lanzar AlertBox de información del juego.
    @FXML
    void onClickButtonInstructions(ActionEvent event) {
        new Alertbox().showInfo("Hangman","Instrucciones:","Ingrese una palabra para empezar a jugar al ahorcado, puedes\nequivocarte hasta 6 veces antes de que el stickman sea\nahorcado.");
    }
    //Botón para revisar que la palabra secreta sea aceptable y lanza AlertBox si no lo es.
    //Si la palabra es aceptable, lanza el GameStage
    @FXML
    void onClickButtonPlay(ActionEvent event) throws IOException {
        String keyword = keywordHidden.getText();
         this.words = new Words(keyword);
        if (!words.checkWord(keyword)) {
            new Alertbox().showError("Hangman", "Error en palabra clave", "Por favor introduzca una palabra sin caracteres especiales y que contenga entre 4 y 15 letras.");
        } else {
            GameStage.getInstance(words);
            WelcomeStage.deleteInstance();
        }
    }
    //Evento para desenmascarar la palabra mientras se tenga presionado el botón
    @FXML
    void onMousePressed(MouseEvent event) {
        keywordVisible.setText(keywordHidden.getText());
        keywordHidden.setVisible(false);
        keywordVisible.setVisible(true);
        keywordVisible.requestFocus();
        keywordVisible.positionCaret(keywordVisible.getText().length());
    }
    //Evento para volver a enmascarar la palabra una vez se suelta el botón.
    @FXML
    void onMouseReleased(MouseEvent event){
        keywordHidden.setText(keywordVisible.getText());
        keywordHidden.setVisible(true);
        keywordVisible.setVisible(false);
        keywordHidden.requestFocus();
        keywordHidden.positionCaret(keywordVisible.getText().length());
    }

    @FXML
    void onKeyPressedPlay(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            String keyword = keywordHidden.getText();
            this.words = new Words(keyword);
            if (!words.checkWord(keyword)) {
                new Alertbox().showError("Hangman", "Error en palabra clave", "Por favor introduzca una palabra sin caracteres especiales y que contenga entre 4 y 15 letras.");
            }
            else {
                GameStage.getInstance(words);
                WelcomeStage.deleteInstance();
                }
            }
        }
    }
