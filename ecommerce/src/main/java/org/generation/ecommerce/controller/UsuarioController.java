package org.generation.ecommerce.controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.generation.ecommerce.model.Usuario;
import org.generation.ecommerce.model.dto.UserLoginDto;
import org.generation.ecommerce.model.dto.UsuarioDTO;
import org.generation.ecommerce.repository.UsuarioRepository;
import org.generation.ecommerce.service.UsuarioService;
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


/**
 * 
 * @author hanely menezes
 *
 */
@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin("*")
public class UsuarioController {
	// @Autowired private UsuarioRepository repositoryU;
	@Autowired
	private UsuarioService serviceU;
	@Autowired
	private UsuarioRepository repositoryU;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
		return serviceU.cadastrarUsuario(usuario);
	}

	@PostMapping("/logar")
	public ResponseEntity<UserLoginDto> authentication(@Valid @RequestBody Optional<UserLoginDto> user) {
		return serviceU.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@GetMapping("/buscar/todos")
	public ResponseEntity<List<Usuario>> listarTodos() {
		return ResponseEntity.ok(repositoryU.findAll());
	}

	@GetMapping("/buscar/id/{idUsuario}")
	public ResponseEntity<Usuario> listarId(@Valid @PathVariable(value = "idUsuario") Long idUsuario) {
		return serviceU.listarPorId(idUsuario);
	}

	@GetMapping("/buscar/nome/{nome}")
	public ResponseEntity<List<Usuario>> listarNome(@Valid @PathVariable(value = "nome") String nome) {
		return serviceU.listarPorNome(nome);
	}

	@PutMapping("/alterar/senha/{idUsuario}")
	public Optional<Usuario> alterarSenha(@Valid @PathVariable Long idUsuario,
			@RequestBody UsuarioDTO senhaParaAtualizar) {
		return serviceU.alterarSenha(idUsuario, senhaParaAtualizar);
	}

	@PutMapping("/alterar/usuario/{idUsuario}")
	public Optional<Usuario> alterarUsuario(@Valid @PathVariable Long idUsuario,
			@RequestBody UsuarioDTO usuarioParaAtualizar) {
		return serviceU.alterarUsuario(idUsuario, usuarioParaAtualizar);
	}

	@PutMapping("/selecionar/produto/idproduto/{idProduto}/usuario/idusuario/{idUsuario}")
	public ResponseEntity<Usuario> adicionarProdutonaCesta(@PathVariable(value = "idUsuario") Long idUsuario,
			@PathVariable(value = "idPruduto") Long idProduto) {
		return serviceU.selecionarProduto(idUsuario, idProduto)
				.map(produtoSelecionado -> ResponseEntity.status(201).body(produtoSelecionado))
				.orElse(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/deletar/{idUsuario}")
	public ResponseEntity<String> deletarUsuario(@Valid @PathVariable Long idUsuario) {
		return serviceU.deletarUsuario(idUsuario);
	}

}
