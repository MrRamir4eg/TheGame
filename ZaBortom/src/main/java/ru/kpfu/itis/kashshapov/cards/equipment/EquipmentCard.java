package ru.kpfu.itis.kashshapov.cards.equipment;

import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.server.GameServer;

import java.io.Serializable;

public abstract class EquipmentCard implements Serializable {
    private int points;
    private int hitPoints;
    private boolean isWeapon;
    private boolean isConsumable;
    private boolean canBeActivated;
    private String imageLocation;

    public EquipmentCard(int points, int hitPoints, boolean isWeapon, boolean isConsumable, boolean canBeActivated, String imageLocation) {
        this.points = points;
        this.hitPoints = hitPoints;
        this.isWeapon = isWeapon;
        this.isConsumable = isConsumable;
        this.canBeActivated = canBeActivated;
        this.imageLocation = imageLocation;
    }

    public int useAsWeapon() {
        return hitPoints;
    }

    public abstract boolean useInTurn(GameServer serverInfo, GameCharacter character);

    public abstract boolean consume(GameCharacter character);

    public int getPoints() {
        return points;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public boolean isWeapon() {
        return isWeapon;
    }

    public boolean isConsumable() {
        return isConsumable;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public boolean isCanBeActivated() {
        return canBeActivated;
    }
}
