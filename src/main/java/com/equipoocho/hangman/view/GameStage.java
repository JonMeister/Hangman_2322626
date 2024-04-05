package com.equipoocho.hangman.view;

import com.equipoocho.hangman.controller.GameController;
import com.equipoocho.hangman.model.Words;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {
    public GameStage(Words words) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/equipoocho/hangman/game-view.fxml"));
        Parent root = loader.load();


        // Obtener el controlador después de cargar el FXML
        GameController gameController = loader.getController();
        gameController.setWords(words);// Para pasar la palabra del WelcomeController al GameController.
        gameController.showGrid(); // Llamar al método que inserta una grilla en función de la longitud de la palabra clave.

        setTitle("Hangman");
        Scene scene = new Scene(root);
        getIcons().add(new Image(
                String.valueOf(getClass().getResource("/com/equipoocho/hangman/images/hangman.png"))));
        setScene(scene);
        setResizable(false);
        show();
    }

    private static class GameStageHolder{
        private static GameStage INSTANCE;
    }
    public static void deleteInstance(){
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE=null;
    }
    public static GameStage getInstance(Words words) throws IOException{
        return GameStageHolder.INSTANCE= new GameStage(words);
    }


}
