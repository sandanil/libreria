package libreria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import libreria.modelo.entidades.Autor;
import libreria.modelo.entidades.Libro;
import libreria.modelo.repositorios.AutorRepositorio;
import libreria.modelo.repositorios.EjemplarRepositorio;
import libreria.modelo.repositorios.LibroRepositorio;
import libreria.propertyeditors.AutorPropertyEditor;


@Controller
@RequestMapping("/libro")
public class LibroController {

	@Autowired
	private LibroRepositorio libroRepo;
	
	@Autowired
	private AutorRepositorio autorRepo;
	
	@Autowired
	private EjemplarRepositorio ejemplarRepo;
	
	@Autowired
	private AutorPropertyEditor autorPropertyEditor;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listar (Model model) {

		Iterable<Libro> lista = libroRepo.findAll();
		Iterable<Autor> listaautor = autorRepo.findAll();
		model.addAttribute("titulo", "Listado de Libros");
		model.addAttribute("libros", lista);
		model.addAttribute("autores", listaautor);
		
		return "libro/listado";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String guardar(@Valid @ModelAttribute Libro libro, BindingResult bindingResult, Model model) {

		libroRepo.save(libro);
		
		Iterable<Libro> lista = libroRepo.findAll();
		Iterable<Autor> listaauto = autorRepo.findAll();
		model.addAttribute("titulo", "Listado de Libros");
		model.addAttribute("libros", lista);
		model.addAttribute("autores", listaauto);
		return "libro/listado";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> borrar (@PathVariable Long id){
		try {
			libroRepo.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception ex){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET )
	@ResponseBody
	public Libro buscarLibro(@PathVariable Long id){
		Libro libro = libroRepo.findOne(id);
		return libro;
	}
	@InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Autor.class, autorPropertyEditor);
}
	
	@RequestMapping(method=RequestMethod.GET, value="/detalles/{id}")
    public String detalleLibro(Model model,@PathVariable Long id){
        Libro libro = libroRepo.findOne(id);
        model.addAttribute("libro", libro);
        return "libro/detalles";
    }
}
