package com.cricket.project.service;

import com.cricket.project.dto.MatchDto;
import com.cricket.project.model.*;

import java.util.List;

public interface MatchService {
    Match setUpMatch(MatchDto matchDto);

    Match Toss(MatchDto matchDto);

    void playMatch(Match match);

    int playInning(Match match, BatAndBallOrder batAndBallOrder, int inningNumber);

    InningStats createInningStats(int matchId, int battingTeamId, int inningNumber, int runs);

    BatAndBallOrder battingAndBowlingTeamOrder(Match match);

    BattingAndBallingTeam decideBatOrBallTeam(Match match);

    List<Player> orderBuilder(List<Integer> teamPlayers, String batOrBall);
}
