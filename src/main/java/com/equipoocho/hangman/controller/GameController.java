package com.equipoocho.hangman.controller;
import com.equipoocho.hangman.model.HangmanImages;
import com.equipoocho.hangman.model.Words;
import com.equipoocho.hangman.view.alert.Alertbox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Random;

import static javafx.scene.input.KeyEvent.KEY_TYPED;

public class GameController {
    private String letter;
    private StringBuilder wordProgress=new StringBuilder(); //Variable para almacenar el avance de la palabra descubierta.
    private boolean listen=true; //Variable booleana para desactivar la escucha de teclado.
    private Words words; //Objeto clase Words para traer la palabra insertada en el WelcomeView.
    private int misses=0,hits=0,hintsGiven=0;// Contadores para el número de errores y aciertos.
    private HangmanImages hangmanImages;//Objeto clase HangmanImages para actualizar las fotos del stickman ahorcado.
    private String gameWord;//Variable tipo string para almacenar la palabra que se obtiene del objeto words.
    private ImageView hangmanView;//ImageView para insertar imágenes del stickman.
    private GridPane letterGrid;// Grilla dinámica en función de la longitud de la palabra.

    //A continuación se inserta el sampler skeleton de Scene Builder:
    @FXML
    private AnchorPane hangmanAnchorPane;
    @FXML
    private Button gameHint;
    @FXML
    private BorderPane mainGameScene;

    @FXML
    private AnchorPane gameWordPane;

    //Botón que nos permitirá revelar SOLO una letra de la palabra clave por pista. Así la letra se repita, no revela más de una letra.
    @FXML
    void onClickButtonHint(ActionEvent event) {
        Random rand = new Random();
        while (true) {
            int randNumber = rand.nextInt(gameWord.length());
            TextField textField = (TextField) letterGrid.getChildren().get(randNumber);
            if(textField.getText().contains(" ")){
                letter=String.valueOf(gameWord.charAt(randNumber));
                textField.setText(letter);
                this.wordProgress.insert(randNumber, letter.toCharArray(), 0, 1);
                this.hintsGiven++;
                break;
            }
        }
        if(this.hintsGiven>2){gameHint.setDisable(true);mainGameScene.requestFocus();}//Condicional que permite usar hasta 3 pistas.
        if(wordProgress.toString().replace(" ","").contains(this.gameWord)){// Condicional para terminar el juego, por si la palabra es completada con las pistas.
            gameHint.setDisable(true);
            listen=false;
            mainGameScene.requestFocus();
            System.out.println("se acabó");
        }
        System.out.println(wordProgress);
    }
    //Escucha de teclado para adivinar la palabra secreta.
    @FXML
    void keyListener(KeyEvent event) {
        wordProgress=new StringBuilder();
        if(!listen){return;}
        String letter = event.getCharacter().toUpperCase();
        hits=0;
            for (int j = 0; j < gameWord.length();j++) {
                if (letter.charAt(0) == gameWord.charAt(j)) {
                    TextField textField = (TextField) letterGrid.getChildren().get(j);
                    textField.setText(letter);
                    hits++;

                }
                this.wordProgress.append(((TextField)(letterGrid.getChildren().get(j))).getCharacters());

            }
            if(hits==0){
                if (hangmanView !=null){
                    hangmanAnchorPane.getChildren().remove(hangmanView);
                }
                hangmanImages= new HangmanImages();

                hangmanView=hangmanImages.getHangmanimage();
                hangmanView.setX(64);
                hangmanView.setY(12);

                hangmanAnchorPane.getChildren().addAll(hangmanView);
                misses++;
                System.out.println(misses);
            }
        if (misses>=6) {
            listen=false;
            gameHint.setDisable(true);
            mainGameScene.requestFocus();
            new Alertbox().showInfo("Hangman","Has perdido","El juego ha terminado, la palabra secreta era: "+gameWord);
        }
        if (wordProgress.toString().replace(" ","").contains(this.gameWord)) {
            listen=false;
            gameHint.setDisable(true);
            mainGameScene.requestFocus();
            new Alertbox().showInfo("Hangman","Has ganado","Felicitaciones, has adivinado la palabra secreta.");
        }
        System.out.println(this.wordProgress);

    }

    public void showGrid() {
        this.gameWord = words.getKeyWord();
        wordProgress.append(" ".repeat(gameWord.length()));

        letterGrid = new GridPane();
        Font font = Font.font("System", FontWeight.BOLD, 18);
        // Recorrer cada letra de la palabra y agregarla a una celda del GridPane
        for (int i = 0; i < gameWord.length(); i++) {
            char letter = ' ';
            TextField letterField = new TextField(String.valueOf(letter));
            letterField.setEditable(false); // Para que el usuario no pueda editar las letras
            letterField.setPrefWidth(45); // Ajusta el ancho de cada casilla según sea necesario
            letterField.setFont(font);
            letterField.setMouseTransparent(true);
            letterField.setAlignment(Pos.CENTER);
            letterGrid.add(letterField, i, 0); // Agregar la letra a la fila 0 en la columna i
        }

        Platform.runLater(() -> {
            double anchorPaneWidth = gameWordPane.getWidth();
            double anchorPaneHeight = gameWordPane.getHeight();

            // Calcular la posición para centrar el GridPane horizontalmente
            double centerX = (anchorPaneWidth - letterGrid.getWidth()) / 2;

            // Calcular la posición para centrar el GridPane verticalmente
            double centerY = (anchorPaneHeight - letterGrid.getHeight()) / 2;

            // Establecer las coordenadas para centrar el GridPane dentro del AnchorPane
            letterGrid.setLayoutX(centerX);
            letterGrid.setLayoutY(centerY);
        });
        gameWordPane.getChildren().add(letterGrid);
    }

    public void setWords(Words words) {
        this.words = words;
    }
}

