package ru.kpfu.itis.kashshapov.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ru.kpfu.itis.kashshapov.cards.equipment.Water;
import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.client.Client;
import ru.kpfu.itis.kashshapov.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameScreenController {
    @FXML
    private ImageView shketUlt;
    @FXML
    private ImageView swap;
    @FXML
    private ImageView stealCards;
    @FXML
    private ImageView navigate;
    @FXML
    private Label enemy;
    @FXML
    private Label friend;
    @FXML
    private Label me;
    @FXML
    private ImageView afk;
    @FXML
    private AnchorPane charContainer1;
    @FXML
    private AnchorPane charContainer2;
    @FXML
    private AnchorPane charContainer3;
    @FXML
    private AnchorPane charContainer4;
    @FXML
    private AnchorPane charContainer5;
    @FXML
    private AnchorPane charContainer6;
    private List<AnchorPane> charContainers = new ArrayList<>();

    @FXML
    private ImageView charView1;
    @FXML
    private ImageView charView2;
    @FXML
    private ImageView charView3;
    @FXML
    private ImageView charView4;
    @FXML
    private ImageView charView5;
    @FXML
    private ImageView charView6;
    private List<ImageView> charViews = new ArrayList<>();

    @FXML
    private ImageView charFight1;
    @FXML
    private ImageView charFight2;
    @FXML
    private ImageView charFight3;
    @FXML
    private ImageView charFight4;
    @FXML
    private ImageView charFight5;
    @FXML
    private ImageView charFight6;
    private List<ImageView> charFights = new ArrayList<>();

    @FXML
    private ImageView charNav1;
    @FXML
    private ImageView charNav2;
    @FXML
    private ImageView charNav3;
    @FXML
    private ImageView charNav4;
    @FXML
    private ImageView charNav5;
    @FXML
    private ImageView charNav6;
    private List<ImageView> charNavs = new ArrayList<>();

    @FXML
    private Label charHp1;
    @FXML
    private Label charHp2;
    @FXML
    private Label charHp3;
    @FXML
    private Label charHp4;
    @FXML
    private Label charHp5;
    @FXML
    private Label charHp6;
    private List<Label> charHps = new ArrayList<>();

    @FXML
    private Label charCardCount1;
    @FXML
    private Label charCardCount2;
    @FXML
    private Label charCardCount3;
    @FXML
    private Label charCardCount4;
    @FXML
    private Label charCardCount5;
    @FXML
    private Label charCardCount6;
    private List<Label> charCardCounts = new ArrayList<>();

    @FXML
    private MenuButton active1;
    @FXML
    private MenuButton active2;
    @FXML
    private MenuButton active3;
    @FXML
    private MenuButton active4;
    @FXML
    private MenuButton active5;
    @FXML
    private MenuButton active6;

    private List<MenuButton> actives = new ArrayList<>();

    private List<GameCharacter> characters = new ArrayList<>();

    @FXML
    private Button check;

    public void initialize() {
        charContainers.add(charContainer1);
        charContainers.add(charContainer2);
        charContainers.add(charContainer3);
        charContainers.add(charContainer4);
        charContainers.add(charContainer5);
        charContainers.add(charContainer6);

        charViews.add(charView1);
        charViews.add(charView2);
        charViews.add(charView3);
        charViews.add(charView4);
        charViews.add(charView5);
        charViews.add(charView6);

        charFights.add(charFight1);
        charFights.add(charFight2);
        charFights.add(charFight3);
        charFights.add(charFight4);
        charFights.add(charFight5);
        charFights.add(charFight6);

        for (ImageView i: charFights) {
            i.setImage(new Image(ApplicationContext.getGameResource("images/status/fighting.png")));
        }

        charNavs.add(charNav1);
        charNavs.add(charNav2);
        charNavs.add(charNav3);
        charNavs.add(charNav4);
        charNavs.add(charNav5);
        charNavs.add(charNav6);

        for (ImageView i : charNavs) {
            i.setImage(new Image(ApplicationContext.getGameResource("images/status/navigating.png")));
        }

        charHps.add(charHp1);
        charHps.add(charHp2);
        charHps.add(charHp3);
        charHps.add(charHp4);
        charHps.add(charHp5);
        charHps.add(charHp6);

        charCardCounts.add(charCardCount1);
        charCardCounts.add(charCardCount2);
        charCardCounts.add(charCardCount3);
        charCardCounts.add(charCardCount4);
        charCardCounts.add(charCardCount5);
        charCardCounts.add(charCardCount6);

        actives.add(active1);
        actives.add(active2);
        actives.add(active3);
        actives.add(active4);
        actives.add(active5);
        actives.add(active6);

        for (MenuButton i : actives) {
            i.setPopupSide(Side.RIGHT);
        }

        characters.add(GameCharacter.MILADY);
        characters.add(GameCharacter.SNOB);
        characters.add(GameCharacter.CAPTAIN);
        characters.add(GameCharacter.BOZMAN);
        characters.add(GameCharacter.CHERPAK);
        characters.add(GameCharacter.SHKET);

        check.setOnAction(actionEvent -> {
            characters.get(0).getActiveDeck().add(new Water());
            characters.get(0).setHp(characters.get(0).getHp() + 1);
            updateView(characters);
        });

        shketUlt.setImage(new Image(ApplicationContext.getGameResource("images/status/shket_ult.png")));
        swap.setImage(new Image(ApplicationContext.getGameResource("images/status/swap.jpg")));
        stealCards.setImage(new Image(ApplicationContext.getGameResource("images/status/card.jpg")));
        afk.setImage(new Image(ApplicationContext.getGameResource("images/status/afk.jpg")));
        navigate.setImage(new Image(ApplicationContext.getGameResource("images/status/navigating.png")));

        me.setText(Client.getTheClient().getCharacter().getName());
        enemy.setText(Client.getTheClient().getCharacter().getEnemy().getName());
        friend.setText(Client.getTheClient().getCharacter().getFriend().getName());

        updateView(characters);
    }

    private void updateView(List<GameCharacter> characters) {
        for (int i = 0; i < characters.size(); i++) {
            GameCharacter c = characters.get(i);
            charViews.get(i).setImage(new Image(ApplicationContext.getGameResource(c.getImageLocation())));
            charNavs.get(i).setVisible(c.isWasNavigating());
            charFights.get(i).setVisible(c.isWasFighting());
            charHps.get(i).setText("ХП: " + c.getHp());
            charCardCounts.get(i).setText("Карты: " + c.getDeck().size());
            actives.get(i).getItems().clear();
            actives.get(i).getItems().addAll(c.getActiveDeck().stream()
                    .map(card -> {
                        ImageView view = new ImageView(ApplicationContext.getGameResource(card.getImageLocation()));
                        view.setFitHeight(180);
                        view.setFitWidth(120);
                        return new MenuItem("", view);
                    }).collect(Collectors.toSet()));
        }
    }
}
