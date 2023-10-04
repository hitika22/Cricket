package com.cricket.project.service.impl;

import com.cricket.project.enums.PlayerType;
import com.cricket.project.model.InningStats;
import com.cricket.project.model.Match;
import com.cricket.project.model.MatchScoreCard;
import com.cricket.project.model.PlayerScoreCard;
import com.cricket.project.repository.*;
import com.cricket.project.service.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreCardServiceImpl implements ScoreCardService {
    @Autowired
    private BallRepository ballRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private InningRepository inningRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private TeamRepository teamRepository;

    public PlayerScoreCard playerScoreCard(int matchId, int playerId) {
        int playerRuns = ballRepository.playerRunsScoredForMatch(matchId, playerId);
        int playerWickets = ballRepository.playerWicketsTakenForMatch(matchId, playerId);
        String playerName = playerRepository.findPlayerById(playerId).getPlayerName();
        int numberOf4 = ballRepository.playerNumberOf4(matchId, playerId);
        int numberOf6 = ballRepository.playerNumberOf6(matchId, playerId);
        PlayerType playerType = playerRepository.findPlayerById(playerId).getPlayerType();

        return PlayerScoreCard.builder()
                .id(playerId)
                .name(playerName)
                .type(playerType)
                .numberOf4(numberOf4)
                .numberOf6(numberOf6)
                .runScored(playerRuns)
                .wicketsTaken(playerWickets)
                .build();
    }

    public MatchScoreCard matchScoreCard(int matchId) {
        Match match = matchRepository.findById(matchId);
        int teamId1 = match.getTeam1Id();
        int teamId2 = match.getTeam2Id();
        InningStats inningStats1 = inningRepository.findInningByMatchId(matchId, teamId1);
        InningStats inningStats2 = inningRepository.findInningByMatchId(matchId, teamId2);
        int winnerId;
        if (inningStats1.getRuns() > inningStats2.getRuns()) {
            winnerId = inningStats1.getBattingTeamId();
        } else {
            winnerId = inningStats2.getBattingTeamId();
        }
        String winningTeam = teamRepository.findTeamById(winnerId).getTeamName();
        List<Integer> team1PlayerIds = teamRepository.findTeamById(teamId1).getTeamPlayersId();
        List<Integer> team2PlayerIds = teamRepository.findTeamById(teamId2).getTeamPlayersId();

        List<PlayerScoreCard> team1PlayerScoreCard = new ArrayList<>();
        List<PlayerScoreCard> team2PlayerScoreCard = new ArrayList<>();

        for (int id : team1PlayerIds) {
            team1PlayerScoreCard.add(playerScoreCard(matchId, id));
        }
        for (int id : team2PlayerIds) {
            team2PlayerScoreCard.add(playerScoreCard(matchId, id));
        }
        return MatchScoreCard.builder()
                .matchId(matchId)
                .team1Runs(inningStats1.getRuns())
                .team1Wickets(inningStats1.getWickets())
                .team2Runs(inningStats2.getRuns())
                .team2Wickets(inningStats2.getWickets())
                .totalOvers(match.getOvers())
                .winner(winningTeam)
                .team1PlayerScoreCard(team1PlayerScoreCard)
                .team2PlayerScoreCard(team2PlayerScoreCard)
                .build();
    }
}
