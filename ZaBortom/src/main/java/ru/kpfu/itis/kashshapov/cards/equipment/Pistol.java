package ru.kpfu.itis.kashshapov.cards.equipment;

import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.server.GameServer;

public class Pistol extends EquipmentCard {

    public Pistol() {
        super(0, 8, true, false, true, "images/equipment/pistol.png");
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
