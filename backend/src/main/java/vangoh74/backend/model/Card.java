package vangoh74.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Card {
    private Rank rank;
    private Suit suit;
}