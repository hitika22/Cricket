package com.cricket.project.model;

import com.cricket.project.enums.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerScoreCard {
    private int id;
    private String name;
    private PlayerType type;
    private int numberOf4;
    private int numberOf6;
    private int runScored;
    private int wicketsTaken;
}