package com.cricket.project.service;

import com.cricket.project.model.Ball;
import com.cricket.project.model.Match;
import com.cricket.project.model.Player;

import java.util.List;

public interface InningService {
    public int playInning(Match match, List<Player> battingOrder, List<Player> bowlingOrder, int battingTeamId, int targetScore);
    public Player getBowlerForOver(List<Player> bowlingOrder, int over);
    public Ball playBall(int ballId, int matchId, int bowlerId, int battingTeamId, Player striker, Player nonStriker);
    public int generateRandomBallStatus();
    public void handleWicket(Ball ball, Player bowler);
    public void handleRuns(Ball ball, Player striker);
    public Player getNextBatsman(List<Player> battingOrder, int wickets);
    public void swapStrikers(Player new_non_striker, Player new_striker);
}
