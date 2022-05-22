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

    private final Deck deck = new Deck();

    public void initShuffledDeck() {
        List<Card> newDeckCards = new ArrayList<>();
        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                newDeckCards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(newDeckCards);
        deck.setPokerDeck(newDeckCards);
    }

    public Card deal() {
        int topCardIndex = 0;

        if (deck.getPokerDeck().isEmpty()) {
            throw new RuntimeException("Deckcards is Empty!!");
        }

        return deck.getPokerDeck().remove(topCardIndex);
    }

}
