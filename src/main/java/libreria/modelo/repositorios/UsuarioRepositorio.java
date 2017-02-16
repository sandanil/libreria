package libreria.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import libreria.modelo.entidades.Usuario;

@Repository

public interface UsuarioRepositorio extends CrudRepository<Usuario,Long> {

	UserDetails findOneByUsername(String usuario);
	
	
	
	
	

}
