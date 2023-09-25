package com.cricket.project.controller;

import com.cricket.project.dto.MatchDto;
import com.cricket.project.model.InningStats;
import com.cricket.project.model.Match;
import com.cricket.project.model.MatchScoreCard;
import com.cricket.project.model.PlayerScoreCard;
import com.cricket.project.service.InningService;
import com.cricket.project.service.ScoreCardService;
import com.cricket.project.service.impl.MatchServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class MatchController {
    @Autowired
    private MatchServiceImpl matchService;

    @Autowired
    private ScoreCardService scoreCardService;
    @PostMapping("/startMatch")
    public Match startMatch(@RequestBody MatchDto document) {
         return matchService.setUpMatch(document);
    }

    @GetMapping("/scoreCardForAMatch")
    public MatchScoreCard PlayerScoreCardForAMatch(
            @RequestParam("matchId") int matchId) {
        return scoreCardService.matchScoreCard(matchId);
    }





}
