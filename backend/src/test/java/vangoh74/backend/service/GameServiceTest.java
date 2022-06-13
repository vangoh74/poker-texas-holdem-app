package vangoh74.backend.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import vangoh74.backend.HelpMethodsForTests;
import vangoh74.backend.model.Deck;
import vangoh74.backend.model.Player;
import vangoh74.backend.model.TableItem;
import vangoh74.backend.repository.TableItemsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameServiceTest {

    private final TableItemsRepository tableItemsRepository = mock(TableItemsRepository.class);
    private final DealerService dealerService = new DealerService();

    private final GameService gameService = new GameService(tableItemsRepository);
    private final HelpMethodsForTests helpMethods = new HelpMethodsForTests(dealerService);

    @Disabled("I don't know, how to mock it!!")
    @Test
    void addPlayerToTableItemTest_whenNewPlayerTakeASeat() {

        // GIVEN
        Deck deck = dealerService.initShuffledDeck();
        Player playerToBeAdded = helpMethods.createNewPlayer("Ana", "Ana-Avatar", deck);
        List<Player> players = new ArrayList<>();
        players.add(playerToBeAdded);

        TableItem item = TableItem.builder().maxSize(3).build();

        when(tableItemsRepository.insert(item)).thenReturn(TableItem.builder()
                .id("1234")
                .maxSize(3)
                .build());

        TableItem itemToBeUpdated = TableItem.builder()
                .id("1234")
                .players(players)
                .build();

        when(tableItemsRepository.save(itemToBeUpdated)).thenReturn(TableItem.builder()
                .id("1234")
                .maxSize(3)
                .players(players)
                .build());

        // WHEN

        // THEN

    }

    @Disabled("I don't know, how I to mock it!!")
    @Test
    void deletePlayerFromTableItem() {
        assertTrue(true);
    }

    @Disabled("I don't know, how I to mock it!!")
    @Test
    void getTableItemToBeUpdated() {
        assertTrue(true);
    }
}
