package com.cricket.project.service.impl;

import com.cricket.project.exception.PlayerException;
import com.cricket.project.model.Player;
import com.cricket.project.repository.PlayerRepository;
import com.cricket.project.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public String addPlayer(Player player) {
        Player existingPlayer = playerRepository.findPlayerById(player.getId());
        if (existingPlayer != null) {
            return "This Player Id Is Already Present!! ";
        }

        playerRepository.savePlayer(player);
        return "Player Added Successfully!!";
    }

    @Override
    public Player getPlayerById(int playerId) throws PlayerException {
        Player player = playerRepository.findPlayerById(playerId);
        if (player != null) {
            return player;
        }

        throw new PlayerException("Player Not Found!!");
    }

    @Override
    public String removePlayer(int playerId) throws PlayerException {
        String result = playerRepository.removePlayer(playerId);

        if (result == null) {
            throw new PlayerException("Player Not Found!!");
        }

        return result;
    }

    @Override
    public List<Player> getPlayerList() throws PlayerException {
        List<Player> playerList = playerRepository.findAllPlayers();

        if (playerList.isEmpty()) {
            throw new PlayerException("No players found in the player list.");
        }

        return playerList;
    }

}


