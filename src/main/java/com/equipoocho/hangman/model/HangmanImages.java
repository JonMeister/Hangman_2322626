package com.equipoocho.hangman.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HangmanImages {

    private ImageView hangmanimage;
    private static int contador=1;

    public HangmanImages (){
        String PATH="/com/equipoocho/hangman/images/";
        contador++;
        this.hangmanimage=
                new ImageView(
                        new Image(String.valueOf(getClass().getResource(PATH+"hangman"+contador+".png")))
                );
        this.hangmanimage.setFitHeight(228);
        this.hangmanimage.setFitWidth(228);
    }

    public ImageView getHangmanimage() {
        return hangmanimage;
    }
}
