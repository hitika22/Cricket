package com.cricket.project.controller;

import com.cricket.project.dto.PlayerToTeamDto;
import com.cricket.project.exception.TeamException;
import com.cricket.project.model.Team;
import com.cricket.project.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamServiceImpl teamService;

    @PostMapping("")
    public ResponseEntity< Team> createTeam(@RequestBody Map<String,String> teamName) throws TeamException {
        return new ResponseEntity<Team>(teamService.createTeam(teamName), HttpStatus.OK);
    }

    @PostMapping("/addPlayerToTeam")
    public ResponseEntity<Team> addPlayerToTeam(@RequestBody PlayerToTeamDto playerToTeamDto) throws TeamException {
        return new ResponseEntity<Team>(teamService.addPlayerToTeam(playerToTeamDto.getPlayerId(),playerToTeamDto.getTeamId()),HttpStatus.OK);
    }

    @PostMapping("/removePlayerFromTeam")
    public ResponseEntity<Team> removePlayerFromTeam(@RequestBody PlayerToTeamDto playerToTeamDto) throws TeamException {
        return new ResponseEntity<Team>(teamService.removePlayerFromTeam(playerToTeamDto.getPlayerId(),playerToTeamDto.getTeamId()),HttpStatus.OK);
    }


}
