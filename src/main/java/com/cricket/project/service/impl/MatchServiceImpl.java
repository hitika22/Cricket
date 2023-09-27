package com.cricket.project.service.impl;

import com.cricket.project.dto.MatchDto;
import com.cricket.project.model.*;
import com.cricket.project.repository.*;
import com.cricket.project.service.MatchService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchServiceImpl implements MatchService {
    @Autowired
    private TeamServiceImpl teamService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private BallRepository ballRepository;
    @Autowired
    private InningServiceImpl inningService;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private InningRepository inningRepository;

    @Override
    public Match setUpMatch(MatchDto matchDto) {
        Match match = Toss(matchDto);
        List<Integer> team1PlayerIds = teamRepository.findTeamById(matchDto.getTeam1Id()).getTeamPlayersId();
        List<Integer> team2PlayerIds = teamRepository.findTeamById(matchDto.getTeam2Id()).getTeamPlayersId();
        match.setTeam1PlayersId(team1PlayerIds);
        match.setTeam2PlayersId(team2PlayerIds);
        matchRepository.save(match);
        playMatch(match);
        return match;
    }

    public Match Toss(MatchDto matchDto) {
        Random random = new Random();
        int toss = random.nextInt(4);
        int tossWinningTeamId = 0;
        int battingFirstTeamId = 0;
        switch (toss) {
            case 0:
                tossWinningTeamId = matchDto.getTeam1Id();
                battingFirstTeamId = matchDto.getTeam1Id();
                break;
            case 1:
                tossWinningTeamId = matchDto.getTeam1Id();
                battingFirstTeamId = matchDto.getTeam2Id();
                break;
            case 2:
                tossWinningTeamId = matchDto.getTeam2Id();
                battingFirstTeamId = matchDto.getTeam1Id();
                break;
            case 3:
                tossWinningTeamId = matchDto.getTeam2Id();
                battingFirstTeamId = matchDto.getTeam2Id();
                break;
        }

        return Match.builder()
                .id(matchDto.getId())
                .date(new Date())
                .team1Id(matchDto.getTeam1Id())
                .team2Id(matchDto.getTeam2Id())
                .matchVenue(matchDto.getMatchVenue())
                .overs(matchDto.getOvers())
                .tossWinningTeamId(tossWinningTeamId)
                .battingFirstTeamId(battingFirstTeamId)
                .build();
    }

    public void playMatch(Match match) {
        BatAndBallOrder batAndBallOrder = battingAndBowlingTeamOrder(match);
        int firstInningScore = playInning(match, batAndBallOrder, 1);
        InningStats inningStats1 = createInningStats(match.getId(), batAndBallOrder.getFirstInningBattingTeamId(), 1, firstInningScore);
        inningRepository.saveInning(inningStats1);
        int secondInningScore = playInning(match, batAndBallOrder, 2);
        InningStats inningStats2 = createInningStats(match.getId(), batAndBallOrder.getSecondInningBattingTeamId(), 2, secondInningScore);
        inningRepository.saveInning(inningStats2);
    }
    public int playInning(Match match, BatAndBallOrder batAndBallOrder, int inningNumber) {
        List<Player> battingOrder = inningNumber == 1 ? batAndBallOrder.getFirstInningBattingOrder() : batAndBallOrder.getSecondInningBattingOrder();
        List<Player> bowlingOrder = inningNumber == 1 ? batAndBallOrder.getFirstInningBowlingOrder() : batAndBallOrder.getSecondInningBowlingOrder();
        int battingTeamId = inningNumber == 1 ? batAndBallOrder.getFirstInningBattingTeamId() : batAndBallOrder.getSecondInningBattingTeamId();
        return inningService.playInning(match, battingOrder, bowlingOrder, battingTeamId, 9999);
    }

    public InningStats createInningStats(int matchId, int battingTeamId, int inningNumber, int runs) {
        int wickets = ballRepository.inningWickets(matchId, battingTeamId);
        return InningStats.builder()
                .matchId(matchId)
                .battingTeamId(battingTeamId)
                .inningNumber(inningNumber)
                .runs(runs)
                .wickets(wickets)
                .build();
    }

    public BatAndBallOrder battingAndBowlingTeamOrder(Match match) {
        List<Player> firstInningBattingOrder;
        List<Player> firstInningBowlingOrder;
        List<Player> secondInningBattingOrder;
        List<Player> secondInningBowlingOrder;

        BattingAndBallingTeam battingAndBallingTeam = decideBatOrBallTeam(match);

        firstInningBattingOrder = orderBuilder(battingAndBallingTeam.getBattingTeamPlayers(), "batting");
        secondInningBowlingOrder = orderBuilder(battingAndBallingTeam.getBattingTeamPlayers(), "bowling");
        firstInningBowlingOrder = orderBuilder(battingAndBallingTeam.getBowlingTeamPlayers(), "bowling");
        secondInningBattingOrder = orderBuilder(battingAndBallingTeam.getBowlingTeamPlayers(), "batting");

        return BatAndBallOrder.builder()
                .firstInningBattingOrder(firstInningBattingOrder)
                .firstInningBowlingOrder(firstInningBowlingOrder)
                .secondInningBattingOrder(secondInningBattingOrder)
                .secondInningBowlingOrder(secondInningBowlingOrder)
                .firstInningBattingTeamId(battingAndBallingTeam.getFirstInningBattingTeamId())
                .secondInningBattingTeamId(battingAndBallingTeam.getSecondInningBattingTeamId())
                .build();
    }

    public BattingAndBallingTeam decideBatOrBallTeam(Match match) {
        int firstInningBattingTeamId;
        int secondInningBattingTeamId;
        Team battingTeam = teamRepository.findTeamById(match.getBattingFirstTeamId());
        List<Integer> battingTeamPlayers = battingTeam.getTeamPlayersId();
        firstInningBattingTeamId = battingTeam.getId();

        Team bowlingTeam;
        List<Integer> bowlingTeamPlayers;

        if (battingTeam.getId() == match.getTeam1Id()) {
            bowlingTeam = teamRepository.findTeamById(match.getTeam2Id());
            secondInningBattingTeamId = match.getTeam2Id();
        } else {
            bowlingTeam = teamRepository.findTeamById(match.getTeam1Id());
            secondInningBattingTeamId = match.getTeam1Id();
        }
        bowlingTeamPlayers = bowlingTeam.getTeamPlayersId();

        return BattingAndBallingTeam.builder()
                .firstInningBattingTeamId(firstInningBattingTeamId)
                .secondInningBattingTeamId(secondInningBattingTeamId)
                .battingTeamPlayers(battingTeamPlayers)
                .bowlingTeamPlayers(bowlingTeamPlayers).build();
    }

    public List<Player> orderBuilder(List<Integer> teamPlayers, String batOrBall) {
        List<Player> players;
        if (Objects.equals(batOrBall, "batting")) {
            players = playerRepository.findListOfPlayersById(teamPlayers);
        } else {
            players = playerRepository.findListOfBowlersById(teamPlayers);
        }
        return players;
    }

}