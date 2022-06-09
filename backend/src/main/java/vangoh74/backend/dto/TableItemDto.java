package vangoh74.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vangoh74.backend.model.Card;
import vangoh74.backend.model.Player;
import vangoh74.backend.model.RoundState;
import vangoh74.backend.model.Seat;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableItemDto {

    private int roundNumber;
    private RoundState roundState;
    private int bigBlind;
    private int smallBlind;
    private int maxSize;
    private int freeSeats;
    private double tableChips;
    private List<Card> tableCards;
    private List<Player> players;
    private List<Seat> seats;

}
