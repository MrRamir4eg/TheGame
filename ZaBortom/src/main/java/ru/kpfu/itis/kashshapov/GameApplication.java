package ru.kpfu.itis.kashshapov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.kpfu.itis.kashshapov.context.ApplicationContext;

import java.io.IOException;

public class GameApplication extends Application {

    private static GameApplication gameApplication;

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        gameApplication = this;
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationContext.getGameResourceURL("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("За Бортом!");
        stage.getIcons().add(new Image(ApplicationContext.getGameResource("images/icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static GameApplication getGameApplication() {
        return gameApplication;
    }

    public Stage getStage() {
        return this.stage;
    }

    public static void main(String[] args) {
        launch();
    }
}
