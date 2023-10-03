package com.cricket.project.service;

import com.cricket.project.model.MatchScoreCard;
import com.cricket.project.model.PlayerScoreCard;

public interface ScoreCardService {
    PlayerScoreCard playerScoreCard(int matchId, int playerId);

    MatchScoreCard matchScoreCard(int matchId);
}
