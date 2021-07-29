package org.generation.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.generation.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author hanely menezes
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public List<Usuario> findByNomeContainingIgnoreCase(String nome);
	public List<Usuario> findAllByUsuario(String usuario);
	Optional<Usuario> findByUsuario(String usuario);
	public List<Usuario> findByEmail(String email);
	
}
