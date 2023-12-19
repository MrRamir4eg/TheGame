package ru.kpfu.itis.kashshapov.client;

import ru.kpfu.itis.kashshapov.characters.GameCharacter;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Client extends Thread {

    private static Client theClient;
    private String username;
    private InetAddress address;
    private final int port;
    private GameCharacter character;
    private DatagramSocket socket;
    private byte[] buffer = new byte[10240];

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
