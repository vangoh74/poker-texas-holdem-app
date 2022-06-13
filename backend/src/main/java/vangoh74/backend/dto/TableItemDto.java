package vangoh74.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vangoh74.backend.model.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableItemDto {

    private int maxSize;
    private CurrentRound currentRound;
    private List<Player> players;

}
