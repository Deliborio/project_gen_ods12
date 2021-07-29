package org.generation.ecommerce.seguranca;

import java.util.Optional;

import org.generation.ecommerce.model.Usuario;
import org.generation.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Poala
 * @author Jessica Marques
 * @author Rafael
 */
@Service
public class UserDetailsServiceImplements implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional <Usuario> usuarioExistente = usuarioRepository.findByUsuario(userName);
		usuarioExistente.orElseThrow(() -> new UsernameNotFoundException (userName + "Não encontrado."));
		
		return usuarioExistente.map(UserDetailsImplements::new).get();
		
	}
	
}
