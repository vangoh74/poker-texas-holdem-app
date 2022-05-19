package vangoh74.backend.model;

public enum Suit {
    DIAMONDS(0),
    CLUBS(1),
    HEARTS(2),
    SPADES(3);
    private int suitValue;
    Suit(int suitValue) {
        this.suitValue = suitValue;
    }
}
