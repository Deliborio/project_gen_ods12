package org.generation.ecommerce.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe DTO utilizada para atualizar dados do usuário
 * 
 * @author hanely menezes
 * 
 */
public class UsuarioDTO {
	@Size(min = 8)
	private String senha;

	private String usuario;

	// Special Methods
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
		

}
