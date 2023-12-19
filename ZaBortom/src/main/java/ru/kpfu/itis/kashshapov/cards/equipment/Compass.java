package ru.kpfu.itis.kashshapov.cards.equipment;

import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.server.GameServer;

public class Compass extends EquipmentCard {

    public Compass() {
        super(0, 0, false, false, true, "images/equipment/compass.png");
    }

    @Override
    public boolean useInTurn(GameServer serverInfo, GameCharacter character) {
        return true;
    }

    @Override
    public boolean consume(GameCharacter character) {
        return false;
    }
}
