package com.cricket.project.service;

import com.cricket.project.model.Match;
import com.cricket.project.model.MatchScoreCard;
import com.cricket.project.model.PlayerScoreCard;

public interface ScoreCardService {
    public PlayerScoreCard playerScoreCard(int matchId,int playerId);
    public MatchScoreCard matchScoreCard(int matchId);
}
