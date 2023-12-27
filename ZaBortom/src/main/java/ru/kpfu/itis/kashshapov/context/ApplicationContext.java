package ru.kpfu.itis.kashshapov.context;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import ru.kpfu.itis.kashshapov.GameApplication;

import java.io.IOException;
import java.net.URL;

public class ApplicationContext {

    private static FXMLLoader joinScene;
    private static FXMLLoader createScene;
    private static FXMLLoader gameScene;

    static {
        try {
            joinScene = new FXMLLoader(ApplicationContext.getGameResourceURL("join.fxml")).load();
            createScene = new FXMLLoader(ApplicationContext.getGameResourceURL("create.fxml")).load();
            gameScene = new FXMLLoader(ApplicationContext.getGameResourceURL("game-screen.fxml")).load();
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

    public static FXMLLoader getJoinScene() {
        return joinScene;
    }

    public static FXMLLoader getCreateScene() {
        return createScene;
    }

    public static FXMLLoader getGameScene() {
        return gameScene;
    }
}
