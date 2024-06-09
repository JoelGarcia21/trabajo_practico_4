package ar.edu.unju.fi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collection.CollectionCarrera;
import ar.edu.unju.fi.model.Carrera;


@Controller
class CarreraController {
	@Autowired
	private Carrera carrera;
	
	@GetMapping("/carreras")
	public String getCarreras(Model model){
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		return "carreras";
	}
	
	@GetMapping("/carreras/nuevo")
	public String getNuevaCarrera(Model model) {
		boolean edicion = false;
		model.addAttribute("carrera", carrera);
		model.addAttribute("edicion", edicion);
		
		return "carreraForm";
	}
	
	@PostMapping("/carreras/guardar")
	public ModelAndView guardarCarrera(@ModelAttribute ("carrera") Carrera carrera) {
		 Carrera existente = CollectionCarrera.buscarCarrera(carrera.getCodigo());
		 ModelAndView modelView = new ModelAndView("carreraForm");
		    if (existente != null) {
		        modelView.addObject("error", "El código de carrera ya existe. Por favor, use un código diferente.");
		        modelView.addObject("carrera", carrera);
		        modelView.addObject("edicion", false);
		        return modelView;
		    }
		    CollectionCarrera.agregarCarrera(carrera);
		    modelView.setViewName("redirect:/carreras");
		    return modelView;
	}
	
	@GetMapping("/carreras/modificar/{codigo}")
	public String modificarCarrera(Model model, @PathVariable(value="codigo") int codigo) {
		Carrera carreraEncontrada = new Carrera();
		boolean edicion = true;
		carreraEncontrada = CollectionCarrera.buscarCarrera(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("carrera", carreraEncontrada);
		
		return "carreraForm";
	}

	@PostMapping("/carreras/modificar")
	public String guardarModificacion(@ModelAttribute("carrera") Carrera carrera) {
		CollectionCarrera.modificarCarrera(carrera);
		return "redirect:/carreras";
		
	}
	
	@GetMapping("/carreras/eliminar/{codigo}")
	public String eliminarCarreras(@PathVariable(value="codigo") int codigo) {
		CollectionCarrera.eliminarCarrera(codigo);
		return "redirect:/carreras";
	}
	
}
