package com.cricket.project.model;


import com.cricket.project.enums.PlayerType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Player {
    @Id
    private int id;
    private String playerName;
    private PlayerType playerType;
    private int age;
    private int totalRunScored;
    private int totalWicketsTaken;
    private int totalMatchesPlayed;
}
