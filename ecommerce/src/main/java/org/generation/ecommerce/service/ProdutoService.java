package org.generation.ecommerce.service;

import org.generation.ecommerce.model.Produto;
import org.generation.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Poala
 * @author Jessica Marques
 * @author Rafael
 *
 */
@Service
public class ProdutoService {

	@Autowired 
	private ProdutoRepository produto;
	/**
	 * 
	 * @param idProduto
	 * @return
	 */
	public ResponseEntity<Produto> buscarPorId(Long idProduto) {
		return produto.findById(idProduto).map(res -> ResponseEntity.ok(res))
				.orElse(ResponseEntity.notFound().build());
	
	}
}
