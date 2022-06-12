package vangoh74.backend.service;

import org.springframework.stereotype.Service;
import vangoh74.backend.model.Card;
import vangoh74.backend.model.Deck;
import vangoh74.backend.model.Rank;
import vangoh74.backend.model.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DealerService {

    private Deck deck;

    public Deck initShuffledDeck() {
        this.deck = new Deck();
        List<Card> newDeckCards = new ArrayList<>();
        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                newDeckCards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(newDeckCards);
        this.deck.setPokerDeck(newDeckCards);

        return this.deck;
    }

    public Card deal(Deck deck) {
        int topCardIndex = 0;

        if (deck.getPokerDeck().isEmpty()) {
            throw new RuntimeException("Deck of cards is Empty!!");
        }

        return deck.getPokerDeck().remove(topCardIndex);
    }

}
