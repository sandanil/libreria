package libreria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import libreria.modelo.repositorios.EjemplarRepositorio;


@Controller
@RequestMapping("/ejemplar")
public class EjemplarController {

	@Autowired
	private EjemplarRepositorio ejemplarRepo;
}
