package vangoh74.backend.service;

import org.springframework.stereotype.Service;
import vangoh74.backend.apiExceptions.PlayerAlreadyAtTableException;
import vangoh74.backend.model.Player;
import vangoh74.backend.model.TableItem;
import vangoh74.backend.repository.TableItemsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

@Service
public class GameService {

    private final TableItemsRepository tableItemsRepo;

    public GameService(TableItemsRepository tableItemsRepo) {
        this.tableItemsRepo = tableItemsRepo;
    }

    public TableItem addPlayerToTableItem(Player newPlayer, String tableId) throws PlayerAlreadyAtTableException {

        TableItem tableItemToBeUpdated = getTableItemToBeUpdated(tableId);
        List<Player> players =  tableItemToBeUpdated.getPlayers();
        if( players == null) {
            players = new ArrayList<>();
            tableItemToBeUpdated.setPlayers(players);
        }

        Predicate<Player> predicate = player -> player.getPlayerName().equals(newPlayer.getPlayerName());

        if((players.stream().noneMatch(predicate)) && (players.size() < tableItemToBeUpdated.getMaxSize())) {
            players.add(newPlayer);
            return tableItemsRepo.save(tableItemToBeUpdated);
        } else {
            throw new PlayerAlreadyAtTableException();
        }
    }

    public TableItem deletePlayerFromTableItem(String tableId, String playerName) {

        TableItem tableItemToBeUpdated = getTableItemToBeUpdated(tableId);
        tableItemToBeUpdated.getPlayers().removeIf(player -> player.getPlayerName().equals(playerName));
        return tableItemsRepo.save(tableItemToBeUpdated);
    }

    public TableItem getTableItemToBeUpdated(String tableId) {
        return tableItemsRepo.findById(tableId)
                .orElseThrow( () -> new NoSuchElementException(("Table with ID " + tableId + " not found!")));
    }
}
