package vangoh74.backend.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import vangoh74.backend.HelpMethodsForTests;
import vangoh74.backend.model.Deck;
import vangoh74.backend.model.Player;
import vangoh74.backend.model.Seat;
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
        List<Seat> seats = new ArrayList<>();
        Player playerToBeAdded = helpMethods.createNewPlayer("Ana", "Ana-Avatar", deck);
        List<Player> players = new ArrayList<>();
        players.add(playerToBeAdded);

        TableItem item = TableItem.builder()
                .maxSize(3)
                .seats(seats)
                .build();

        when(tableItemsRepository.insert(item)).thenReturn(TableItem.builder()
                .id("1234")
                .maxSize(3)
                .tableCards(helpMethods.createListOfCards(5, deck))
                .seats(seats)
                .build());

        TableItem itemToBeUpdated = TableItem.builder()
                .id("1234")
                .players(players)
                .build();

        when(tableItemsRepository.save(itemToBeUpdated)).thenReturn(TableItem.builder()
                .id("1234")
                .maxSize(3)
                .players(players)
                .seats(seats)
                .build());

        // WHEN
        gameService.addPlayerToTableItem(playerToBeAdded, "1234");
        Optional<TableItem> actual = tableItemsRepository.findById("1234");

        // THEN
        TableItem expected = TableItem.builder()
                .id("1234")
                .maxSize(3)
                .players(players)
                .seats(seats)
                .build();

        assertEquals(expected, actual);

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