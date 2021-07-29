package org.generation.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Poala
 * @author Jessica Marques
 * @author Rafael
 */
@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProduto;

	@NotNull
	private String status;

	@NotNull
	private String endereco;

	@NotNull
	private String dataRetirada;

	private String descricao;

	@NotNull
	private Long quantidade;

	@ManyToOne
	@JsonIgnoreProperties({"produto", "listaProduto"})
	@JoinColumn(name = "fk_categoria")
	@NotNull
	private Categoria categoria;

	@ManyToOne
	@JsonIgnoreProperties({"usuario","listaProduto"})
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;

	// Special Methods
	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(String dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// fk_categoria
	// fk_usuario
	// @ManyToOne
	// @JsonIgnoreProperties({""})
	// private CategoriaModel categoria;

}