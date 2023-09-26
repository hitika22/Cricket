package com.cricket.project.service.impl;

import com.cricket.project.enums.PlayerType;
import com.cricket.project.exception.TeamException;
import com.cricket.project.model.Player;
import com.cricket.project.model.Team;
import com.cricket.project.repository.PlayerRepository;
import com.cricket.project.repository.TeamRepository;
import com.cricket.project.service.TeamService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
public class TeamServiceImpl implements TeamService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    private Map<Integer, Integer> exist = new HashMap<>();
    int count=0;
    @Override
    public Team createTeam(Map<String,String> teamName) throws TeamException {
        count++;
        if (count >= 3) {
            throw new TeamException("Cannot create more than 2 teams!");
        }
        List<Integer> playersInTeam = setPlayersInTeam();

        Team team = Team.builder()
                .id(count)
                .teamName(teamName.get("teamName"))
                .teamCaptain(playerRepository.findPlayerById(playersInTeam.get(0)).getPlayerName())
                .teamPlayersId(playersInTeam)
                .totalWins(0)
                .totalMatchesPlayed(0)
                .build();

        teamRepository.saveTeam(team);
        return team;

    }

    public List<Integer> setPlayersInTeam()
    {
        List<Player> bowlers = playerRepository.findPlayersByPlayerType(PlayerType.valueOf("bowler"));
        List<Player> batsmen = playerRepository.findPlayersByPlayerType(PlayerType.valueOf("batsman"));
        List<Player> allRounders = playerRepository.findPlayersByPlayerType(PlayerType.valueOf("allRounder"));
        List<Integer> playersInTeam = new ArrayList<>();

        int numberOfBowlers = 4;
        int numberOfBatsman = 4;
        int numberOfAllRounder = 3;

        setPlayersInTeamByPlayerType(allRounders, playersInTeam, numberOfAllRounder);
        setPlayersInTeamByPlayerType(batsmen, playersInTeam, numberOfBatsman);
        setPlayersInTeamByPlayerType(bowlers, playersInTeam, numberOfBowlers);
        return playersInTeam;
    }

    private void setPlayersInTeamByPlayerType(List<Player> players, List<Integer> playersInTeam, int numberOfPlayerType) {
        for(Player player:players)
        {
            int allRounderId = player.getId();
            if(!exist.containsKey(allRounderId) && numberOfPlayerType>0)
            {
                exist.put(allRounderId,1);
                playersInTeam.add(allRounderId);
                numberOfPlayerType--;
            }
        }
    }

    @Override
    public Team addPlayerToTeam(int playerId, int teamId) throws TeamException {
        Team team = teamRepository.findTeamById(teamId);
        List<Integer> playerIds = team.getTeamPlayersId();
        if(playerIds.contains(playerId))
        {
            throw new TeamException("Player Already Exists In Team");
        }
        if(playerIds.size()==11)
        {
            throw new TeamException("Team Already Have 11 Players!! Can't Add More");
        }
        playerIds.add(playerId);
        team.setTeamPlayersId(playerIds);
        team.setTeamCaptain(playerRepository.findPlayerById(playerIds.get(0)).getPlayerName());
        teamRepository.saveTeam(team);
        return team;
     }

    @Override
    public Team removePlayerFromTeam(int playerId, int teamId) throws TeamException {
        Team team = teamRepository.findTeamById(teamId);
        List<Integer> playerIds = team.getTeamPlayersId();
        if (playerIds.isEmpty()) {
            throw new TeamException("Team is empty!");
        }
        if (!playerIds.contains(playerId)) {
            throw new TeamException("Team does not contain this player! So cannot remove this player..");
        }
        playerIds.removeIf(id -> id == playerId);
        team.setTeamPlayersId(playerIds);
        team.setTeamCaptain(playerRepository.findPlayerById(playerIds.get(0)).getPlayerName());
        teamRepository.saveTeam(team);
        return team;
    }
}
