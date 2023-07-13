package com.devsuperior.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.services.GameListService;

@RestController
@RequestMapping("/lists")
public class GameListController {
	
	
	@Autowired
	private GameListService gameListService;
	
	@GetMapping
	public List<GameListDTO> listar(){
		return gameListService.listar();
		
	}

	@GetMapping("/{id}")
	public GameListDTO buscar(@PathVariable Long id ) {
		return gameListService.buscar(id);
	}

}
