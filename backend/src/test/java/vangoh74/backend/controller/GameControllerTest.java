package vangoh74.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;
import vangoh74.backend.HelpMethodsForTests;
import vangoh74.backend.model.*;
import vangoh74.backend.repository.TableItemsRepository;
import vangoh74.backend.security.model.AppUser;
import vangoh74.backend.security.repository.AppUserRepository;
import vangoh74.backend.service.DealerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameControllerTest {

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
    void postToAddPlayerToTableItem_whenPlayerDontExists_shouldAddNewPlayer() {

        // GIVEN
        Deck deck = dealerService.initShuffledDeck();
        List<Seat> seats = new ArrayList<>();
        TableItem tableItem = TableItem.builder()
                .bigBlind(6)
                .maxSize(2)
                .seats(seats)
                .build();

        TableItem addedTableItem = webTestClient.post()
                .uri("http://localhost:" + port + "/api/tableitems")
                .headers(http -> http.setBearerAuth(dummy_JWT))
                .bodyValue(tableItem)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(TableItem.class)
                .returnResult()
                .getResponseBody();

        // WHEN
        assertNotNull(addedTableItem);

        List<Player> players = new ArrayList<>();
        Player playerToBeAdded = helpMethods.createNewPlayer("Anton", "Anton-Avatar", deck);
        players.add(playerToBeAdded);

        TableItem updatedTableItem = TableItem.builder()
                .id(addedTableItem.getId())
                .bigBlind(6)
                .maxSize(2)
                .tableCards(addedTableItem.getTableCards())
                .players(players)
                .seats(seats)
                .build();

        webTestClient.post()
                .uri("http://localhost:" + port + "/api/tableitems/" + updatedTableItem.getId() + "/players")
                .headers(http -> http.setBearerAuth(dummy_JWT))
                .bodyValue(playerToBeAdded)
                .exchange()
                .expectStatus().is2xxSuccessful();

        TableItem actual = webTestClient.get()
                .uri("http://localhost:" + port + "/api/tableitems/" + updatedTableItem.getId())
                .headers(http -> http.setBearerAuth(dummy_JWT))
                .exchange()
                .expectBody(TableItem.class)
                .returnResult()
                .getResponseBody();

        // THEN
        TableItem expected = TableItem.builder()
                .id(addedTableItem.getId())
                .bigBlind(6)
                .maxSize(2)
                .roundState(addedTableItem.getRoundState())
                .tableCards(addedTableItem.getTableCards())
                .players(players)
                .seats(seats)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    void postToDeletePlayerFromTableItem_whenPlayerNameIsFound_DeleteThisPlayer() {

        // GIVEN
        Deck deck = dealerService.initShuffledDeck();
        List<Seat> seats = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        players.add(helpMethods.createNewPlayer("Anton", "Anton-Avatar", deck));
        players.add(helpMethods.createNewPlayer("Anna", "Anna-Avatar", deck));

        TableItem tableItem = TableItem.builder()
                .bigBlind(6)
                .maxSize(2)
                .tableCards(helpMethods.createListOfCards(5, deck))
                .players(players)
                .seats(seats)
                .build();

        TableItem addedTableItem = webTestClient.post()
                .uri("http://localhost:" + port + "/api/tableitems")
                .headers(http -> http.setBearerAuth(dummy_JWT))
                .bodyValue(tableItem)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(TableItem.class)
                .returnResult()
                .getResponseBody();

        // WHEN
        assertNotNull(addedTableItem);

        webTestClient.post()
                .uri("http://localhost:" + port + "/api/tableitems/" + addedTableItem.getId() + "/players/Anna")
                .headers(http -> http.setBearerAuth(dummy_JWT))
                .exchange()
                .expectStatus().is2xxSuccessful();

        TableItem actual = webTestClient.get()
                .uri("http://localhost:" + port + "/api/tableitems/" + addedTableItem.getId())
                .headers(http -> http.setBearerAuth(dummy_JWT))
                .exchange()
                .expectBody(TableItem.class)
                .returnResult()
                .getResponseBody();

        // THEN
        players.removeIf(player -> player.getPlayerName().equals("Anna"));
        TableItem expected = TableItem.builder()
                .id(addedTableItem.getId())
                .bigBlind(6)
                .maxSize(2)
                .tableCards(addedTableItem.getTableCards())
                .players(players)
                .seats(seats)
                .build();

        assertEquals(expected, actual);
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
