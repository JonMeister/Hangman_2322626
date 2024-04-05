package com.equipoocho.hangman.model;

public class Words {
    private String keyWord;
    public Words(String keyWord){
        this.keyWord=keyWord;
    }
    //Función para obtener el string keyWord del objeto Words
    public String getKeyWord(){return keyWord;}
    public boolean checkWord(String keyWord){
        String regex = "^[a-zA-Z]{4,15}$"; // Expresión regular para verificar que no tenga caracteres especiales y tenga entre 4 y 12 letras.
        if (keyWord.matches(regex)){this.keyWord=keyWord.toUpperCase(); return true;}
        else{this.keyWord=""; return false;}
    }
}
