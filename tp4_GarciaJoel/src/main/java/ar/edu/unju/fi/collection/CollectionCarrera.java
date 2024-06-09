package ar.edu.unju.fi.collection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;

@Component
public class CollectionCarrera {
	private static List<Carrera> carreras = new ArrayList<>();
	public static List<Carrera> getCarreras() {
		if(carreras.isEmpty()) {
			carreras.add(new Carrera(1, "Analista Programador Universitario",  (byte) 3, true));
			carreras.add(new Carrera(2, "Ingeniería Informática", (byte) 5, true));
			carreras.add(new Carrera(3, "Ingeniería Quimica",  (byte) 5, true));
			carreras.add(new Carrera(4, "Licenciatura en Sistemas",  (byte) 5, true));
			carreras.add(new Carrera(5, "Licenciatura en Tecnología de los Alimentos",  (byte) 4, true));
		}
		return carreras;
	}
	
	public static void eliminarCarrera(int codigo) {
		Carrera c = buscarCarrera(codigo);
		if (c != null) {
			carreras.remove(c);
		}
	}
	
	public static void agregarCarrera(Carrera carrera) {
		carreras.add(carrera);
	}
	
	public static Carrera buscarCarrera(int codigo) {
		for (Carrera c : carreras) {
			if (c.getCodigo()==codigo) {
				return c;
			}
		}
		return null;
	}
	
	public static void modificarCarrera(Carrera carrera) {
		Carrera existente = buscarCarrera(carrera.getCodigo());
        if (existente != null) {
            existente.setNombre(carrera.getNombre());
            existente.setCantidadAnios(carrera.getCantidadAnios());
            existente.setEstado(carrera.isEstado());
        }
	}
}
