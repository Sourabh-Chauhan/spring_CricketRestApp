package com.chauhan.cricketRestApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chauhan.cricketRestApp.entity.Team;
import com.chauhan.cricketRestApp.exception.ControllerLevelException;
import com.chauhan.cricketRestApp.exception.InsufficientFundException;
import com.chauhan.cricketRestApp.exception.ResourceException;
import com.chauhan.cricketRestApp.exception.ResourceNotFoundException;
import com.chauhan.cricketRestApp.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {
//	final String ResourceNotFoundException= "com.chauhan.cricketRestApp.exception.ResourceNotFoundException";

	@Autowired
	private TeamService service;

	@GetMapping
	public ResponseEntity<List<Team>> getAllTeams() {
		List<Team> AllTeams = null;
		try {
			AllTeams = service.getAllTeams();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerLevelException("Team", "Service", e.getMessage());
		}

		return new ResponseEntity<List<Team>>(AllTeams, HttpStatus.CREATED);
	}

	@PostMapping()
	public ResponseEntity<Team> saveTeam(@RequestBody Team team) {
		Team saveTeam = null;
		try {
			saveTeam = service.saveTeam(team);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerLevelException("Team", "Service", e.getMessage());
		}
		return new ResponseEntity<Team>(saveTeam, HttpStatus.CREATED);

	}

	@PutMapping("{id}")
	public ResponseEntity<Team> updateTeam(@PathVariable("id") long id, @RequestBody Team team) {
		Team updatedTeam = null;
		try {
			updatedTeam = service.updateTeam(id, team);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerLevelException("Team", "Service", e.getMessage());
		}
		return new ResponseEntity<Team>(updatedTeam, HttpStatus.OK);
	}

	@GetMapping("{name}")
	public ResponseEntity<?> getTeamByName(@PathVariable("name") String name) {
		ResponseEntity<Team> res = null;
		try {
			res = new ResponseEntity<Team>(service.getTeamByName(name), HttpStatus.OK);
		}
//		catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getClass());
//			if(e.getClass().getName().equalsIgnoreCase(ResourceNotFoundException)) {
//				 return ResponseEntity
//				            .status(HttpStatus.NOT_FOUND)
//				            .body("Team NOT FOUND Message");
//				
//			}
//		}
		catch (ResourceNotFoundException e) {
			e.printStackTrace();
			throw new ControllerLevelException("Team", "Service", e.getMessage());

		}

		return res;
	}

	@PutMapping("{TeamId}/add/{PlayerId}")
	public ResponseEntity<?> updateTeam(@PathVariable("TeamId") long teamId,
			@PathVariable("PlayerId") long playerId) {
		ResponseEntity<Team> res = null;
		
			try {
				res = new ResponseEntity<Team>(service.addPlayerToTeam(teamId, playerId), HttpStatus.OK);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
		
		return res;
	}

}
