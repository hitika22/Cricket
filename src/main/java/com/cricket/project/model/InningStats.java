package com.cricket.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.stereotype.Component;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@CompoundIndexes({
        @CompoundIndex(name = "match_battingTeam_unique", def = "{'matchId': 1, 'battingTeamId': 1}", unique = true)
})
public class InningStats {
    private int matchId;
    private int battingTeamId;
    private int inningNumber;
    private int runs;
    private int wickets;
}
