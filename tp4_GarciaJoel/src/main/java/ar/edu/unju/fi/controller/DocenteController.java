package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collection.CollectionDocente;
import ar.edu.unju.fi.model.Docente;


@Controller
public class DocenteController {
	@Autowired
	private Docente docente;
	
	@GetMapping("/docentes")
	public String getDocentes(Model model){
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		return "docentes";
	}
	
	@GetMapping("/docentes/nuevo")
	public String getNuevoDocente(Model model) {
		boolean edicion = false;
		model.addAttribute("docente", docente);
		model.addAttribute("edicion", edicion);
		
		return "docenteForm";
	}
	
	@PostMapping("/docentes/guardar")
	public ModelAndView guardarDocente(@ModelAttribute ("docente") Docente docente) {
		 Docente existente = CollectionDocente.buscarDocente(docente.getLegajo());
		 ModelAndView modelView = new ModelAndView("docenteForm");
		    if (existente != null) {
		        modelView.addObject("error", "El legajo del profesor ya existe. Por favor, use un legajo diferente.");
		        modelView.addObject("docente", docente);
		        modelView.addObject("edicion", false);
		        return modelView;
		    }
		    CollectionDocente.agregarDocente(docente);
		    modelView.setViewName("redirect:/docentes");
		    return modelView;
	}
	
	@GetMapping("/docentes/modificar/{legajo}")
	public String modificarDocente(Model model, @PathVariable(value="legajo") int legajo) {
		Docente docenteEncontrado = new Docente();
		boolean edicion = true;
		docenteEncontrado = CollectionDocente.buscarDocente(legajo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("docente", docenteEncontrado);
		
		return "docenteForm";
	}
	
	@PostMapping("/docentes/modificar")
	public String guardarModificacion(@ModelAttribute("docente") Docente docente) {
		CollectionDocente.modificarDocente(docente);
		return "redirect:/docentes";
		
	}
	
	@GetMapping("/docentes/eliminar/{legajo}")
	public String eliminarDocente(@PathVariable(value="legajo") int legajo) {
		CollectionDocente.eliminarDocente(legajo);
		return "redirect:/docentes";
	}
}
