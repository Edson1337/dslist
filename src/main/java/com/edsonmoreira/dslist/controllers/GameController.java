package com.edsonmoreira.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edsonmoreira.dslist.dto.GameDTO;
import com.edsonmoreira.dslist.dto.GameMinDTO;
import com.edsonmoreira.dslist.entities.Game;
import com.edsonmoreira.dslist.services.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@GetMapping(value = "/{id}")
	public GameDTO findById(@PathVariable Long id) {
		return gameService.findById(id);
	}
	
	@GetMapping
	public List<GameMinDTO> findAll() {
		return gameService.findAll();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> updateGame(
			@PathVariable Long id,
			@RequestBody Game game) {
		gameService.updateGame(id, game);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletGame(@PathVariable Long id) {
		gameService.deleteGame(id);
		return ResponseEntity.noContent().build();
	}
}
