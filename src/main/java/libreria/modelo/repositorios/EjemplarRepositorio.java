package libreria.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import libreria.modelo.entidades.Ejemplar;

@Repository
public interface EjemplarRepositorio extends CrudRepository<Ejemplar, Long>{

}
