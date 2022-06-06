package vangoh74.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vangoh74.backend.dto.TableItemDto;
import vangoh74.backend.model.Card;
import vangoh74.backend.model.TableItem;
import vangoh74.backend.repository.TableItemsRepository;

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
        tableItemDto.setTableCards(tableCards);

        newTableItem.setBigBlind(tableItemDto.getBigBlind());
        newTableItem.setTableSize(tableItemDto.getTableSize());
        newTableItem.setFreeSeats(tableItemDto.getFreeSeats());
        newTableItem.setRoundNumber(tableItemDto.getRoundNumber());
        newTableItem.setTableCards(tableItemDto.getTableCards());

        return tableItemsRepository.insert(newTableItem);
    }

    public TableItem getTableItemsByTableId(String id) {
        return tableItemsRepository.findById(id)
                .orElseThrow( () -> new NoSuchElementException("TableItem with id: " + id + " not found!"));
    }
}
