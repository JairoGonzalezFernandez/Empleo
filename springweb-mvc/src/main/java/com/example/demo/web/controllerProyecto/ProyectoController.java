package com.example.demo.web.controllerProyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.web.entity.Empleado;
import com.example.demo.web.entity.Proyectos;
import com.example.demo.web.service.EmpleadoService;
import com.example.demo.web.service.ProyectoService;

@Controller
public class ProyectoController {
	
	@Autowired
	private ProyectoService servicio;
	
	
	@GetMapping("/proyecto")
	public String mostrarProyectos(Model modelo) {
		
		modelo.addAttribute("dato",servicio.listarTodosLosProyectos());
		return "proyecto";
	}
	
	@GetMapping("proyecto/nuevo")
	public String crearProyectosTemplate(Model modelo) {
		Proyectos proy=new Proyectos();
		modelo.addAttribute("keyProyecto",proy);
		
		return "nuevo_proyecto";
	}
	
	@PostMapping("/proyecto")
	public String guardarProyectos(@ModelAttribute("KeyProyecto") Proyectos proyecto){
		servicio.guardarProyecto(proyecto);
		return "redirect:/proyecto";
		
	}
	
	@GetMapping("/proyecto/editar/{id}")
	public String editarProyecto(@PathVariable Long id,Model modelo){
		modelo.addAttribute("keyProyecto", servicio.obtenerProyecto(id));
		
		return "editar_proyecto";
	
	}
	
	@PostMapping("/proyecto/{id}")
	public String actualizarProyecto(@PathVariable Long id,@ModelAttribute("keyProyecto")Proyectos proyecto) {
		
		Proyectos proyectoExistente = servicio.obtenerProyecto(id);
		
		proyectoExistente.setId(id);
		proyectoExistente.setNombre(proyecto.getNombre());
		proyectoExistente.setFechaInicio(proyecto.getFechaInicio());
		proyectoExistente.setFechaFinal(proyecto.getFechaFinal());
		proyectoExistente.setActivo(proyecto.getActivo());
		
		servicio.guardarProyecto(proyectoExistente);
		
		return "redirect:/proyecto";
	}
	
	
	@GetMapping("proyecto/eliminar/{id}")
	public String eliminarProyecto(@PathVariable Long id) {
		servicio.eliminarProyecto(id);
		
		return "redirect:/proyecto";
	}
	
	

}


