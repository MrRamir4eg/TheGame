package ru.kpfu.itis.kashshapov.cards.equipment;

import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.server.GameServer;

public class SharkTrap extends EquipmentCard {

    public SharkTrap() {
        super(0, 0, false, false, true, "images/equipment/shark_trap.png");
    }

    @Override
    public boolean useInTurn(GameServer serverInfo, GameCharacter character) {
        for (GameCharacter i : serverInfo.getOutOfBoard()) {
            i.setHp(i.getHp());
        }
        return true;
    }

    @Override
    public boolean consume(GameCharacter character) {
        return false;
    }
}
