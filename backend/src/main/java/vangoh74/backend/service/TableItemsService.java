package vangoh74.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vangoh74.backend.dto.TableItemDto;
import vangoh74.backend.model.*;
import vangoh74.backend.repository.TableItemsRepository;

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
        int maxSize = tableItemDto.getMaxSize();

        tableItemDto.setMaxSize(maxSize);
        newTableItem.setMaxSize(tableItemDto.getMaxSize());
        newTableItem.setPlayers(tableItemDto.getPlayers());

        return tableItemsRepository.insert(newTableItem);
    }

    public TableItem getTableItemsByTableId(String id) {
        return tableItemsRepository.findById(id)
                .orElseThrow( () -> new NoSuchElementException("TableItem with id: " + id + " not found!"));
    }
}
