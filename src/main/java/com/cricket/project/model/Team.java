package com.cricket.project.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
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
