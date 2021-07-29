package org.generation.ecommerce.service;

import java.nio.charset.Charset;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.generation.ecommerce.model.Produto;
import org.generation.ecommerce.model.Usuario;
import org.generation.ecommerce.model.dto.UserLoginDto;
import org.generation.ecommerce.model.dto.UsuarioDTO;
import org.generation.ecommerce.repository.ProdutoRepository;
import org.generation.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 
 * @author hanely menezes
 *@version v.0.1
 *
 *
 */
@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repositoryU;
	@Autowired
	private ProdutoRepository repositoryP;

	/**
	 * 
	 * @param novoUsuario
	 * @return, status ok e salva usuário caso não haja duplicatas
	 * @return, status 400 caso usuário existente
	 */
	public ResponseEntity<Usuario> cadastrarUsuario(Usuario novoUsuario) {
		List<Usuario> usuarioExistente = repositoryU.findAllByUsuario(novoUsuario.getUsuario());
		List<Usuario> emailExistente = repositoryU.findByEmail(novoUsuario.getEmail());
		if (usuarioExistente.isEmpty() && emailExistente.isEmpty()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(senhaCriptografada);
			return ResponseEntity.status(201).body(repositoryU.save(novoUsuario));
			
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	/**
	 * 
	 * @param, usuarioLogin
	 * @return, usuario logado com a senha criptografada,
	 * @return, null caso o usuário erre a senha.
	 */
	public Optional<UserLoginDto> logar(Optional<UserLoginDto> usuarioLogin) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repositoryU.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {

			if (encoder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				String auth = usuarioLogin.get().getUsuario() + ":" + usuarioLogin.get().getSenha();
				byte[] encondedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encondedAuth);

				usuarioLogin.get().setToken(authHeader);
				usuarioLogin.get().setNome(usuario.get().getNome());

				return usuarioLogin;
			}
		}

		return null;
	}

	/**
	 * 
	 * @param idUsuario, tipo long para pesquisar o usuário no banco
	 * @return, usuário encontrado ou então retorna notFound
	 */
	public ResponseEntity<Usuario> listarPorId(long idUsuario) {
		return repositoryU.findById(idUsuario).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * 
	 * @param nome, tipo String para encontrar usuário pelo nome
	 * @return notFound, para usuário não existentes e status ok para usuários existentes
	 */
	public ResponseEntity<List<Usuario>> listarPorNome(String nome) {
		List<Usuario> nomeExistente = repositoryU.findByNomeContainingIgnoreCase(nome);

		if (nomeExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(nomeExistente);
		}
	}

	/**
	 * 
	 * @param idUsuario, tipo Long para encontrar usuario no banco
	 * @param senhaParaAtualizar, para atualizar senha
	 * @return Optional salvar senha atualizada
	 */
	public Optional<Usuario> alterarSenha(Long idUsuario, UsuarioDTO senhaParaAtualizar) {
		return repositoryU.findById(idUsuario).map(senhaAtual -> {
			senhaAtual.setSenha(senhaParaAtualizar.getSenha());
			return Optional.ofNullable(repositoryU.save(senhaAtual));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

	/**
	 * 
	 * @param idUsuario, tipo Long para encontrar usuário no banco
	 * @param usuarioParaAtualizar, da classe DTO para atualizar
	 * @return Optional usuário salvo
	 */
	public Optional<Usuario> alterarUsuario(Long idUsuario, UsuarioDTO usuarioParaAtualizar) {
		return repositoryU.findById(idUsuario).map(usuarioAtual -> {
			usuarioAtual.setUsuario(usuarioParaAtualizar.getUsuario());
			return Optional.ofNullable(repositoryU.save(usuarioAtual));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

	/**
	 * 
	 * @param idUsuario, tipo Long para retornar usuario e deletar
	 * @return String com mensagem de ação
	 */
	public ResponseEntity<String> deletarUsuario(Long idUsuario) {
		return repositoryU.findById(idUsuario).map(usuarioExistente -> {
			repositoryU.deleteById(usuarioExistente.getIdUsuario());
			return ResponseEntity.ok("Usuário deletado com sucesso!");
		}).orElseGet(() -> {
			return ResponseEntity.ok("Usuário não encontrado!");
		});
	}

	/**
	 * 
	 * @param idUsuario, tipo Long para selecionar usuário
	 * @param idProduto, tipo Long para selecionar produto
	 * @return produto dentro de usuário.
	 */
	public Optional<Usuario> selecionarProduto(Long idUsuario, Long idProduto) {
		Optional<Produto> produtoExistente = repositoryP.findById(idProduto);
		if (produtoExistente.isPresent()) {
			return repositoryU.findById(idUsuario).map(usuarioExistente -> {
				usuarioExistente.getListaProduto().add(produtoExistente.get());
				return Optional.ofNullable(repositoryU.save(usuarioExistente));
			}).orElseGet(() -> {
				return Optional.empty();
			});
		} else {
			return Optional.empty();
		}
	}
}
