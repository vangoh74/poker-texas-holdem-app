package vangoh74.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {

    private String playerName;
    private double playerChips;
    private List<Card> playerCards;
    private String playerImage;

}
