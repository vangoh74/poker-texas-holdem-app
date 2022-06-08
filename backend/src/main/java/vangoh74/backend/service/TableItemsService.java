package vangoh74.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vangoh74.backend.dto.TableItemDto;
import vangoh74.backend.model.*;
import vangoh74.backend.repository.TableItemsRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TableItemsService {

    private final TableItemsRepository tableItemsRepository;

    @Autowired
    public TableItemsService(TableItemsRepository tableItemsRepository) {
        this.tableItemsRepository = tableItemsRepository;
    }

    public List<TableItem> getTableItems() {
        return tableItemsRepository.findAll();
    }

    public TableItem addNewTableItem(TableItemDto tableItemDto) {

        TableItem newTableItem = new TableItem();

        DealerService dealerService = new DealerService();
        List<Card> tableCards = new ArrayList<>();
        dealerService.initShuffledDeck();

        // Flop, Turn and River
        for (int i = 0; i < 5; i++) {
            tableCards.add(dealerService.deal());
        }

        Player dummyPlayer = new Player();
        Seat testSeat = new Seat();

        List<Card> playerCards = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        List<Seat> seats = new ArrayList<>();

        playerCards.add(dealerService.deal());
        playerCards.add(dealerService.deal());

        dummyPlayer.setPlayerName("test");
        dummyPlayer.setPlayerChips(1000);
        dummyPlayer.setPlayerImage("Avatar");
        dummyPlayer.setPlayerCards(playerCards);
        testSeat.setPlayerName(dummyPlayer.getPlayerName());
        testSeat.setSeatNumber(1);

        players.add(dummyPlayer);
        seats.add(testSeat);

        newTableItem.setRoundNumber(tableItemDto.getRoundNumber());
        newTableItem.setRoundState(RoundState.PRE_FLOP);
        newTableItem.setBigBlind(tableItemDto.getBigBlind());
        newTableItem.setSmallBlind(tableItemDto.getSmallBlind());
        newTableItem.setMaxSize(tableItemDto.getMaxSize());
        newTableItem.setFreeSeats(tableItemDto.getFreeSeats());
        newTableItem.setTableChips(tableItemDto.getTableChips());
        newTableItem.setTableCards(tableCards);
        newTableItem.setPlayers(players);
        newTableItem.setSeats(seats);

        return tableItemsRepository.insert(newTableItem);
    }

    public TableItem getTableItemsByTableId(String id) {
        return tableItemsRepository.findById(id)
                .orElseThrow( () -> new NoSuchElementException("TableItem with id: " + id + " not found!"));
    }
}
