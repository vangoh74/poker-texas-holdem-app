package vangoh74.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;
import vangoh74.backend.model.*;
import vangoh74.backend.repository.TableItemsRepository;
import vangoh74.backend.security.model.AppUser;
import vangoh74.backend.security.repository.AppUserRepository;

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

    @Disabled
    @Test
    void postToAddPlayerToTableItem_whenPlayerDontExists_shouldAddNewPlayer() {

        // GIVEN
        TableItem tableItem = TableItem.builder()
                .roundNumber(0)
                .bigBlind(6)
                .maxSize(2)
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

        String testPlayerName = "Anton";
        Player player = getTestPlayer(testPlayerName);

        List<Player> players = new ArrayList<>();
        players.add(player);

        TableItem updatedTableItem = TableItem.builder().id(addedTableItem.getId()).players(players).build();

        TableItem actual = webTestClient.post()
                .uri("http://localhost:" + port + "/api/tableitems/{tableId}/players")
                .headers(http -> http.setBearerAuth(dummy_JWT))
                .bodyValue(updatedTableItem)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(TableItem.class)
                .returnResult()
                .getResponseBody();

        // THEN
        TableItem expected = TableItem.builder()
                .id(addedTableItem.getId())
                .roundNumber(0)
                .bigBlind(6)
                .maxSize(2)
                .players(players)
                .build();

        assertEquals(expected, actual);

    }

    @Test
    void postToDeletePlayerFromTableItem() {
    }

    private Player getTestPlayer(String testPlayerName) {
        Card card_1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card_2 = new Card(Rank.JACK, Suit.DIAMONDS);

        List<Card> playerCards = new ArrayList<>();
        playerCards.add(card_1);
        playerCards.add(card_2);
        Player player = Player.builder()
                .playerName(testPlayerName)
                .playerChips(1000)
                .playerCards(playerCards)
                .playerImage("Avatar")
                .build();
        return player;
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