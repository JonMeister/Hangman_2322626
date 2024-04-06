package com.equipoocho.hangman.controller;

import com.equipoocho.hangman.model.HangmanImages;
import com.equipoocho.hangman.model.Words;
import com.equipoocho.hangman.view.GameStage;
import com.equipoocho.hangman.view.WelcomeStage;
import com.equipoocho.hangman.view.alert.Alertbox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.util.Random;

public class GameController {

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
    private Button verifyButton;
    @FXML
    private AnchorPane gameWordPane;
    @FXML
    private Label hintsLabel;
    @FXML
    private Label missesLabel;
    @FXML
    private TextField letterTextfield;

    //Botón que nos permitirá revelar SOLO una letra de la palabra clave por pista. Así la letra se repita, no revela más de una letra.
    @FXML
    void onClickButtonHint(ActionEvent event) {
        Random rand = new Random();
        while (true) {
            int randNumber = rand.nextInt(gameWord.length());
            TextField textField = (TextField) letterGrid.getChildren().get(randNumber);
            if(textField.getText().contains(" ")){
                String letter;
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
            verifyButton.setDisable(true);
            mainGameScene.requestFocus();
            listen=false;
        }
        hintsLabel.setText("Pistas: "+hintsGiven+"/3");//Contador de pistas.
    }
    //Botón para verificar que se ingrese una letra del alfabeto y que verifica si la letra pertenece a la palabra..
    @FXML
    void onClickButtonVerifyLetter(ActionEvent event) {
    if(!listen){return;}

    String letter=letterTextfield.getText().toUpperCase();
    // Expresión regular para verificar que no tenga caracteres especiales y sea solo un carácter.
    String regex = "^[a-zA-Z]$";
    // Si se ingresa un carácter válido se aplica la lógica del juego.
    if (letter.matches(regex)){
        wordProgress=new StringBuilder();
        hits=0;
        // Recorre la palabra clave para ver si hay aciertos.
        for (int j = 0; j < gameWord.length();j++) {
            if (letter.charAt(0) == gameWord.charAt(j)) {
                TextField textField = (TextField) letterGrid.getChildren().get(j);
                textField.setText(letter);
                hits++;

            }
            this.wordProgress.append(((TextField)(letterGrid.getChildren().get(j))).getCharacters());

        }
        // Si no hay aciertos se va dibujando el stickman.
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
            //Contador de errores
            missesLabel.setText("Errores: "+misses+"/6");
        }

        //Condicional que detiene el juego cuando se alcanzan los 6 errores y lanza AlertBox.
        if (misses>=6) {
            listen=false;
            verifyButton.setDisable(true);
            gameHint.setDisable(true);
            mainGameScene.requestFocus();
            new Alertbox().showInfo("Hangman","Has perdido","El juego ha terminado, la palabra secreta era: "+gameWord);
        }
        //Condicional que detiene el juego si se ha adivinado la palabra clave y lanza AlertBox.
        if (wordProgress.toString().replace(" ","").contains(this.gameWord)) {
            listen=false;
            verifyButton.setDisable(true);
            gameHint.setDisable(true);
            mainGameScene.requestFocus();
            new Alertbox().showInfo("Hangman","Has ganado","Felicitaciones, has adivinado la palabra secreta.");
        }


    }
    // Si no se ingresa un carácter válido se lanza un AlertBox
    else{new Alertbox().showError("Hangman","Error en carácter ingresado","Por favor ingrese UNA letra del abecedario, sin tildes");}
    letterTextfield.setText("");
    letterTextfield.requestFocus();
    letterTextfield.positionCaret(letterTextfield.getText().length());
    }
    @FXML
    void onKeyPressedVerify(KeyEvent event) {
        if(!listen){return;}
        if(event.getCode()== KeyCode.ENTER){
            String letter=letterTextfield.getText().toUpperCase();
            // Expresión regular para verificar que no tenga caracteres especiales y sea solo un carácter.
            String regex = "^[a-zA-Z]$";
            // Si se ingresa un carácter válido se aplica la lógica del juego.
            if (letter.matches(regex)){
                wordProgress=new StringBuilder();
                hits=0;
                // Recorre la palabra clave para ver si hay aciertos.
                for (int j = 0; j < gameWord.length();j++) {
                    if (letter.charAt(0) == gameWord.charAt(j)) {
                        TextField textField = (TextField) letterGrid.getChildren().get(j);
                        textField.setText(letter);
                        hits++;

                    }
                    this.wordProgress.append(((TextField)(letterGrid.getChildren().get(j))).getCharacters());

                }
                // Si no hay aciertos se va dibujando el stickman.
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
                }
                //Condicional que detiene el juego cuando se alcanzan los 6 errores y lanza AlertBox.
                if (misses>=6) {
                    listen=false;
                    verifyButton.setDisable(true);
                    gameHint.setDisable(true);
                    mainGameScene.requestFocus();
                    new Alertbox().showInfo("Hangman","Has perdido","El juego ha terminado, la palabra secreta era: "+gameWord);
                }
                //Condicional que detiene el juego si se ha adivinado la palabra clave y lanza AlertBox.
                if (wordProgress.toString().replace(" ","").contains(this.gameWord)) {
                    listen=false;
                    verifyButton.setDisable(true);
                    gameHint.setDisable(true);
                    mainGameScene.requestFocus();
                    new Alertbox().showInfo("Hangman","Has ganado","Felicitaciones, has adivinado la palabra secreta.");
                }
                //Contador de errores
                missesLabel.setText("Errores: "+misses+"/6");

            }
            // Si no se ingresa un carácter válido se lanza un AlertBox
            else{new Alertbox().showError("Hangman","Error en carácter ingresado","Por favor ingrese UNA letra del abecedario, sin tildes");}
            letterTextfield.setText("");
        }

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
            letterField.setMouseTransparent(true);//Para que el usuario no pueda seleccionar los textfield en la grilla.
            letterField.setAlignment(Pos.CENTER);
            letterGrid.add(letterField, i, 0); // Agregar la letra a la fila 0 en la columna i
        }
        //Para asegurarme de que la grilla se centre después de ser creada la ventana.
        Platform.runLater(() -> {
            double anchorPaneWidth = gameWordPane.getWidth();
            double anchorPaneHeight = gameWordPane.getHeight();

            // Calcular la posición para centrar el GridPane horizontalmente.
            double centerX = (anchorPaneWidth - letterGrid.getWidth()) / 2;

            // Calcular la posición para centrar el GridPane verticalmente.
            double centerY = (anchorPaneHeight - letterGrid.getHeight()) / 2;

            // Establecer las coordenadas para centrar el GridPane dentro del AnchorPane.
            letterGrid.setLayoutX(centerX);
            letterGrid.setLayoutY(centerY);
        });
        gameWordPane.getChildren().add(letterGrid);
    }
    //Función para traer el objeto words del WelcomeView al GameView
    public void setWords(Words words) {
        this.words = words;
    }
    //Botón para borrar la instancia de GameStage y volver a una instancia del WelcomeStage
    @FXML
    void onClickButtonRestart(ActionEvent event) throws IOException {
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }
}

