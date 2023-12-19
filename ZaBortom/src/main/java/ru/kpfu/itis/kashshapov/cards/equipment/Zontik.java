package ru.kpfu.itis.kashshapov.cards.equipment;

import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.server.GameServer;

public class Zontik extends EquipmentCard {

    public Zontik() {
        super(0, 0, false, false, true, "images/equipment/zontik.png");
    }

    @Override
    public boolean useInTurn(GameServer serverInfo, GameCharacter character) {
        if (character.getThirst() > 0) {
            character.setThirst(character.getThirst() - 1);
        }
        return true;
    }

    @Override
    public boolean consume(GameCharacter character) {
        return false;
    }
}
