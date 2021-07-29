package org.generation.ecommerce.controller;

import javax.validation.Valid;

import org.generation.ecommerce.model.Categoria;
import org.generation.ecommerce.repository.CategoriaRepository;
import org.generation.ecommerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leonardo Rosenbaum
 **/
@RestController
@RequestMapping("api/v1/residuo")
@CrossOrigin("*")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private CategoriaRepository repository;

	@GetMapping("/todos")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(categoriaService.findAll());
	}
	
	@GetMapping("/buscar/id/{idCategoria}")
	public ResponseEntity<?> findById(@Valid @PathVariable Long idCategoria) {
		return ResponseEntity.ok(repository.findById(idCategoria));
	}

	@GetMapping("/buscar/residuo/{residuo}")
	public ResponseEntity<?> findByResiduo(@Valid @PathVariable String residuo) {
		return ResponseEntity.ok(repository.findByResiduoContainingIgnoreCase(residuo));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastroResiduo(@Valid @RequestBody Categoria residuo) {
		return ResponseEntity.ok(categoriaService.cadastrar(residuo));
	}

	@PutMapping("/atualizar/id/{idCategoria}")
	public ResponseEntity<?> atualizar(@Valid @PathVariable Long idCategoria, @RequestBody Categoria residuo) {
		return ResponseEntity.ok(categoriaService.att(idCategoria, residuo));
	}

	@DeleteMapping("/deletar/id/{idCategoria}")
	public void delete(@Valid @PathVariable Long idCategoria) {
		repository.deleteById(idCategoria);
	}
}
