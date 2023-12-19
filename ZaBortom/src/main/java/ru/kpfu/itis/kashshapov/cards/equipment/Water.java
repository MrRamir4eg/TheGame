package ru.kpfu.itis.kashshapov.cards.equipment;

import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.server.GameServer;

public class Water extends EquipmentCard{

    public Water() {
        super(0, 0, false, true, false, "images/equipment/water.png");
    }
    @Override
    public boolean useInTurn(GameServer serverInfo, GameCharacter character) {
        return false;
    }

    @Override
    public boolean consume(GameCharacter character) {
        if (character.getThirst() > 0) {
            character.setThirst(character.getThirst() - 1);
            return true;
        }
        return false;
    }
}
