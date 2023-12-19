package ru.kpfu.itis.kashshapov.context;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import ru.kpfu.itis.kashshapov.GameApplication;

import java.io.IOException;
import java.net.URL;

public class ApplicationContext {

    private static Scene joinScene;
    private static Scene createScene;
    private static Scene gameScene;

    static {
        try {
            joinScene = new Scene(new FXMLLoader(ApplicationContext.getGameResourceURL("join.fxml")).load());
            createScene = new Scene(new FXMLLoader(ApplicationContext.getGameResourceURL("create.fxml")).load());
            gameScene = new Scene(new FXMLLoader(ApplicationContext.getGameResourceURL("game-screen.fxml")).load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getGameResource(String path) {
        return String.valueOf(GameApplication.class.getResource(path));
    }

    public static URL getGameResourceURL(String path) {
        return GameApplication.class.getResource(path);
    }

    public static Scene getJoinScene() {
        return joinScene;
    }

    public static Scene getCreateScene() {
        return createScene;
    }

    public static Scene getGameScene() {
        return gameScene;
    }
}
