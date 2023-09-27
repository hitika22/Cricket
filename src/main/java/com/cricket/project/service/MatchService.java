package com.cricket.project.service;

import com.cricket.project.dto.MatchDto;
import com.cricket.project.model.*;

import java.util.List;

public interface MatchService {
    public Match setUpMatch(MatchDto matchDto);
    public Match Toss(MatchDto matchDto);
    public void playMatch(Match match);
    public int playInning(Match match, BatAndBallOrder batAndBallOrder, int inningNumber);
    public InningStats createInningStats(int matchId, int battingTeamId, int inningNumber, int runs);
    public BatAndBallOrder battingAndBowlingTeamOrder(Match match);
    public BattingAndBallingTeam decideBatOrBallTeam(Match match);
    public List<Player> orderBuilder(List<Integer> teamPlayers, String batOrBall);

}
