package com.chauhan.cricketRestApp.service;

import java.util.List;

import com.chauhan.cricketRestApp.entity.Player;

public interface PlayerService {

	List<Player> getAllPlayers();

	List<Player> getAllPlayersSort();

	Player savePlayer(Player player);

	Player updatePlayer(long id, Player player);

	Player getPlayerById(long id);


}
