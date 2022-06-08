package vangoh74.backend.service;

import org.springframework.stereotype.Service;
import vangoh74.backend.model.Player;
import vangoh74.backend.model.TableItem;
import vangoh74.backend.repository.TableItemsRepository;

import java.security.Principal;
import java.util.NoSuchElementException;

@Service
public class GameService {

    private final TableItemsRepository tableItemsRepo;

    public GameService(TableItemsRepository tableItemsRepo) {
        this.tableItemsRepo = tableItemsRepo;
    }

    public void addNewPlayer(Player newPlayer, String tableId) {

        TableItem tableToBeUpdated = tableItemsRepo.findById(tableId)
                .orElseThrow( () -> new NoSuchElementException(("Table with ID " + tableId + " not found!")));
        //tableItemsRepo.save(newPlayer);
    }
}