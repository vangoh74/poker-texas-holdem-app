package vangoh74.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vangoh74.backend.model.Player;
import vangoh74.backend.service.GameService;

@RestController
@RequestMapping("/api/tableitems")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/{tableId}/players")
    public void postToAddPlayerToTableItem(@RequestBody Player newPlayer, @PathVariable String tableId) {
        gameService.addPlayerToTableItem(newPlayer, tableId);
    }

    @PostMapping("/{tableId}/players/{playerName}")
    public void postToDeletePlayerFromTableItem(@PathVariable String tableId, @PathVariable String playerName) {
        gameService.deletePlayerFromTableItem(tableId, playerName);
    }
}
