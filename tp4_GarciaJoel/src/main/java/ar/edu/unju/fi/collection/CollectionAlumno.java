package ar.edu.unju.fi.collection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;

@Component
public class CollectionAlumno {

private static List<Alumno> alumnos = new ArrayList<>();
	
	public static List<Alumno> getAlumnos() {
		if(alumnos.isEmpty()) {
			alumnos.add(new Alumno(12345678, "Juan", "Pérez", "juan.perez@example.com", 123456789, LocalDate.of(1995, 3, 15), "Calle Falsa 123", 1001));
            alumnos.add(new Alumno(87654321, "María", "González", "maria.gonzalez@example.com", 987654321, LocalDate.of(1997, 7, 22), "Avenida Siempre Viva 742", 1002));
            alumnos.add(new Alumno(11223344, "Pedro", "Martínez", "pedro.martinez@example.com", 112233445, LocalDate.of(1993, 11, 5), "Calle Sesamo 456", 1003));
            alumnos.add(new Alumno(44332211, "Ana", "López", "ana.lopez@example.com", 556677889, LocalDate.of(1994, 9, 30), "Calle del Sol 789", 1004));
            alumnos.add(new Alumno(66554433, "Carlos", "Ramírez", "carlos.ramirez@example.com", 334455667, LocalDate.of(1996, 1, 12), "Avenida de los Álamos 123", 1005));
        }
		return alumnos;
	}
	
	public static void eliminarAlumno(int lu) {
		Alumno a = buscarAlumno(lu);
		if (a != null) {
			alumnos.remove(a);
		}
	}
	
	public static void agregarAlumno(Alumno alumno) {
		alumnos.add(alumno);
	}
	
	public static Alumno buscarAlumno(int lu) {
		for (Alumno a : alumnos) {
			if (a.getLu()==lu) {
				return a;
			}
		}
		return null;
	}
	
	public static void modificarAlumno(Alumno alumno) {
		Alumno existente = buscarAlumno(alumno.getLu());
        if (existente != null) {
            existente.setNombre(alumno.getNombre());
            existente.setApellido(alumno.getApellido());
            existente.setCorreo(alumno.getCorreo());
            existente.setTelefono(alumno.getTelefono());
            existente.setFechaNacimiento(alumno.getFechaNacimiento());
            existente.setDomicilio(alumno.getDomicilio());
        }
	}
	
}
