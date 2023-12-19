package ru.kpfu.itis.kashshapov.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import ru.kpfu.itis.kashshapov.GameApplication;
import ru.kpfu.itis.kashshapov.context.ApplicationContext;

public class GameController {

    @FXML
    private Button createLobbyButton;

    @FXML
    private Button joinLobbyButton;

    @FXML
    private Hyperlink rulesHyperlink;

    public void initialize() {
        rulesHyperlink.setOnAction(actionEvent -> GameApplication.getGameApplication()
                .getHostServices()
                .showDocument("https://www.mosigra.ru/download/%D0%9F%D1%80%D0%B0%D0%B2%D0%B8%D0%BB%D0%B0/za_bortom_2izd_310820.pdf"));

        createLobbyButton.setOnAction(actionEvent -> GameApplication
                .getGameApplication()
                .getStage()
                .setScene(ApplicationContext.getCreateScene()));

        joinLobbyButton.setOnAction(actionEvent -> GameApplication
                .getGameApplication()
                .getStage()
                .setScene(ApplicationContext.getJoinScene()));

    }

}
