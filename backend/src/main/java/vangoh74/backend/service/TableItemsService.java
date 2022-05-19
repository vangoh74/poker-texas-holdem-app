package vangoh74.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vangoh74.backend.model.Card;
import vangoh74.backend.model.TableItem;
import vangoh74.backend.repository.TableItemsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TableItemsService {

    private final TableItemsRepository tableItemsRepository;
    private final IdService idService;

    @Autowired
    public TableItemsService(TableItemsRepository tableItemsRepository, IdService idService) {
        this.tableItemsRepository = tableItemsRepository;
        this.idService = idService;
    }

    public List<TableItem> getTableItems() {
        return tableItemsRepository.getTableItems();
    }

    public TableItem postNewTableItem(TableItem newTableItem) {
        String randomId = idService.generateId();
        newTableItem.setId(randomId);

        DeckService deckService = new DeckService();
        List<Optional<Card>> fiveCards = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            fiveCards.add(deckService.deal());
        }
        
        newTableItem.setTableCards(fiveCards);
        return tableItemsRepository.postNewTableItem(newTableItem);
    }
}
