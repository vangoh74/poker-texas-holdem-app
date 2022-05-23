package vangoh74.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import vangoh74.backend.model.*;
import vangoh74.backend.repository.TableItemsRepository;
import vangoh74.backend.service.DealerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TableItemsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient testClient;

    @Autowired
    private TableItemsRepository tableItemsRepo;

    @BeforeEach
    public void cleanUp() {
        tableItemsRepo.deleteAll();
    }

    @Test
    void getTableItemsTest_ReturnAllGivenTableItems() {

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
                .roundNbr(1)
                .tableCards(round_1_cards)
                .build();

        TableItem item_2 = TableItem.builder()
                .id("2")
                .roundNbr(2)
                .tableCards(round_2_cards)
                .build();

        tableItemsRepo.insert(item_1);
        tableItemsRepo.insert(item_2);

        // WHEN
        List<TableItem> actual = testClient.get()
                .uri("/api/tableitems")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(TableItem.class)
                .returnResult()
                .getResponseBody();

        // THEN
        List<TableItem> expected = List.of(
                TableItem.builder()
                        .id("1")
                        .roundNbr(1)
                        .tableCards(round_1_cards)
                        .build(),
                TableItem.builder()
                        .id("2")
                        .roundNbr(2)
                        .tableCards(round_2_cards)
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    void postNewTableItemTest_returnAddedNewItem() {
        // GIVEN
        TableItem newItem = TableItem.builder()
                .roundNbr(1)
                .build();

        // WHEN
        TableItem actual = testClient.post()
                .uri("/api/tableitems")
                .bodyValue(newItem)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(TableItem.class)
                .returnResult()
                .getResponseBody();

        // THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        TableItem expected = TableItem.builder()
                .id(actual.getId())
                .roundNbr(1)
                .tableCards(actual.getTableCards())
                .build();
        assertEquals(expected, actual);
    }
}