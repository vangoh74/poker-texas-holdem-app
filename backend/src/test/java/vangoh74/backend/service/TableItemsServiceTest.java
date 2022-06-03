package vangoh74.backend.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import vangoh74.backend.dto.TableItemDto;
import vangoh74.backend.model.Card;
import vangoh74.backend.model.Rank;
import vangoh74.backend.model.Suit;
import vangoh74.backend.model.TableItem;
import vangoh74.backend.repository.TableItemsRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TableItemsServiceTest {

    private final TableItemsRepository tableItemsRepository = mock(TableItemsRepository.class);
    private final TableItemsService tableItemsService = new TableItemsService(tableItemsRepository);

    @Test
    void getTableItemsTest_whenTableItemsAreAvailable_thenReturnsAllTableItems() {
        // GIVEN
        Card card_1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card_2 = new Card(Rank.JACK, Suit.DIAMONDS);
        Card card_3 = new Card(Rank.TWO, Suit.HEARTS);
        Card card_4 = new Card(Rank.TEN, Suit.HEARTS);

        List<Card> round_1_cards = new ArrayList<>();
        round_1_cards.add(card_1);
        round_1_cards.add(card_2);

        List<Card> round_2_cards = new ArrayList<>();
        round_2_cards.add(card_3);
        round_2_cards.add(card_4);

        TableItem item_1 = TableItem.builder()
                .id("1")
                .roundNumber(1)
                .tableCards(round_1_cards)
                .build();

        TableItem item_2 = TableItem.builder()
                .id("2")
                .roundNumber(2)
                .tableCards(round_2_cards)
                .build();

        when(tableItemsRepository.findAll()).thenReturn(List.of(item_1, item_2));

        // WHEN
        List<TableItem> actual = tableItemsService.getTableItems();

        // THEN
        List<TableItem> expected = List.of(
                TableItem.builder()
                    .id("1")
                    .roundNumber(1)
                    .tableCards(round_1_cards)
                    .build(),
                TableItem.builder()
                    .id("2")
                    .roundNumber(2)
                    .tableCards(round_2_cards)
                    .build()
        );

        verify(tableItemsRepository).findAll();
        assertEquals(expected, actual);
    }

    @Disabled
    @Test
    void addNewTableItemTest_whenNewTableItemGiven_thenAddInToDBAndReturnNewItemAdded() {
        // GIVEN
        Card card_1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card_2 = new Card(Rank.JACK, Suit.DIAMONDS);

        List<Card> round_1_cards = new ArrayList<>();
        round_1_cards.add(card_1);
        round_1_cards.add(card_2);

        TableItem itemToAdd = TableItem.builder()
                .roundNumber(1)
                .tableCards(round_1_cards)
                .build();
        when(tableItemsRepository.insert(itemToAdd)).thenReturn(TableItem.builder()
                .id("Test")
                .roundNumber(1)
                .tableCards(round_1_cards)
                .build());

        // WHEN
        TableItemDto newTableItem = TableItemDto.builder()
                .roundNbr(1)
                .tableCards(round_1_cards)
                .build();

        TableItem actual = tableItemsService.addNewTableItem(newTableItem);

        // THEN
        TableItem expected = TableItem.builder()
                .id("Test")
                .roundNumber(1)
                .tableCards(round_1_cards)
                .build();
        assertEquals(expected, actual);
    }
}