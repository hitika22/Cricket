package com.cricket.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name = "match_inning_ball", def = "{'matchId': 1, 'battingTeamId': 1, 'ballId': 1}", unique = true)
})
@Document
public class Ball {
    private int matchId;
    private int battingTeamId;
    private int ballId;
    private int strikerId;
    private int nonStrikerId;
    private int bowlerId;
    private int runs;
    private int wickets;
}
