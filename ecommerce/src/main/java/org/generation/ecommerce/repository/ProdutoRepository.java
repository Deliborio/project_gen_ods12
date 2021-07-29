package org.generation.ecommerce.repository;

import java.util.List;

import org.generation.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Poala
 * @author Jessica Marques
 * @author Rafael
 *
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	public List<Produto> findAllByDescricaoContainingIgnoreCase (String descricao);
	public List<Produto> findAllByStatusContainingIgnoreCase (String status);
	public List<Produto> findAllByEnderecoContainingIgnoreCase (String endereço);
	public List<Produto> findAllByQuantidade (Long quantidade);
	//public List<Produto> findAllByData_RetiradaContainingIgnoreCase (String data_retirada);
	//public List<Produto> findAllByQuantidadeContainingIgnoreCase1(Long quantidade);
}