package com.cricket.project.service.impl;

import com.cricket.project.enums.PlayerType;
import com.cricket.project.model.MatchScoreCard;
import com.cricket.project.model.PlayerScoreCard;
import com.cricket.project.repository.BallRepository;
import com.cricket.project.repository.PlayerRepository;
import com.cricket.project.service.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreCardServiceImpl implements ScoreCardService {
    @Autowired
    private BallRepository ballRepository;
    @Autowired
    private PlayerRepository playerRepository;
    public PlayerScoreCard playerScoreCard(int matchId, int playerId){
        int playerRuns = ballRepository.playerRunsScoredForMatch(matchId,playerId);
        int playerWickets=ballRepository.playerWicketsTakenForMatch(matchId,playerId);
        String playerName = playerRepository.findPlayerById(playerId).getPlayerName();
        int numberOf4 = ballRepository.playerNumberOf4(matchId,playerId);
        int numberOf6 = ballRepository.playerNumberOf6(matchId,playerId);
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
    public MatchScoreCard matchScoreCard(){
        return MatchScoreCard.builder().build();
    }
}

