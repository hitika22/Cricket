package com.cricket.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchScoreCard {
    private int matchId;
    private String winner;
    private int team1Runs;
    private int team1Wickets;
    private int team2Runs;
    private int team2Wickets;
    private int totalOvers;
    private List<PlayerScoreCard> team1PlayerScoreCard;
    private List<PlayerScoreCard> team2PlayerScoreCard;
}
