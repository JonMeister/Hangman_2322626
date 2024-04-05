package com.equipoocho.hangman.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {
    public WelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/equipoocho/hangman/welcome-view.fxml"));
        Parent root = loader.load();
        setTitle("Hangman");
        Scene scene = new Scene(root);
        getIcons().add(new Image(
                String.valueOf(getClass().getResource("/com/equipoocho/hangman/images/hangman.png"))));
        setScene(scene);
        setResizable(false);
        show();
    }

    private static class WelcomeStageHolder{
        private static WelcomeStage INSTANCE;
    }
    public static void deleteInstance(){
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE=null;
    }
    public static WelcomeStage getInstance() throws IOException{
        return WelcomeStageHolder.INSTANCE= new WelcomeStage();
    }
}
