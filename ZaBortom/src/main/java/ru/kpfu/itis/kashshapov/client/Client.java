package ru.kpfu.itis.kashshapov.client;

import ru.kpfu.itis.kashshapov.GameApplication;
import ru.kpfu.itis.kashshapov.characters.GameCharacter;
import ru.kpfu.itis.kashshapov.context.ApplicationContext;
import ru.kpfu.itis.kashshapov.controllers.GameScreenController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Client extends Thread {

    private static Client theClient;
    private String username;
    private InetAddress address;
    private final int port;
    private GameCharacter character;
    private DatagramSocket socket;
    private byte[] buffer = new byte[10240];
    private ByteArrayInputStream byteArrayInputStream;
    private ObjectInput objectInput;

    public Client(String username, String address, int port) {
        try {
            theClient = this;
            this.setDaemon(true);
            this.username = username;
            this.address = InetAddress.getByName(address);
            this.port = port;
            socket = new DatagramSocket();
            DatagramPacket confirm = new DatagramPacket(buffer, buffer.length, this.address, port);
            confirm.setData(username.getBytes(StandardCharsets.UTF_8));
            try {
                socket.send(confirm);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                byte[] request = packet.getData();
                byteArrayInputStream = new ByteArrayInputStream(request);
                byteArrayInputStream.read();
                switch (request[0]) {
                    case 0 -> {
                        GameApplication.getGameApplication().getStage().setScene(ApplicationContext.getGameScene().load());
                        objectInput = new ObjectInputStream(byteArrayInputStream);
                        List<GameCharacter> characters = (List<GameCharacter>) objectInput.readObject();
                        character = characters.get(byteArrayInputStream.read());
                        GameScreenController.getController().updateView(characters);
                    }
                    case 1 -> {
                        objectInput = new ObjectInputStream(byteArrayInputStream);
                        List<GameCharacter> characters = (List<GameCharacter>) objectInput.readObject();
                        character = characters.get(byteArrayInputStream.read());
                        GameScreenController.getController().updateView(characters);
                    }
                    case 2 -> {
                        objectInput = new ObjectInputStream(byteArrayInputStream);
                        GameCharacter fighting = (GameCharacter) objectInput.readObject();
                        if (character.getPower() < fighting.getPower()) {
                            packet.setData(new byte[]{3});
                        } else {
                            packet.setData(new byte[]{4});
                        }
                        socket.send(packet);
                        character.setWasFighting(true);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public static Client getTheClient() {
        return theClient;
    }
}
