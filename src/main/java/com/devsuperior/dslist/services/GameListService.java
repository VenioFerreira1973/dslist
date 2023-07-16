package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
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
	
	@Transactional
	public List<GameMinDTO> move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);
	
		/*
		list.stream()
			.forEach(g -> gameListRepository.updateBelongingPosition(listId, g.getId(), g.getPosition()));
		*/
		int min = sourceIndex < destinationIndex ? sourceIndex:destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex:sourceIndex;
		
		for(int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
		
		List<GameMinDTO> lista = list.stream().map(x -> new GameMinDTO(x)).toList();
		
		return lista;
	}
	
}
