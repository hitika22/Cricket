package com.cricket.project.controller;

import com.cricket.project.exception.PlayerException;
import com.cricket.project.model.Player;
import com.cricket.project.model.PlayerScoreCard;
import com.cricket.project.service.impl.PlayerServiceImpl;
import com.cricket.project.service.impl.ScoreCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerServiceImpl playerService;

    @Autowired
    private ScoreCardServiceImpl scoreCardService;

    @PostMapping
    public ResponseEntity<String> addPlayer(@RequestBody Player player) {
        String result = playerService.addPlayer(player);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getPlayerList() {
        List<Player> players = playerService.getPlayerList();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") int id) {
        try {
            Player player = playerService.getPlayerById(id);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (PlayerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePlayer(@PathVariable("id") int id) {
        String result = playerService.removePlayer(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PlayerScoreCard> playerScoreCard(@RequestParam("matchId") int matchId, @RequestParam("playerId") int playerId) {
        PlayerScoreCard scoreCard = scoreCardService.playerScoreCard(matchId, playerId);
        return new ResponseEntity<>(scoreCard, HttpStatus.OK);
    }
}