package ru.kpfu.itis.kashshapov.cards.equipment;

import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.server.GameServer;

public class Jewels extends EquipmentCard {

    public Jewels() {
        super(3, 0, false, false, false, "images/equipment/jewels.png");
    }

    @Override
    public boolean useInTurn(GameServer serverInfo, GameCharacter character) {
        return false;
    }

    @Override
    public boolean consume(GameCharacter character) {
        return false;
    }
}
