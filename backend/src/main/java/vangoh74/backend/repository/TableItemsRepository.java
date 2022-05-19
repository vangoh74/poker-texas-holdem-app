package vangoh74.backend.repository;

import org.springframework.stereotype.Repository;
import vangoh74.backend.model.TableItem;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TableItemsRepository {

    private final List<TableItem> tableItems = new ArrayList<>();
    public List<TableItem> getTableItems() {
        return tableItems;
    }

    public TableItem postNewTableItem(TableItem newTableItem) {
        tableItems.add(newTableItem);
        return newTableItem;
    }
}
