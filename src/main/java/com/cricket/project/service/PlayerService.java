package com.cricket.project.service;

import com.cricket.project.exception.PlayerException;
import com.cricket.project.model.Player;

import java.util.List;

public interface PlayerService {
    String addPlayer(Player player);

    Player getPlayerById(int playerId) throws PlayerException;

    String removePlayer(int playerId) throws PlayerException;

    List<Player> getPlayerList() throws PlayerException;
}
