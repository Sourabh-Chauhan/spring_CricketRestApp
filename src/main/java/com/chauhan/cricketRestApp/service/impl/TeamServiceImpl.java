package com.chauhan.cricketRestApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauhan.cricketRestApp.entity.Player;
import com.chauhan.cricketRestApp.entity.Team;
import com.chauhan.cricketRestApp.exception.ControllerLevelException;
import com.chauhan.cricketRestApp.exception.InsufficientFundException;
import com.chauhan.cricketRestApp.exception.ResourceException;
import com.chauhan.cricketRestApp.exception.ResourceNotFoundException;
import com.chauhan.cricketRestApp.repository.TeamRepository;
import com.chauhan.cricketRestApp.service.PlayerService;
import com.chauhan.cricketRestApp.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository repository;

	@Autowired
	private PlayerService playerService;

	// REST CONTROLLERS

	@Override
	public List<Team> getAllTeams() {
		return repository.findAll();
	}

	@Override
	public Team saveTeam(Team team) {
		return repository.save(team);
	}

	@Override
	public Team getTeamById(long id) throws ResourceNotFoundException {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team", "Id", id));
	}

	@Override
	public Team updateTeam(long id, Team team) {

		Team existingTeam = getTeamById(id);
		System.out.println("Team" + existingTeam);

		if (!(team.getName() == null || team.getName().isEmpty()))
			existingTeam.setName(team.getName());

		if (team.getTeamBudget() > 0)
			existingTeam.setTeamBudget(team.getTeamBudget());

//		for (Player player : team.getPlayers()) {
//			System.out.println(player);
//		}

		repository.save(existingTeam);
		return existingTeam;
	}

	@Override
	public Team getTeamByName(String name) throws ResourceNotFoundException {

		Team team = repository.findByName(name);

		if (team == null) {
			throw new ResourceNotFoundException("Team", "Name", name);
		}

		return team;
	}

	@Override
	public Team addPlayerToTeam(long teamId, long playerId)
			throws ResourceNotFoundException, ResourceException, InsufficientFundException {
		Team existingTeam = null;
		Player existingPlayer = null;
		try {
			existingTeam = getTeamById(teamId);
			existingPlayer = playerService.getPlayerById(playerId);

			double sum = 0;
			List<Player> playerList = existingTeam.getPlayers();
			// System.out.println(playerList);

			if (playerList.size() > 10)
				throw new ResourceException(" Team already Full ", existingTeam.getName(), existingPlayer.getName());

			for (Player player : playerList) {
				if (player.equals(existingPlayer))
					throw new ResourceException("in Team already ", existingTeam.getName(), existingPlayer.getName());

				sum += player.getCost();
			}

			if (existingPlayer.getCost() > (existingTeam.getTeamBudget() - sum)) {
				throw new InsufficientFundException(
						"Team TeamBudget " + (existingPlayer.getCost() - existingTeam.getTeamBudget() + sum),
						existingTeam.getName(), existingPlayer.getName() + " " + existingPlayer.getCost());
			}

			existingTeam.getPlayers().add(existingPlayer);
			repository.save(existingTeam);

		} catch (ResourceNotFoundException | ResourceException | InsufficientFundException  e) {
			
			throw new ControllerLevelException("Team", "Service", e.getMessage());
		}

		return existingTeam;
	}

}
