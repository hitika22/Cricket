package com.cricket.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MatchDto {
    private int id;
    private int team1Id;
    private int team2Id;
    private String matchVenue;
    private int overs;
}
