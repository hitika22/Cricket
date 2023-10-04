package com.cricket.project.service;

import com.cricket.project.model.Ball;
import com.cricket.project.model.Match;
import com.cricket.project.model.Player;

import java.util.List;

public interface InningService {
    int playInning(Match match, List<Player> battingOrder, List<Player> bowlingOrder, int battingTeamId, int targetScore);

    Player getBowlerForOver(List<Player> bowlingOrder, int over);

    Ball playBall(int ballId, int matchId, int bowlerId, int battingTeamId, Player striker, Player nonStriker);

    int generateRandomBallStatus();

    void handleWicket(Ball ball, Player bowler);

    void handleRuns(Ball ball, Player striker);

    Player getNextBatsman(List<Player> battingOrder, int wickets);

    void swapStrikers(Player new_non_striker, Player new_striker);
}
