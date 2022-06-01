package vangoh74.backend.security.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;
import vangoh74.backend.security.model.AppUser;
import vangoh74.backend.security.repository.AppUserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppUserAuthControllerTest {

    @Value("${vangoh.texas.holdem.app.jwt.secret}")
    private String JWT_SECRET;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        appUserRepository.deleteAll();
    }

    @Test
    void login_withValidCredentials_returnValidJwt() {
        // GIVEN
        createDummyUserInDataBase();

        // WHEN
        String jwt = webTestClient.post()
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

        // THEN
        Claims claims= Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(jwt)
                .getBody();
        assertEquals("test_username", claims.getSubject());
    }

    @Test
    void login_withWrongCredentials_returnForbiddenError() {
        // GIVEN
        createDummyUserInDataBase();

        // WHEN // THEN
        webTestClient.post()
                .uri("/auth/login")
                .bodyValue(AppUser.builder()
                        .username("test_username")
                        .password("WRONG_PASSWORD")
                        .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.FORBIDDEN);
    }

    private void createDummyUserInDataBase() {
        String hashedPassword = passwordEncoder.encode("test_password");
        AppUser dummyUser = AppUser.builder()
                .username("test_username")
                .password(hashedPassword)
                .build();
        appUserRepository.save(dummyUser);
    }
}