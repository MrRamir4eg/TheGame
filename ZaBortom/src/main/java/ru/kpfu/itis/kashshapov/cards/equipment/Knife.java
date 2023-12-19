package ru.kpfu.itis.kashshapov.cards.equipment;

import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.server.GameServer;

public class Knife extends EquipmentCard {

    public Knife() {
        super(0, 3, true, false, false, "images/equipment/knife.png");
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
