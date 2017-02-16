package libreria.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import libreria.modelo.entidades.Tienda;

@Repository
public interface TiendaRepositorio extends CrudRepository<Tienda, Long>{

}
