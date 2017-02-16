package libreria.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import libreria.modelo.entidades.Libro;

@Repository
public interface LibroRepositorio extends CrudRepository<Libro, Long>{

}
