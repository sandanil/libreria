package libreria.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import libreria.modelo.entidades.Autor;
import libreria.modelo.repositorios.AutorRepositorio;

@Component
public class AutorPropertyEditor extends PropertyEditorSupport{
	
@Autowired private AutorRepositorio autorRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long idAutor = Long.parseLong(text);
		Autor autor = autorRepositorio.findOne(idAutor);
		setValue(autor);
	}
}
