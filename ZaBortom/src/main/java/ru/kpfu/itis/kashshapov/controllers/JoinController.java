package ru.kpfu.itis.kashshapov.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.kpfu.itis.kashshapov.client.Client;
import ru.kpfu.itis.kashshapov.server.GameServer;

public class JoinController {

    @FXML
    private TextField username;

    @FXML
    private TextField host;

    @FXML
    private TextField port;

    @FXML
    private Button joinButton;

    @FXML
    private Label errorLabel;

    private static JoinController controller;

    public void initialize() {
        controller = this;
        joinButton.setOnAction(actionEvent -> {
            new Client(username.getText(), host.getText(), Integer.valueOf(port.getText()));
        });
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public static JoinController getController() {
        return controller;
    }
}
