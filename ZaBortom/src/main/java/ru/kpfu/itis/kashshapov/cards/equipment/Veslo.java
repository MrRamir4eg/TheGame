package ru.kpfu.itis.kashshapov.cards.equipment;

import ru.kpfu.itis.kashshapov.cards.navigation.NavigationCard;
import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.server.GameServer;

import java.util.Collection;
import java.util.Collections;

public class Veslo extends EquipmentCard {

    public Veslo() {
        super(0, 1, true, false, true, "images/equipment/veslo.png");
    }

    @Override
    public boolean useInTurn(GameServer serverInfo, GameCharacter character) {
        Collections.shuffle(NavigationCard.getDeck());
        serverInfo.getChoice().add(NavigationCard.getDeck().get(0));
        return true;
    }

    @Override
    public boolean consume(GameCharacter character) {
        return false;
    }
}
