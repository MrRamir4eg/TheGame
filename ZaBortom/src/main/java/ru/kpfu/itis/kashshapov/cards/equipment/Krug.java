package ru.kpfu.itis.kashshapov.cards.equipment;

import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.server.GameServer;

public class Krug extends EquipmentCard {

    public Krug() {
        super(0, 0, false, false, true, "images/equipment/krug.png");
    }

    @Override
    public boolean useInTurn(GameServer serverInfo, GameCharacter character) {
        character.setHp(character.getHp() + 1);
        return true;
    }

    @Override
    public boolean consume(GameCharacter character) {
        return false;
    }
}
