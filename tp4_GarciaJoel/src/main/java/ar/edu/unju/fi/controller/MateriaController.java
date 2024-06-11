package ar.edu.unju.fi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collection.CollectionCarrera;
import ar.edu.unju.fi.collection.CollectionDocente;
import ar.edu.unju.fi.collection.CollectionMateria;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

@Controller
@RequestMapping("") 
public class MateriaController {

	@GetMapping("/materias")
	public String getMaterias(Model model){
		model.addAttribute("materias", CollectionMateria.getMaterias());
		return "materias";
	}
	
	@GetMapping("/materias/nuevo")
	public String getNuevaMateria(Model model) {
		Materia materia = new Materia();
		boolean edicion = false;
	    model.addAttribute("materia", materia);
        model.addAttribute("edicion", edicion);
        model.addAttribute("docentes", CollectionDocente.getDocentes());
        model.addAttribute("carreras", CollectionCarrera.getCarreras());
	    
	    return "materiaForm";
	}
	
	@PostMapping("/materias/guardar")
	public ModelAndView guardarMateria(@ModelAttribute ("materia") Materia materia) {
		 Materia existente = CollectionMateria.buscarMateria(materia.getCodigo());
		 ModelAndView modelView = new ModelAndView("materiaForm");
		    if (existente != null) {
		        modelView.addObject("error", "El código de la materia ya existe. Por favor, use un código diferente.");
		        modelView.addObject("materia", materia);
		        modelView.addObject("docentes", CollectionDocente.getDocentes());
		        modelView.addObject("carreras", CollectionCarrera.getCarreras());
		        modelView.addObject("edicion", false);
		        return modelView;
		    }
		 // Buscar y asignar las instancias de Docente y Carrera
	        Docente docente = CollectionDocente.buscarDocente(materia.getDocente().getLegajo());
	        Carrera carrera = CollectionCarrera.buscarCarrera(materia.getCarrera().getCodigo());
	        materia.setDocente(docente);
	        materia.setCarrera(carrera);
	        
		    CollectionMateria.agregarMateria(materia);
		    modelView.setViewName("redirect:/materias");
		    return modelView;
	}
	
	@GetMapping("/materias/modificar/{codigo}")
	public String modificarMateria(Model model, @PathVariable(value="codigo") int codigo) {
		Materia materiaEncontrada = new Materia();
		boolean edicion = true;
		materiaEncontrada = CollectionMateria.buscarMateria(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaEncontrada);
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		 model.addAttribute("docentes", CollectionDocente.getDocentes());
		
		return "materiaForm";
	}
	
	@PostMapping("/materias/modificar")
	public String guardarModificacion(@ModelAttribute("materia") Materia materia) {
		List<Docente> docentes = CollectionDocente.getDocentes();
	    for (Docente docente : docentes) {
	        if (docente.getLegajo() == materia.getDocente().getLegajo()) {
	            materia.setDocente(docente);
	            break;
	        }
	    }
	    List<Carrera> carreras = CollectionCarrera.getCarreras();
	    for (Carrera carrera : carreras) {
	        if (carrera.getCodigo() == materia.getCarrera().getCodigo()) {
	            materia.setCarrera(carrera);
	            break;
	        }
	    }
		CollectionMateria.modificarMateria(materia);
		return "redirect:/materias";
		
	}
	
	@GetMapping("/materias/eliminar/{codigo}")
	public String eliminarMateria(@PathVariable(value="codigo") int codigo) {
		CollectionMateria.eliminarMateria(codigo);
		return "redirect:/materias";
	}
}
