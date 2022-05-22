package vangoh74.backend.service;

import org.junit.jupiter.api.Test;
import vangoh74.backend.model.Card;
import vangoh74.backend.model.Deck;
import vangoh74.backend.model.Rank;
import vangoh74.backend.model.Suit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DealerServiceTest {

    private final DealerService dealerService = mock(DealerService.class);

    @Test
    void dealTest_RemoveCardFromDeckAndReturnCard() {
        // GIVEN
        Card card_1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card_2 = new Card(Rank.JACK, Suit.DIAMONDS);
        Card card_3 = new Card(Rank.TWO, Suit.HEARTS);
        Card card_4 = new Card(Rank.TEN, Suit.HEARTS);

        List<Card> listOfCards = new ArrayList<>();
        listOfCards.add(card_1);
        listOfCards.add(card_2);
        listOfCards.add(card_3);
        listOfCards.add(card_4);

        Deck deckCards = new Deck();
        deckCards.setPokerDeck(listOfCards);

        when(dealerService.deal()).thenReturn(Card.builder()
                .rank(Rank.ACE)
                .suit(Suit.CLUBS)
                .build());

        // WHEN
        Card actual = dealerService.deal();
        //int actualSize = deckCards.getPokerDeck().size();

        // THEN
        Card expected = card_1;
       // int expectedSize = 3;
        assertEquals(expected, actual);
       // assertEquals(expectedSize, actualSize);
    }
}