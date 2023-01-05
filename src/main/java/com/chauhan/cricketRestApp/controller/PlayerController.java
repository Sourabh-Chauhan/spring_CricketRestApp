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

import com.chauhan.cricketRestApp.entity.Player;
import com.chauhan.cricketRestApp.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

	final String ResourceNotFoundException = "com.chauhan.cricketRestApp.exception.ResourceNotFoundException";

	@Autowired
	private PlayerService service;

	@GetMapping
	public List<Player> getAllPlayers() {
		return service.getAllPlayers();
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getPlayerByID(@PathVariable("id") long id) {
		ResponseEntity<Player> res = null;
		try {
			res = new ResponseEntity<Player>(service.getPlayerById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e.getClass());
			if (e.getClass().getName().equalsIgnoreCase(ResourceNotFoundException)) {
				// res= new ResponseEntity<>( HttpStatus.NOT_FOUND);

				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND Message");
				// res= (ResponseEntity<Player>) ResponseEntity.notFound().build()

			}
		}

		return res;
	}

	@GetMapping("/sort")
	public List<Player> getAllPlayersSorted() {
		return service.getAllPlayersSort();
	}

	@PostMapping()
	public ResponseEntity<Player> savePlayer(@RequestBody Player player) {
		return new ResponseEntity<Player>(service.savePlayer(player), HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<Player> updatePlayer(@PathVariable("id") long id, @RequestBody Player player) {
		return new ResponseEntity<Player>(service.updatePlayer(id, player), HttpStatus.OK);
	}
}
