package com.equipoocho.hangman.view.alert;

public interface IAlertBox {
    void showError(String title, String header, String content);
    void showInfo(String title, String header, String content);


}
