package org.generation.ecommerce.repository;
import java.util.Optional;
import org.generation.ecommerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Leonardo Rosenbaum
 **/
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	public Optional<Categoria>  findByResiduoContainingIgnoreCase(String residuo);

}
