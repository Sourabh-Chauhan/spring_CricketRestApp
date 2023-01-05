package com.chauhan.cricketRestApp.service;

import java.util.List;

import com.chauhan.cricketRestApp.entity.Team;

public interface TeamService {

	List<Team> getAllTeams();

	Team saveTeam(Team team);

	Team updateTeam(long id, Team team);

	Team getTeamById(long id);

	Team getTeamByName(String name);

	Team addPlayerToTeam(long teamId, long playerId) throws Exception;

}
