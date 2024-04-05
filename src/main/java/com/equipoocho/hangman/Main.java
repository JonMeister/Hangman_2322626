/*
Archivo: Main.java
Autores:
- Jonathan Aristizabal (202322626)

Correos:
- jonathan.aristizabal@correounivalle.edu.co

Fecha creación: 2024-03-28
Fecha última modificación: 2024-04-05
*/

package com.equipoocho.hangman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.equipoocho.hangman.view.WelcomeStage;

import java.io.IOException;

public class Main extends Application  {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        WelcomeStage.getInstance();
    }
}