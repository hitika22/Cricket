package com.cricket.project.service;

import com.cricket.project.model.Team;

import java.util.Map;

public interface TeamService {
    Team createTeam(Map<String, String> teamName);

    Team addPlayerToTeam(int playerId, int teamId);

    Team removePlayerFromTeam(int playerId, int teamId);
}
