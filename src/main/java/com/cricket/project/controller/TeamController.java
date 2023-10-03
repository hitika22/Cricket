package com.cricket.project.controller;

import com.cricket.project.model.Team;
import com.cricket.project.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamServiceImpl teamService;

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Map<String, String> teamName) {
        return new ResponseEntity<Team>(teamService.createTeam(teamName), HttpStatus.CREATED);
    }

    @PutMapping("/{teamId}/players/{playerId}")
    public ResponseEntity<Team> addPlayerToTeam(@PathVariable int teamId, @PathVariable int playerId) {
        return new ResponseEntity<Team>(teamService.addPlayerToTeam(playerId, teamId), HttpStatus.OK);
    }

    @DeleteMapping("/{teamId}/players/{playerId}")
    public ResponseEntity<Team> removePlayerFromTeam(@PathVariable int teamId, @PathVariable int playerId) {
        return new ResponseEntity<Team>(teamService.removePlayerFromTeam(playerId, teamId), HttpStatus.OK);
    }
}
