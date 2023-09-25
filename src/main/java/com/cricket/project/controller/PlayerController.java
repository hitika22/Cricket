package com.cricket.project.controller;

import com.cricket.project.model.Player;
import com.cricket.project.model.PlayerScoreCard;
import com.cricket.project.service.impl.PlayerServiceImpl;
import com.cricket.project.service.impl.ScoreCardServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PlayerController {
    @Autowired
    private PlayerServiceImpl playerService;
    @Autowired
    private ScoreCardServiceImpl scoreCardService;

    @PostMapping("/addPlayer")
    public String addPlayer(@RequestBody Player player) {
        return playerService.addPlayer(player);
    }

    @GetMapping("/players")
    public List<Player> getPlayerList() {
        return playerService.getPlayerList();
    }

    @GetMapping("/player/{id}")
    public Player getPlayerById(@PathVariable("id") int id) {
        return playerService.getPlayerById(id);
    }

    @DeleteMapping("/removePlayer/{id}")
    public String removePlayer(@PathVariable("id") int id) {
        return playerService.removePlayer(id);
    }

    @GetMapping("/playerScoreCardForAMatch")
    public PlayerScoreCard PlayerScoreCardForAMatch(
            @RequestParam("matchId") int matchId,
            @RequestParam("playerId") int playerId) {
        return scoreCardService.playerScoreCard(matchId,playerId);
    }
}