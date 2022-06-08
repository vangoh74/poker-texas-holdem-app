package vangoh74.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {

    private String name;
    private double amount;
    private List<Card> playerCards;
    private ImageIcon playerAvatar;

}
