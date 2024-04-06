package com.equipoocho.hangman.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HangmanImages {

    private ImageView hangmanimage;
    private static int contador=2;
    //Función para configuar la imagen del stickman y que suma al contador cada que se llama.
    public HangmanImages (){
        String PATH="/com/equipoocho/hangman/images/";
        this.hangmanimage=
                new ImageView(
                        new Image(String.valueOf(getClass().getResource(PATH+"hangman"+contador+".png")))
                );
        this.hangmanimage.setFitHeight(228);
        this.hangmanimage.setFitWidth(228);
    }
    //Función que devuelve la imagen.
    public ImageView getHangmanimage() {
        contador++;
        return hangmanimage;
    }
    public void resetCounter(){contador=2;}
}
