package vangoh74.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vangoh74.backend.apiExceptions.PlayerAlreadyAtTableException;
import vangoh74.backend.model.Player;
import vangoh74.backend.model.TableItem;
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
    public TableItem postToAddPlayerToTableItem(@RequestBody Player newPlayer, @PathVariable String tableId) throws PlayerAlreadyAtTableException {
        return gameService.addPlayerToTableItem(newPlayer, tableId);
    }

    @PostMapping("/{tableId}/players/{playerName}")
    public TableItem postToDeletePlayerFromTableItem(@PathVariable String tableId, @PathVariable String playerName) {
        return gameService.deletePlayerFromTableItem(tableId, playerName);
    }
}
