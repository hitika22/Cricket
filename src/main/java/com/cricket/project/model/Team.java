package com.cricket.project.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;


@Data
@Builder
public class Team {
    @Id
    private int id;
    @Getter
    private String teamName;
    @Getter
    private String teamCaptain;
    @Getter
    private List<Integer> teamPlayersId;
    @Getter
    private int totalWins;
    @Getter
    private int totalMatchesPlayed;

}
