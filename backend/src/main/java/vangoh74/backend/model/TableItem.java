package vangoh74.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Builder
@Document(collection = "tableItems")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableItem {

    @Id
    private String id;
    private double bigBlind;
    private int tableSize;
    private int freeSeats;
    private int roundNumber;
    private List<Card> tableCards;

}
