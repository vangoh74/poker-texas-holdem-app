package vangoh74.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vangoh74.backend.model.Card;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableItemDto {
    private int roundNbr;
    private List<Card> tableCards;
}