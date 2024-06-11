package ar.edu.unju.fi.collection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;


@Component
public class CollectionMateria {
	private static List<Materia> materias = new ArrayList<>();
	
	public static List<Materia> getMaterias() {
		if(materias.isEmpty()) {
			List<Docente> docentes = CollectionDocente.getDocentes();
			List<Carrera> carreras = CollectionCarrera.getCarreras();
            materias.add(new Materia(1, "Matemáticas", "Curso 1", (byte) 4, false, docentes.get(0), carreras.get(0)));
            materias.add(new Materia(2, "Base de datos 2", "Aula 18", (byte) 3, false, docentes.get(1), carreras.get(2)));
            materias.add(new Materia(3, "Algebra 1", "Aula 15", (byte) 2, true, docentes.get(2), carreras.get(3)));
            materias.add(new Materia(4, "Programacion Visual", "Sala 4", (byte) 3, false, docentes.get(3), carreras.get(4)));
        }
        return materias;
	}
	
	public static void eliminarMateria(int codigo) {
		Materia m = buscarMateria(codigo);
		if (m != null) {
			materias.remove(m);
		}
	}
	
	public static void agregarMateria(Materia materia) {
		materias.add(materia);
	}
	
	public static Materia buscarMateria(int codigo) {
		for (Materia m : materias) {
			if (m.getCodigo()==codigo) {
				return m;
			}
		}
		return null;
	}
	
	public static void modificarMateria(Materia materia) {
		Materia existente = buscarMateria(materia.getCodigo());
        if (existente != null) {
            existente.setNombre(materia.getNombre());
            existente.setCurso(materia.getCurso());
            existente.setHoras(materia.getHoras());
            existente.setModalidad(materia.isModalidad());
            existente.setDocente(materia.getDocente());
            existente.setCarrera(materia.getCarrera());
        }
	}
}
