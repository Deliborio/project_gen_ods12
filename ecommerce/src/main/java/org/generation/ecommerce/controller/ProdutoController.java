package org.generation.ecommerce.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.generation.ecommerce.model.Produto;
import org.generation.ecommerce.repository.ProdutoRepository;
import org.generation.ecommerce.service.ProdutoService;

/**
 * @author Jessica Marques
 * @author Paola
 * @author Rafael
 **/
@RestController
@RequestMapping("/api/v1/produto")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produto;

	@Autowired
	private ProdutoService serviceProduto;

	@GetMapping("/buscar/todos")
	public ResponseEntity<List<Produto>> buscarTodos() {
		return ResponseEntity.ok(produto.findAll());
	}

	@GetMapping("/buscar/id/{idProduto}")
	public ResponseEntity<ResponseEntity<Produto>> getID(@Valid @PathVariable Long idProduto) {
		return ResponseEntity.ok(serviceProduto.buscarPorId(idProduto));
	}

	@GetMapping("/buscar/status/{status}")
	public ResponseEntity<List<Produto>> buscarStatus(@Valid @PathVariable String status) {
		return ResponseEntity.ok(produto.findAllByStatusContainingIgnoreCase(status));
	}

	@GetMapping("/buscar/endereco/{endereco}")
	public ResponseEntity<List<Produto>> buscarEndereco(@Valid @PathVariable String endereco) {
		return ResponseEntity.ok(produto.findAllByEnderecoContainingIgnoreCase(endereco));
	}

	@GetMapping("/buscar/descricao/{descricao}")
	public ResponseEntity<List<Produto>> buscarDescricao(@Valid @PathVariable String descricao) {
		return ResponseEntity.ok(produto.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Produto> salvarproduto(@Valid @RequestBody Produto produto1) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produto.save(produto1));
	}

	@PutMapping("/atualizar/id/{idProduto}")
	public ResponseEntity<Produto> alterar(@Valid @PathVariable @RequestBody Produto produto1, long idProduto) {
		return ResponseEntity.ok(produto.save(produto1));
	}

	@DeleteMapping("/deletar/{id}")
	public void delete(@Valid @PathVariable Long id) {
		produto.deleteById(id);
	}
}