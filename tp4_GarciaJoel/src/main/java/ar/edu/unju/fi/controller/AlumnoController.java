package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collection.CollectionAlumno;
import ar.edu.unju.fi.model.Alumno;

@Controller
public class AlumnoController {

	@Autowired
	private Alumno alumno;
	
	@GetMapping("/alumnos")
	public String getAlumnos(Model model){
		model.addAttribute("alumnos", CollectionAlumno.getAlumnos());
		return "alumnos";
	}
	
	@GetMapping("/alumnos/nuevo")
	public String getNuevoAlumno(Model model) {
		boolean edicion = false;
		model.addAttribute("alumno", alumno);
		model.addAttribute("edicion", edicion);
		
		return "alumnoForm";
	}
	
	@PostMapping("/alumnos/guardar")
	public ModelAndView guardarAlumno(@ModelAttribute ("alumno") Alumno alumno) {
		 Alumno existente = CollectionAlumno.buscarAlumno(alumno.getLu());
		 ModelAndView modelView = new ModelAndView("alumnoForm");
		    if (existente != null) {
		        modelView.addObject("error", "Ese LU ya le pertenece a un alumno. Por favor, use un LU diferente.");
		        modelView.addObject("alumno", alumno);
		        modelView.addObject("edicion", false);
		        return modelView;
		    }
		    CollectionAlumno.agregarAlumno(alumno);
		    modelView.setViewName("redirect:/alumnos");
		    return modelView;
	}
	
	@GetMapping("/alumnos/modificar/{lu}")
	public String modificarAlumno(Model model, @PathVariable(value="lu") int lu) {
		Alumno alumnoEncontrado = new Alumno();
		boolean edicion = true;
		alumnoEncontrado = CollectionAlumno.buscarAlumno(lu);
		model.addAttribute("edicion", edicion);
		model.addAttribute("alumno", alumnoEncontrado);
		
		return "alumnoForm";
	}
	
	@PostMapping("/alumnos/modificar")
	public String guardarModificacion(@ModelAttribute("alumno") Alumno alumno) {
		CollectionAlumno.modificarAlumno(alumno);
		return "redirect:/alumnos";
		
	}
	
	@GetMapping("/alumnos/eliminar/{lu}")
	public String eliminarAlumno(@PathVariable(value="lu") int lu) {
		CollectionAlumno.eliminarAlumno(lu);
		return "redirect:/alumnos";
	}
	
}
