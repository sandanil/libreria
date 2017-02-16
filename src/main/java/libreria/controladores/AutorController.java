package libreria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import libreria.modelo.entidades.Autor;
import libreria.modelo.entidades.Libro;
import libreria.modelo.repositorios.AutorRepositorio;
import libreria.modelo.repositorios.LibroRepositorio;


@Controller
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	private AutorRepositorio autorRepo;

	@Autowired
	private LibroRepositorio libroRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listar (Model model) {

		Iterable<Libro> lista = libroRepo.findAll();
		Iterable<Autor> listaautor = autorRepo.findAll();
		model.addAttribute("titulo", "Listado de Autores");
		model.addAttribute("libros", lista);
		model.addAttribute("autores", listaautor);
		
		return "autor/listado";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String guardar(@Valid @ModelAttribute Autor autor, BindingResult bindingResult, Model model) {

		autorRepo.save(autor);
		
		Iterable<Libro> lista = libroRepo.findAll();
		Iterable<Autor> listaauto = autorRepo.findAll();
		model.addAttribute("titulo", "Listado de Autores");
		model.addAttribute("libros", lista);
		model.addAttribute("autores", listaauto);
		return "autor/listado";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> borrar (@PathVariable Long id){
		try {
			autorRepo.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception ex){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET )
	@ResponseBody
	public Autor buscarAutor(@PathVariable Long id){
		Autor autor = autorRepo.findOne(id);
		return autor;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detalles/{id}")
    public String detalleAutor(Model model,@PathVariable Long id){
        Autor autor = autorRepo.findOne(id);
        model.addAttribute("autor", autor);
        return "autor/detalles";
    }
}
