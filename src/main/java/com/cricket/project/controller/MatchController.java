package com.cricket.project.controller;

import com.cricket.project.dto.MatchDto;
import com.cricket.project.model.Match;
import com.cricket.project.model.MatchScoreCard;
import com.cricket.project.service.ScoreCardService;
import com.cricket.project.service.impl.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchServiceImpl matchService;

    @Autowired
    private ScoreCardService scoreCardService;

    @PostMapping
    public ResponseEntity<Match> startMatch(@RequestBody MatchDto matchDto) {
        Match match = matchService.setUpMatch(matchDto);
        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<MatchScoreCard> getMatchScoreCard(@PathVariable int matchId) {
        MatchScoreCard scoreCard = scoreCardService.matchScoreCard(matchId);
        if (scoreCard != null) {
            return new ResponseEntity<>(scoreCard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
