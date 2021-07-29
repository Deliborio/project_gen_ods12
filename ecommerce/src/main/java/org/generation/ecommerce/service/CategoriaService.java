package org.generation.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.generation.ecommerce.model.Categoria;
import org.generation.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Leonardo Rosenbaum
 */
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	/**
	 * 
	 * @return
	 */
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> listaCategoria = repository.findAll();
		if (listaCategoria.isEmpty()) {
			return ResponseEntity.status(204).build();

		} else {
			return ResponseEntity.status(200).body(listaCategoria);
		}
	}

	/**
	 * @param novoResiduo - Corpo de parametro necessario para passar um novo residuo.
	 * @return - Retorna o residuo salvo na db.
	 * @version - v1.0
	 * @author - Rosenbaum
	 */
	public Optional<?> cadastrar(Categoria novoResiduo) {
		return ((Optional<?>) repository.findByResiduoContainingIgnoreCase(novoResiduo.getResiduo()))
				.map(residuoExistente -> {
					return Optional.empty();
				}).orElseGet(() -> {
					return Optional.ofNullable(repository.save(novoResiduo));
				});
			}

	/** 
	 * @param idCategoria - Pede um ID de categoria para checar se ele existe
	 * @param residuoParaAtualizar - Se o ID corresponder com a DB, solicita o corpo de um novo residuo para atualizar.
	 * @return - Retorna o residuo atualizado.
	 * @version v1.0
	 * @author - Rosenbaum
	 */
	public Optional<Categoria> att(Long idCategoria, Categoria residuoParaAtualizar) {
		return repository.findById(idCategoria).map(novoResiduo -> {
			novoResiduo.setResiduo(residuoParaAtualizar.getResiduo());
			return Optional.ofNullable(repository.save(novoResiduo));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
