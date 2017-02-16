package libreria.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import libreria.modelo.entidades.Autor;

@Repository
public interface AutorRepositorio extends CrudRepository<Autor, Long>{

}
