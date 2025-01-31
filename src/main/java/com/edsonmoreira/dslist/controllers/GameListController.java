package com.edsonmoreira.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edsonmoreira.dslist.dto.GameListDTO;
import com.edsonmoreira.dslist.dto.GameMinDTO;
import com.edsonmoreira.dslist.dto.ReplacementDTO;
import com.edsonmoreira.dslist.entities.Game;
import com.edsonmoreira.dslist.entities.GameList;
import com.edsonmoreira.dslist.services.GameListService;
import com.edsonmoreira.dslist.services.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {
	
	@Autowired
	private GameListService gameListService;
	
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public List<GameListDTO> findAll() {
		return gameListService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<GameList> createGameList(@RequestBody GameList gameList) {
		GameList gameListSaved = gameListService.createGameList(gameList);
		return  ResponseEntity.status(HttpStatus.CREATED).body(gameListSaved);
	}
	
	@PutMapping(value = "/{listId}")
	public ResponseEntity<Void> updateGameList(
			@PathVariable Long listId, 
			@RequestBody GameList gameList) {
		gameListService.updateList(listId, gameList);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{listId}/games")
	public List<GameMinDTO> findByList(@PathVariable Long listId) {
		return gameService.findByList(listId);
	}
	
	@PostMapping(value = "/{listId}/replacement")
	public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
		gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
	}
	
	@PostMapping(value = "/{listId}/games")
	public ResponseEntity<Game> createGame(
			@PathVariable Long listId,
	        @RequestBody Game game) {
	    Game createdGame = gameService.createGameInAList(listId, game);
	    return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
	}
	
	@DeleteMapping("/{listId}")
	public ResponseEntity<Void> deleteGameList(@PathVariable Long listId) {
		gameListService.deleteGameList(listId);
		return ResponseEntity.noContent().build();
	}
}