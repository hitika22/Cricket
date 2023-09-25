package com.cricket.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    private int id;
    private Date date;
    private int team1Id;
    private int team2Id;
    List<Integer> team1PlayersId;
    List<Integer> team2PlayersId;
    private String matchVenue;
    private int overs;
    private int tossWinningTeamId;
    private int battingFirstTeamId;

}
