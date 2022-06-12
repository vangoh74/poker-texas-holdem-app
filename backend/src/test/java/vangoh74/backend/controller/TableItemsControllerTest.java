package vangoh74.backend.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;
import vangoh74.backend.HelpMethodsForTests;
import vangoh74.backend.dto.TableItemDto;
import vangoh74.backend.model.*;
import vangoh74.backend.repository.TableItemsRepository;
import vangoh74.backend.security.model.AppUser;
import vangoh74.backend.security.repository.AppUserRepository;
import vangoh74.backend.service.DealerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TableItemsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TableItemsRepository tableItemsRepository;

    private final DealerService dealerService = new DealerService();

    private final HelpMethodsForTests helpMethods = new HelpMethodsForTests(dealerService);

    private String dummy_JWT;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserRepository appUserRepository;

    @BeforeEach
    public void cleanUp() {
        tableItemsRepository.deleteAll();
        appUserRepository.deleteAll();

        dummy_JWT = generateJwt();
    }

    @Test
    void getTableItemsTest_ReturnAllTableItemsFromDB() {

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

        Deck deckOfTable_2 = dealerService.initShuffledDeck();
        List<Seat> table_2_seats = new ArrayList<>();
        List<Player> table_2_players = new ArrayList<>();
        table_1_players.add(helpMethods.createNewPlayer("Peter", "Anton-Avatar", deckOfTable_2));
        table_1_players.add(helpMethods.createNewPlayer("Amelie", "Amelie-Avatar", deckOfTable_2));

        TableItem item_2 = TableItem.builder()
                .id("2")
                .maxSize(5)
                .tableCards(helpMethods.createListOfCards(5, deckOfTable_2))
                .players(table_2_players)
                .seats(table_2_seats)
                .build();

        tableItemsRepository.insert(item_1);
        tableItemsRepository.insert(item_2);

        // WHEN
        List<TableItem> actual = webTestClient.get()
                .uri("/api/tableitems")
                .headers(http -> http.setBearerAuth(dummy_JWT))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(TableItem.class)
                .returnResult()
                .getResponseBody();

        // THEN
        List<TableItem> expected = List.of(
                TableItem.builder()
                        .id("1")
                        .maxSize(2)
                        .tableCards(item_1.getTableCards())
                        .players(table_1_players)
                        .seats(table_1_seats)
                        .build(),

                TableItem.builder()
                        .id("2")
                        .maxSize(5)
                        .tableCards(item_2.getTableCards())
                        .players(table_2_players)
                        .seats(table_2_seats)
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    void postNewTableItemTest_returnAddedNewItem() {

        // GIVEN
        Deck deck = dealerService.initShuffledDeck();
        List<Seat> seats = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        players.add(helpMethods.createNewPlayer("Anton", "Anton-Avatar", deck));
        players.add(helpMethods.createNewPlayer("Anna", "Anna-Avatar", deck));

        TableItem newItem = TableItem.builder()
                .maxSize(4)
                .players(players)
                .seats(seats)
                .build();

        // WHEN
        TableItem actual = webTestClient.post()
                .uri("/api/tableitems")
                .headers(http -> http.setBearerAuth(dummy_JWT))
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
                .maxSize(4)
                .tableCards(actual.getTableCards())
                .players(players)
                .seats(seats)
                .build();
        assertEquals(expected, actual);
    }

    @Test
    void getTableItemsByTableId_whenTableIdIsValid() {

        // GIVEN
        TableItem tableItem = TableItem.builder()
                .id("123")
                .bigBlind(10)
                .roundState(RoundState.PRE_FLOP)
                .maxSize(2)
                .roundNumber(1)
                .build();

        TableItem addedTableItem = tableItemsRepository.insert(tableItem);

        // WHEN
        assertNotNull(addedTableItem);
        TableItem actual = webTestClient.get()
                .uri("http://localhost:" + port + "/api/tableitems/" + addedTableItem.getId())
                .headers(http -> http.setBearerAuth(dummy_JWT))
                .exchange()
                .expectBody(TableItem.class)
                .returnResult()
                .getResponseBody();

        // THEN
        assertNotNull(actual);
        TableItem expected = TableItem.builder()
                .id(actual.getId())
                .bigBlind(10)
                .roundState(RoundState.PRE_FLOP)
                .maxSize(2)
                .roundNumber(1)
                .tableCards(actual.getTableCards())
                .build();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getTableItemsByTableIdTest_whenTableIdIsNotValid_shouldThrowException() {

        // GIVEN
        TableItemDto tableItemDto = TableItemDto.builder()
                .bigBlind(10)
                .maxSize(2)
                .freeSeats(2)
                .roundNumber(1)
                .build();

        webTestClient.post()
                .uri("http://localhost:" + port + "/api/tableitems")
                .headers(http -> http.setBearerAuth(dummy_JWT))
                .bodyValue(tableItemDto)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(TableItem.class)
                .returnResult();

        // WHEN
        webTestClient.get()
                .uri("http://localhost:" + port + "/api/tableitems" + "WRONG_ID")
                .headers(http -> http.setBearerAuth(dummy_JWT))
                .exchange()
        // THEN
                .expectStatus().is4xxClientError();

    }

    private String generateJwt() {

        String hashedPassword = passwordEncoder.encode("test_password");
        AppUser dummyUser = AppUser.builder()
                .username("test_username")
                .password(hashedPassword)
                .build();
        appUserRepository.save(dummyUser);

        return webTestClient.post()
                .uri("/auth/login")
                .bodyValue(AppUser.builder()
                        .username("test_username")
                        .password("test_password")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
    }

}
