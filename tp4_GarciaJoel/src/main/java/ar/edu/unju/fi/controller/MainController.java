package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;


@Controller
class mainController{
	@GetMapping("/index")
	public String getIndex(){
		return "index";
	}
}