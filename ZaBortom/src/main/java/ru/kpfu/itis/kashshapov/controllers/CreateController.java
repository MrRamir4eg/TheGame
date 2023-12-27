package ru.kpfu.itis.kashshapov.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import ru.kpfu.itis.kashshapov.GameApplication;
import ru.kpfu.itis.kashshapov.client.Client;
import ru.kpfu.itis.kashshapov.context.ApplicationContext;
import ru.kpfu.itis.kashshapov.enums.Phase;
import ru.kpfu.itis.kashshapov.server.GameServer;

import java.io.IOException;

public class CreateController {
    @FXML
    private TextField username;

    @FXML
    private TextField host;

    @FXML
    private TextField port;

    @FXML
    private Button createButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Button joinGameButton;

    private static CreateController controller;

    public void initialize() {
        controller = this;
        createButton.setOnAction(actionEvent -> {
            GameServer server = new GameServer(Integer.valueOf(port.getText()));
            server.start();
            new Client(username.getText(), host.getText(), Integer.valueOf(port.getText()));
        });

        joinGameButton.setOnAction(actionEvent -> {
            try {
                GameApplication.getGameApplication()
                        .getStage()
                        .setScene(new Scene(ApplicationContext.getGameScene().load()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            GameServer.getTheServer().setCurrentPhase(Phase.INIT);
        });
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public static CreateController getController() {
        return controller;
    }

    public Button getJoinGameButton() {
        return joinGameButton;
    }
}
