package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.repositories.GameListRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository gameListRepository;
	
	
	@Transactional(readOnly = true)
	public GameListDTO buscar(Long id) {
		GameList game = gameListRepository.findById(id).get();
		return new GameListDTO(game);
	}
	
	@Transactional(readOnly = true)
	public List<GameListDTO> listar(){
		List<GameList> result = gameListRepository.findAll();
		List<GameListDTO> lista = result.stream().map(x -> new GameListDTO(x)).toList();
		return lista;
		
		
	}
}
