package vangoh74.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vangoh74.backend.model.Player;
import vangoh74.backend.model.TableItem;

@Repository
public interface TableItemsRepository extends MongoRepository<TableItem, String> {
}
