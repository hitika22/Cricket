package com.cricket.project.service;

import com.cricket.project.exception.TeamException;
import com.cricket.project.model.Team;

import java.util.Map;

public interface TeamService {
    public Team createTeam(Map<String,String> teamName) throws TeamException;
    public Team addPlayerToTeam(int playerId,int teamId) throws TeamException;
    public Team removePlayerFromTeam(int playerId,int teamId) throws TeamException;


}
