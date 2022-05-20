package vangoh74.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vangoh74.backend.dto.TableItemDto;
import vangoh74.backend.model.TableItem;
import vangoh74.backend.service.TableItemsService;

import java.util.List;

@RestController
@RequestMapping("/api/tableitems")
public class TableItemsController {

    private final TableItemsService tableItemsService;
    @Autowired
    public TableItemsController(TableItemsService tableItemsService) {
        this.tableItemsService = tableItemsService;
    }

    @GetMapping
    public List<TableItem> getTableItems () {
        return tableItemsService.getTableItems();
    }

    @PostMapping
    public TableItem postNewTableItem(@RequestBody TableItemDto tableItemDto) {
        return tableItemsService.addNewTableItem(tableItemDto);
    }
}
