package com.devsuperior.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.dto.ReplacementDTO;
import com.devsuperior.dslist.services.GameListService;
import com.devsuperior.dslist.services.GameService;

@RestController
@RequestMapping("/lists")
public class GameListController {
	
	
	@Autowired
	private GameListService gameListService;
	
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public List<GameListDTO> listar(){
		return gameListService.listar();
		
	}
	
	@GetMapping("/{id}/games")
	public List<GameMinDTO> findByList(@PathVariable Long id ) {
		return gameService.findByList(id);
	}
	
	@GetMapping("/{id}")
	public GameListDTO buscar(@PathVariable Long id ) {
		return gameListService.buscar(id);
	}
	
	@PostMapping("/{listId}/replacement")
	public List<GameMinDTO> mover(@RequestBody ReplacementDTO body, @PathVariable Long listId) {
		return gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
	}

}
