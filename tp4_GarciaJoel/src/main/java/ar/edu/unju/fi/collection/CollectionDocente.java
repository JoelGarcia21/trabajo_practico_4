package ar.edu.unju.fi.collection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Docente;

@Component
public class CollectionDocente {

private static List<Docente> docentes = new ArrayList<>();
	
	public static List<Docente> getDocentes() {
		if(docentes.isEmpty()) {
			docentes.add(new Docente(1234, "Juan", "Pérez", "juan@gmail.com", 123456789));
			docentes.add(new Docente(5678, "María", "González", "maria@gmail.com", 987654321));
			docentes.add(new Docente(9012, "Carlos", "Rodríguez", "carlos@gmail.com", 111222333));
			docentes.add(new Docente(3456, "Laura", "López", "laura@gmail.com", 444555666));
			docentes.add(new Docente(7890, "Pedro", "Martínez", "pedro@gmail.com", 777888999));
		}
		return docentes;
	}
	
	public static void eliminarDocente(int legajo) {
		Docente d = buscarDocente(legajo);
		if (d != null) {
			docentes.remove(d);
		}
	}
	
	public static void agregarDocente(Docente docente) {
		docentes.add(docente);
	}
	
	public static Docente buscarDocente(int legajo) {
		for (Docente d : docentes) {
			if (d.getLegajo()==legajo) {
				return d;
			}
		}
		return null;
	}
	
	public static void modificarDocente(Docente docente) {
		Docente existente = buscarDocente(docente.getLegajo());
        if (existente != null) {
            existente.setNombre(docente.getNombre());
            existente.setApellido(docente.getApellido());
            existente.setCorreo(docente.getCorreo());
            existente.setTelefono(docente.getTelefono());
        }
	}
}
