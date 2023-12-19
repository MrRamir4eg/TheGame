package ru.kpfu.itis.kashshapov.characters;

import ru.kpfu.itis.kashshapov.cards.equipment.EquipmentCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public enum GameCharacter implements Serializable {
    MILADY("Milady", 4, 4, 8, 0, false, false, false, false, new ArrayList<>(), new ArrayList<>(), 0, "images/characters/milady.png"),
    SNOB("Snob", 5, 5, 7, 0, false, false, false, false, new ArrayList<>(), new ArrayList<>(), 0, "images/characters/snob.png"),
    CAPTAIN("Captain", 7, 7, 5, 0, false, false, false, false, new ArrayList<>(), new ArrayList<>(), 0, "images/characters/captain.png"),
    BOZMAN("Bozman", 8, 8, 4, 0, false, false, false, false, new ArrayList<>(), new ArrayList<>(), 0, "images/characters/bozman.png"),
    CHERPAK("Cherpak", 6, 6, 6, 0, false, false, false, false, new ArrayList<>(), new ArrayList<>(), 0, "images/characters/cherpak.png"),
    SHKET("Shket", 3, 3, 9, 0, false, false, false, false, new ArrayList<>(), new ArrayList<>(), 0, "images/characters/shket.png");

    String name;
    int hp;
    final int power;
    final int pointsForSurvival;
    int thirst;
    boolean isUnconscious;
    boolean isDead;
    boolean wasFighting;
    boolean wasNavigating;
    List<EquipmentCard> deck;
    List<EquipmentCard> activeDeck;
    transient GameCharacter friend;
    transient GameCharacter enemy;
    int score;
    private final String imageLocation;

    GameCharacter(String name, int hp, int power, int pointsForSurvival,
                  int thirst, boolean isUnconscious, boolean isDead, boolean wasFighting,
                  boolean wasNavigating, List<EquipmentCard> deck, List<EquipmentCard> activeDeck,
                  int score, String imageLocation) {
        this.name = name;
        this.hp = hp;
        this.power = power;
        this.pointsForSurvival = pointsForSurvival;
        this.thirst = thirst;
        this.isUnconscious = isUnconscious;
        this.isDead = isDead;
        this.wasFighting = wasFighting;
        this.wasNavigating = wasNavigating;
        this.deck = deck;
        this.activeDeck = activeDeck;
        this.score = score;
        this.imageLocation = imageLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPower() {
        return power;
    }

    public boolean isUnconscious() {
        return isUnconscious;
    }

    public void setUnconscious(boolean unconscious) {
        isUnconscious = unconscious;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public List<EquipmentCard> getDeck() {
        return deck;
    }

    public void setDeck(List<EquipmentCard> deck) {
        this.deck = deck;
    }

    public List<EquipmentCard> getActiveDeck() {
        return activeDeck;
    }

    public void setActiveDeck(List<EquipmentCard> activeDeck) {
        this.activeDeck = activeDeck;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public GameCharacter getFriend() {
        return friend;
    }

    public void setFriend(GameCharacter friend) {
        this.friend = friend;
    }

    public GameCharacter getEnemy() {
        return enemy;
    }

    public void setEnemy(GameCharacter enemy) {
        this.enemy = enemy;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPointsForSurvival() {
        return pointsForSurvival;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public boolean isWasFighting() {
        return wasFighting;
    }

    public void setWasFighting(boolean wasFighting) {
        this.wasFighting = wasFighting;
    }

    public boolean isWasNavigating() {
        return wasNavigating;
    }

    public void setWasNavigating(boolean wasNavigating) {
        this.wasNavigating = wasNavigating;
    }
}
