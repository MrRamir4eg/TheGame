package ru.kpfu.itis.kashshapov.cards.navigation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public enum NavigationCard implements Serializable {
    NAV1(1, new String[]{"Bozman"}, new String[]{"Shket"}, true, false, "images/navigation/nav1.png"),
    NAV2(1, new String[]{"Snob"}, new String[]{"Shket", "Captain"}, false, true, "images/navigation/nav2.png"),
    NAV3(1, new String[]{"Bozman", "Captain", "Snob", "Milady", "Shket", "Cherpak"}, new String[]{"Bozman", "Captain", "Snob", "Milady", "Shket", "Cherpak"}, true, true, "images/navigation/nav3.png"),
    NAV4(0, new String[]{"Bozman"}, new String[]{"Milady"}, false, false, "images/navigation/nav4.png"),
    NAV5(1, new String[]{"Cherpak"}, new String[]{"Bozman", "Captain", "Snob"}, false, false, "images/navigation/nav5.png"),
    NAV6(0, new String[]{"Bozman", "Captain", "Snob", "Milady", "Shket", "Cherpak"}, new String[]{"Bozman", "Captain", "Snob", "Milady", "Shket", "Cherpak"}, true, false, "images/navigation/nav6.png"),
    NAV7(1, new String[]{}, new String[]{}, false, false, "images/navigation/nav7.png"),
    NAV8(0, new String[]{"Cherpak"}, new String[]{"Bozman", "Captain", "Milady"}, true, false, "images/navigation/nav8.png"),
    NAV9(0, new String[]{"Bozman"}, new String[]{"Captain", "Cherpak"}, true, true, "images/navigation/nav9.png"),
    NAV10(0, new String[]{"Cherpak"}, new String[]{"Bozman", "Captain", "Snob", "Cherpak"}, true, true, "images/navigation/nav10.png"),
    NAV11(0, new String[]{"Shket"}, new String[]{"Bozman", "Captain", "Milady", "Cherpak"}, false, false, "images/navigation/nav11.png"),
    NAV12(-1, new String[]{"Milady"}, new String[]{"Bozman", "Captain", "Snob", "Milady", "Shket", "Cherpak"}, false, true, "images/navigation/nav12.png"),
    NAV13(0, new String[]{"Shket"}, new String[]{"Bozman", "Captain", "Snob", "Shket", "Cherpak"}, true, false, "images/navigation/nav13.png"),
    NAV14(0, new String[]{"Bozman"}, new String[]{"Bozman", "Captain"}, false, true, "images/navigation/nav14.png"),
    NAV15(0, new String[]{"Shket"}, new String[]{"Bozman", "Captain", "Snob", "Milady", "Cherpak"}, false, true, "images/navigation/nav15.png"),
    NAV16(0, new String[]{"Captain"}, new String[]{"Snob"}, true, true, "images/navigation/nav16.png"),
    NAV17(1, new String[]{"Shket"}, new String[]{"Bozman", "Captain", "Snob", "Shket", "Cherpak"}, true, true, "images/navigation/nav17.png");

    private static List<NavigationCard> deck = new ArrayList<>();

    static {
        deck.add(NAV1);
        deck.add(NAV2);
        deck.add(NAV3);
        deck.add(NAV4);
        deck.add(NAV5);
        deck.add(NAV6);
        deck.add(NAV7);
        deck.add(NAV8);
        deck.add(NAV9);
        deck.add(NAV10);
        deck.add(NAV11);
        deck.add(NAV12);
        deck.add(NAV13);
        deck.add(NAV14);
        deck.add(NAV15);
        deck.add(NAV16);
        deck.add(NAV17);
    }

    private int seagullCount;
    private String[] outOfBoard;
    private String[] thirsty;
    private boolean forFighting;
    private boolean forNavigating;
    private final String imageLocation;

    NavigationCard(int seagullCount, String[] outOfBoard, String[] thirsty, boolean forFighting, boolean forNavigating, String imageLocation) {
        this.seagullCount = seagullCount;
        this.outOfBoard = outOfBoard;
        this.thirsty = thirsty;
        this.forFighting = forFighting;
        this.forNavigating = forNavigating;
        this.imageLocation = imageLocation;
    }

    public int getSeagullCount() {
        return seagullCount;
    }

    public String[] getOutOfBoard() {
        return outOfBoard;
    }

    public String[] getThirsty() {
        return thirsty;
    }

    public boolean isForFighting() {
        return forFighting;
    }

    public boolean isForNavigating() {
        return forNavigating;
    }

    public static List<NavigationCard> getDeck() {
        return deck;
    }
}
