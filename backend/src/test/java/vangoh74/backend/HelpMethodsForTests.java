package vangoh74.backend;

import vangoh74.backend.model.Card;
import vangoh74.backend.model.Deck;
import vangoh74.backend.model.Player;
import vangoh74.backend.service.DealerService;

import java.util.ArrayList;
import java.util.List;

public class HelpMethodsForTests {
    private final DealerService dealerService;

    public HelpMethodsForTests(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    public Player createNewPlayer(String name, String image, Deck deck) {
        return Player.builder()
                .playerName(name)
                .playerChips(1000)
                .playerCards(createListOfCards(2, deck))
                .playerImage(image)
                .build();
    }

    public List<Card> createListOfCards(int numberOfCards, Deck deck) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            cards.add(dealerService.deal(deck));
        }
        return cards;
    }
}
