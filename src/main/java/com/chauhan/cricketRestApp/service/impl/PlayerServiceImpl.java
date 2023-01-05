package com.chauhan.cricketRestApp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Direction;

import com.chauhan.cricketRestApp.entity.Player;
import com.chauhan.cricketRestApp.exception.ResourceNotFoundException;
import com.chauhan.cricketRestApp.repository.PlayerRepository;
import com.chauhan.cricketRestApp.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	private PlayerRepository repository;

	// REST CONTROLLERS

	@Override
	public List<Player> getAllPlayers() {
		return repository.findAll();
	}

	@Override
	public List<Player> getAllPlayersSort() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(Direction.DESC, "Age"));
		orders.add(new Order(Direction.ASC, "Name"));

		return repository.findAll(Sort.by(orders));
	}

	@Override
	public Player savePlayer(Player player) {
		return repository.save(player);
	}

	@Override
	public Player getPlayerById(long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Collage", "Id", id));
	}

	@Override
	public Player updatePlayer(long id, Player player) {

		Player existingPlayer = getPlayerById(id);
		System.out.println("Collage" + existingPlayer);
		
		if (!(player.getName() == null || player.getName().isEmpty()))
			existingPlayer.setName(player.getName());
		
		if (player.getCost() > 0)
			existingPlayer.setCost(player.getCost());
		
		if (!(player.getPlayerType() == null || player.getPlayerType().isEmpty()))
			existingPlayer.setPlayerType(player.getPlayerType());
		

		repository.save(existingPlayer);
		return existingPlayer;
	}

}
