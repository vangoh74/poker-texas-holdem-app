package vangoh74.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Builder
@Document(collection = "tableItems")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableItem {

    @Id
    private String id;
    private int roundNumber;
    private RoundState roundState;
    private int bigBlind;
    private int smallBlind;
    private int maxSize;
    private int freeSeats;
    private double tableChips;
    private List<Card> tableCards;
    private List<Player> players;
    private Map<String, Integer> seats;

}
