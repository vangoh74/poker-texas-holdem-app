package vangoh74.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Deck {
    private List<Card> pokerDeck;
}
