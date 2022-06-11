package vangoh74.backend.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import vangoh74.backend.HelpMethodsForTests;
import vangoh74.backend.dto.TableItemDto;
import vangoh74.backend.model.*;
import vangoh74.backend.repository.TableItemsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TableItemsServiceTest {

    private final TableItemsRepository tableItemsRepository = mock(TableItemsRepository.class);
    private final DealerService dealerService = mock(DealerService.class);
    private final TableItemsService tableItemsService = new TableItemsService(tableItemsRepository, dealerService);
    private final HelpMethodsForTests helpMethods = new HelpMethodsForTests(dealerService);

    @Test
    void getTableItemsTest_whenTableItemsAreAvailable_thenReturnsAllTableItems() {

        // GIVEN
        Deck deckOfTable_1 = dealerService.initShuffledDeck();
        List<Seat> table_1_seats = new ArrayList<>();
        List<Player> table_1_players = new ArrayList<>();
        table_1_players.add(helpMethods.createNewPlayer("Anton", "Anton-Avatar", deckOfTable_1));
        table_1_players.add(helpMethods.createNewPlayer("Anna", "Anna-Avatar", deckOfTable_1));

        TableItem item_1 = TableItem.builder()
                .id("1")
                .maxSize(2)
                .tableCards(helpMethods.createListOfCards(5, deckOfTable_1))
                .players(table_1_players)
                .seats(table_1_seats)
                .build();
        Deck deck = dealerService.initShuffledDeck();

        Deck deckOfTable_2 = dealerService.initShuffledDeck();
        List<Seat> table_2_seats = new ArrayList<>();
        List<Player> table_2_players = new ArrayList<>();
        table_1_players.add(helpMethods.createNewPlayer("Peter", "Peter-Avatar", deckOfTable_2));
        table_1_players.add(helpMethods.createNewPlayer("Amelie", "Amelie-Avatar", deckOfTable_2));

        TableItem item_2 = TableItem.builder()
                .id("2")
                .maxSize(5)
                .tableCards(helpMethods.createListOfCards(5, deckOfTable_2))
                .players(table_2_players)
                .seats(table_2_seats)
                .build();

        when(tableItemsRepository.findAll()).thenReturn(List.of(item_1, item_2));

        // WHEN
        List<TableItem> actual = tableItemsService.getTableItems();

        // THEN
        List<TableItem> expected = List.of(
                TableItem.builder()
                        .id("1")
                        .maxSize(2)
                        .tableCards(item_1.getTableCards())
                        .seats(table_1_seats)
                        .players(table_1_players)
                        .build(),

                TableItem.builder()
                        .id("2")
                        .maxSize(5)
                        .tableCards(item_2.getTableCards())
                        .players(table_2_players)
                        .seats(table_2_seats)
                        .build()
        );

        verify(tableItemsRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void addNewTableItemTest_whenNewTableItemGiven_thenAddInToDBAndReturnNewItemAdded() {

        // GIVEN
        DealerService dealer = new DealerService();
        Deck deck = dealer.initShuffledDeck();
        List<Seat> seats = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        players.add(helpMethods.createNewPlayer("Ana", "Ana-Avatar", deck));

        TableItem itemToAdd = TableItem.builder()
                .maxSize(2)
                .tableCards(helpMethods.createListOfCards(5, deck))
                .players(players)
                .seats(seats)
                .build();

        when(tableItemsRepository.insert(itemToAdd)).thenReturn(TableItem.builder()
                .id("1234")
                .maxSize(2)
                .tableCards(itemToAdd.getTableCards())
                .players(players)
                .seats(seats)
                .build());

        // WHEN
        TableItemDto tableItemDto = TableItemDto.builder()
                .maxSize(2)
                .players(players)
                .seats(seats)
                .build();

        TableItem actual = tableItemsService.addNewTableItem(tableItemDto);

        // THEN
        TableItem expected = TableItem.builder()
                .id("1234")
                .maxSize(2)
                .tableCards(itemToAdd.getTableCards())
                .players(players)
                .seats(seats)
                .build();
        assertEquals(expected, actual);
    }

    @Test
    void getTableItemsByTableId_whenIdIsValid() {

        // GIVEN
        Deck deck = dealerService.initShuffledDeck();
        List<Card> tableCards = helpMethods.createListOfCards(5, deck);
        when(tableItemsRepository.findById("123")).thenReturn(
                Optional.of(TableItem.builder()
                        .id("123")
                        .bigBlind(10)
                        .maxSize(2)
                        .roundNumber(1)
                        .tableCards(tableCards)
                        .build()
                )
        );

        // WHEN
        TableItem actual = tableItemsService.getTableItemsByTableId("123");

        // THEN
        TableItem expected = TableItem.builder()
                .id("123")
                .bigBlind(10)
                .maxSize(2)
                .roundNumber(1)
                .tableCards(tableCards)
                .build();

        verify(tableItemsRepository).findById("123");
        assertEquals(expected, actual);
    }

    @Test
    void getTableItemsByTableIdTest_ifTableIdIsNotValid_shouldThrowException() {

        // GIVEN
        when(tableItemsRepository.findById("123")).thenReturn(Optional.empty());

        //WHEN //THEN
        assertThrows(NoSuchElementException.class,
                () -> tableItemsService.getTableItemsByTableId("123"));

        verify(tableItemsRepository).findById("123");
    }

}
