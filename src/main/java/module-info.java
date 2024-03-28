module com.equipoocho.hangman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.equipoocho.hangman to javafx.fxml;
    opens com.equipoocho.hangman.controller to javafx.fxml;

    exports com.equipoocho.hangman;
}