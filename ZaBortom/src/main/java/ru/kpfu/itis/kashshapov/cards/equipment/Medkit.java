package ru.kpfu.itis.kashshapov.cards.equipment;

import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.server.GameServer;

public class Medkit extends EquipmentCard {

    public Medkit() {
        super(0, 0, false, true, false, "images/equipment/medkit.png");
    }

    @Override
    public boolean useInTurn(GameServer serverInfo, GameCharacter character) {
        return false;
    }

    @Override
    public boolean consume(GameCharacter character) {
        character.setHp(character.getHp() + 1);
        return true;
    }
}
