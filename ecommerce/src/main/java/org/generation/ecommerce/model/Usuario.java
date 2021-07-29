package org.generation.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.generation.ecommerce.model.util.TipoUsuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author hanely menezes
 *
 */
@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;

	@NotBlank(message = "Nome não deve ser nulo ou vazio")
	@Size(max = 40)
	private String nome;

	@NotBlank(message = "Sobrenome não deve ser nulo ou vazio")
	@Size(max = 40)
	private String sobrenome;
	
	@NotBlank
	@Size(max = 100)
	private String usuario;

	@NotNull(message = "Email não pode ser nulo ou vazio")
	private String email;

	@NotNull(message = "Senha não deve ser nulo ou vazio")
	@Size(min = 8)
	private String senha;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "usuario" })
	private List<Produto> listaProduto = new ArrayList<>();

	// Special Methods
	public long getIdUsuario() {
		return idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

}
