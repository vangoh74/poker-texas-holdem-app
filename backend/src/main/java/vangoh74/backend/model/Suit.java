package vangoh74.backend.model;

public enum Suit {

    DIAMONDS('♦'),
    CLUBS('♣'),
    HEARTS('♥'),
    SPADES('♠');

    private char suitValue;
    Suit(char suitValue) {
        this.suitValue = suitValue;
    }

}
