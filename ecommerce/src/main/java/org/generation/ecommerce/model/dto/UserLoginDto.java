package org.generation.ecommerce.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserLoginDto {

	private String nome;

	private String sobrenome;

	@NotBlank(message = "Campo não pode ser vazio")
	private String usuario;

	private String email;

	@NotNull(message = "Campo não pode ser nulo")
	private String senha;

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
