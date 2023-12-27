package ru.kpfu.itis.kashshapov.server;

import ru.kpfu.itis.kashshapov.cards.equipment.*;
import ru.kpfu.itis.kashshapov.cards.navigation.NavigationCard;
import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.client.Client;
import ru.kpfu.itis.kashshapov.controllers.CreateController;
import ru.kpfu.itis.kashshapov.enums.Phase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.*;

public class GameServer extends Thread {

    private static GameServer theServer;
    private List<GameCharacter> characters;
    private List<GameCharacter> order;
    private DatagramSocket server;
    private Set<Client> players;
    private List<EquipmentCard> equipmentCards;
    private List<NavigationCard> navigationDeck;
    private int seagullCount;
    private byte[] buffer = new byte[10240];
    private byte[] objectBuffer = new byte[10240];
    private Phase currentPhase;
    private Set<GameCharacter> outOfBoard;
    private NavigationCard choice;
    private final int port;
    private ObjectInput objectInput;
    private ByteArrayInputStream inputStream;

    public GameServer(int port) {
        try {
            theServer = this;
            this.port = port;
            server = new DatagramSocket(port);
            players = new HashSet<>();
            characters = new ArrayList<>();
            characters.add(GameCharacter.MILADY);
            characters.add(GameCharacter.SNOB);
            characters.add(GameCharacter.CAPTAIN);
            characters.add(GameCharacter.BOZMAN);
            characters.add(GameCharacter.CHERPAK);
            characters.add(GameCharacter.SHKET);
            inputStream = new ByteArrayInputStream(buffer);
            this.setDaemon(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        CreateController controller = CreateController.getController();
        while (true) {
            if (currentPhase == null) {
                if (players.size() < 6) {
                    if (controller != null) {
                        controller.getJoinGameButton().setVisible(false);
                    }
                    waitForPlayers();
                } else {
                    if (controller != null) {
                        controller.getJoinGameButton().setVisible(true);
                    }
                }
            } else {
                buffer[0] = 0;
                for (Client i : players) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, i.getAddress(), i.getPort());
                    try {
                        server.send(packet);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                switch (currentPhase) {
                    case INIT -> {
                        if (seagullCount == 4) {
                            currentPhase = Phase.SCORE;
                            continue;
                        }
                        order = new ArrayList<>();
                        for (GameCharacter i : characters) {
                            if (!i.isUnconscious() && !i.isDead()) {
                                order.add(i);
                            }
                        }
                        setNavigationDeck();
                        setEquipmentCards();
                        currentPhase = Phase.MORNING;
                    }
                    case MORNING -> {
                        Collections.shuffle(equipmentCards);
                        List<EquipmentCard> toGive = new ArrayList<>();
                        for (int i = 0; i < 6; i++) {
                            toGive.add(equipmentCards.get(0));
                        }
                        currentPhase = Phase.DAY;
                    }
                    case DAY -> {
                        try {
                            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                            server.receive(packet);
                            inputStream = new ByteArrayInputStream(packet.getData());
                            choice = (NavigationCard) objectInput.readObject();
                            currentPhase = Phase.NAVIGATING;
                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case NAVIGATING -> {
                        String[] out = choice.getOutOfBoard();
                        ;
                        String[] thirsty = choice.getThirsty();
                        seagullCount += choice.getSeagullCount();
                        if (seagullCount < 0) {
                            seagullCount = 0;
                        }

                        for (String name : out) {
                            for (GameCharacter character : characters) {
                                if (name.equals(character.getName())) {
                                    if (!name.equals("Cherpak")) {
                                        character.setHp(character.getHp() - 1);
                                    }
                                    character.setActiveDeck(new ArrayList<>());
                                }
                            }
                        }

                        for (String name : thirsty) {
                            for (GameCharacter character : characters) {
                                if (name.equals(character.getName())) {
                                    character.setThirst(character.getThirst() + 1);
                                }
                            }
                        }
                        currentPhase = Phase.INIT;
                    }
                    case SCORE -> {
                        int score = 0;
                        for (GameCharacter character : characters) {
                            for (EquipmentCard i : character.getDeck()) {
                                score += i.getPoints();
                                if (character == GameCharacter.MILADY && i instanceof Jewels) {
                                    score += i.getPoints();
                                }
                                if (character == GameCharacter.SNOB && i instanceof Painting) {
                                    score += i.getPoints();
                                }
                                if (character == GameCharacter.CAPTAIN && i instanceof Money) {
                                    score += i.getPoints();
                                }
                            }
                            if (character.getEnemy().isDead()) {
                                score += character.getEnemy().getPower();
                            }
                            if (!character.getFriend().isDead()) {
                                score += character.getFriend().getPointsForSurvival();
                            }
                            character.setScore(score);
                            score = 0;
                        }
                    }
                }
            }
        }
    }

    private void waitForPlayers() {
        try {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            server.receive(packet);
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            String username = new String(packet.getData(), 0, packet.getLength());
            players.add(new Client(username, address.toString(), port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSeagullCount() {
        return seagullCount;
    }

    public void setSeagullCount(int seagullCount) {
        this.seagullCount = seagullCount;
    }

    public Set<GameCharacter> getOutOfBoard() {
        return outOfBoard;
    }

    public void setOutOfBoard(Set<GameCharacter> outOfBoard) {
        this.outOfBoard = outOfBoard;
    }

    public static GameServer getTheServer() {
        return theServer;
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
    }

    private void setNavigationDeck() {
        navigationDeck = NavigationCard.getDeck();
    }

    private void setEquipmentCards() {
        equipmentCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            equipmentCards.add(new Water());
            equipmentCards.add(new Painting());
            equipmentCards.add(new Jewels());
            equipmentCards.add(new Money());
        }
        equipmentCards.add(new Bagor());
        equipmentCards.add(new Club());
        equipmentCards.add(new Compass());
        equipmentCards.add(new Knife());
        equipmentCards.add(new Medkit());
        equipmentCards.add(new Pistol());
        equipmentCards.add(new Krug());
        equipmentCards.add(new SharkTrap());
        equipmentCards.add(new Zontik());
    }

    public NavigationCard getChoice() {
        return choice;
    }
}
