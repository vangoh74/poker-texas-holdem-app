package vangoh74.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentRound {

    private int roundNumber;
    private CurrentRoundState currentRoundState;
    private List<CurrentRoundState> validState;
    private CurrentPlayerAction currentPlayerAction;
    private int bigBlind;
    private String playerWithBigBlind;
    private int smallBlind;
    private String playerWithSmallBlind;
    private double tableChips;
    private List<Player> playersInTheGame;
    private List<Card> tableCards;

}
