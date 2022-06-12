package vangoh74.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vangoh74.backend.dto.TableItemDto;
import vangoh74.backend.model.*;
import vangoh74.backend.repository.TableItemsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TableItemsService {

    private final TableItemsRepository tableItemsRepository;
    private final DealerService dealerService;

    @Autowired
    public TableItemsService(TableItemsRepository tableItemsRepository, DealerService dealerService) {
        this.tableItemsRepository = tableItemsRepository;
        this.dealerService = dealerService;
    }

    public List<TableItem> getTableItems() {
        return tableItemsRepository.findAll();
    }

    public TableItem addNewTableItem(TableItemDto tableItemDto) {

        TableItem newTableItem = new TableItem();
        int maxSize = tableItemDto.getMaxSize();
        Deck deck = dealerService.initShuffledDeck();
        List<Seat> seats = new ArrayList<>();
        List<Card> tableCards = new ArrayList<>();

        // Flop, Turn and River
        for (int i = 0; i < 5; i++) {
            tableCards.add(dealerService.deal(deck));
        }

        tableItemDto.setMaxSize(maxSize);
        tableItemDto.setSeats(seats);
        tableItemDto.setTableCards(tableCards);

        newTableItem.setRoundNumber(tableItemDto.getRoundNumber());
        newTableItem.setRoundState(tableItemDto.getRoundState());
        newTableItem.setBigBlind(tableItemDto.getBigBlind());
        newTableItem.setSmallBlind(tableItemDto.getSmallBlind());
        newTableItem.setMaxSize(tableItemDto.getMaxSize());
        newTableItem.setTableChips(tableItemDto.getTableChips());
        newTableItem.setTableCards(tableItemDto.getTableCards());
        newTableItem.setPlayers(tableItemDto.getPlayers());
        newTableItem.setSeats(tableItemDto.getSeats());

        return tableItemsRepository.insert(newTableItem);
    }

    public TableItem getTableItemsByTableId(String id) {
        return tableItemsRepository.findById(id)
                .orElseThrow( () -> new NoSuchElementException("TableItem with id: " + id + " not found!"));
    }
}
