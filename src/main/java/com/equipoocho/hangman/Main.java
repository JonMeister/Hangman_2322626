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

import com.equipoocho.hangman.view.WelcomeStage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

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