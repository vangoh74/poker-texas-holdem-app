package vangoh74.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vangoh74.backend.model.Card;
import vangoh74.backend.model.Rank;
import vangoh74.backend.model.Suit;

import java.util.*;
@Service
public class DeckService {
    private final List<Card> deckCards;
    @Autowired
    public DeckService() {
        this.deckCards = initShuffledDeck();
    }

    private List<Card> initShuffledDeck() {
        List<Card> newDeckCards = new ArrayList<>();
        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                newDeckCards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(newDeckCards);
        return newDeckCards;
    }

    public Optional<Card> deal() {
        int topCardIndex = 0;
        return this.deckCards.isEmpty() ? Optional.empty() : Optional.of(this.deckCards.remove(topCardIndex));
    }

}
