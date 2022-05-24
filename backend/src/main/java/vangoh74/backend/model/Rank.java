package vangoh74.backend.model;

public enum Rank {

    ACE(1, 'A'),
    TWO(2, '2'),
    THREE(3, '3'),
    FOUR(4, '4'),
    FIVE(5, '5'),
    SIX(6, '6'),
    SEVEN(7, '7'),
    EIGHT(8, '8'),
    NINE(9, '9'),
    TEN(10,'T'),
    JACK(11, 'J'),
    QUEEN(12, 'Q'),
    KING(13, 'K');

    private final int rankValue;
    private final char rankSymbol;
    Rank(int rankValue, char rankSymbol) {
        this.rankValue = rankValue;
        this.rankSymbol = rankSymbol;
    }

}
