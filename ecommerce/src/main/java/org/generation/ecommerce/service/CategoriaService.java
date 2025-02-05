package org.generation.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.generation.ecommerce.model.Categoria;
import org.generation.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
	 * 
	 * @param novoResiduo
	 * @return
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
	 * 
	 * @param residuo
	 * @return
	 */
	public Optional<?> atualizar(Categoria residuo) {
		return ((Optional<?>) repository.findByResiduoContainingIgnoreCase(residuo.getResiduo()))
				.map(residuoExistente -> {
					return Optional.empty();
				}).orElseGet(() -> {
					return Optional.ofNullable(repository.save(residuo));
				});
	}

	/**
	 * 
	 * @param id
	 * @param residuoParaAtualizar
	 * @return
	 */
	public Optional<Categoria> att(Long id, Categoria residuoParaAtualizar) {
		return repository.findById(id).map(novoResiduo -> {
			novoResiduo.setResiduo(residuoParaAtualizar.getResiduo());
			return Optional.ofNullable(repository.save(novoResiduo));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
