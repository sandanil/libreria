package libreria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import libreria.modelo.repositorios.TiendaRepositorio;


@Controller
@RequestMapping("/tienda")
public class TiendaController {

	@Autowired
	private TiendaRepositorio tiendaRepo;
}
