package vangoh74.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "tableItems")
public class TableItem {

    @Id
    private String id;
    private int maxSize;
    private CurrentRound currentRound;
    private List<Player> players;

}
