package vangoh74.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vangoh74.backend.model.Player;
import vangoh74.backend.model.TableItem;
import vangoh74.backend.service.GameService;

import java.security.Principal;

@RestController
@RequestMapping("/api/tableitems")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/{id}/players")
    public void postNewPlayer(@RequestBody Player newPlayer, String tableId) {
        gameService.addNewPlayer(newPlayer, tableId);
    }
}
