package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.web.entity.Empleado;
import com.example.demo.web.service.EmpleadoService;

@Controller
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService servicio;
	
	
	@GetMapping("/empleado")
	public String mostrarEmpleados(Model modelo) {
		
		modelo.addAttribute("dato",servicio.listarTodosLosEmpleados());
		return "empleado";
	}
	
	@GetMapping("empleado/nuevo")
	public String crearEmpleadoTemplate(Model modelo) {
		Empleado empl=new Empleado();
		modelo.addAttribute("keyEmpleado",empl);
		
		return "nuevo_empleado";
	}
	
	@PostMapping("/empleado")
	public String guardarEmpleado(@ModelAttribute("KeyEmpleado") Empleado empleado){
		servicio.guardarEmpleado(empleado);
		return "redirect:/empleado";
		
	}
	
	@GetMapping("/empleado/editar/{id}")
	public String editarEmpleado(@PathVariable Long id,Model modelo){
		modelo.addAttribute("keyEmpleado", servicio.obtenerEmpleado(id));
		
		return "editar_empleado";
	
	}
	
	@PostMapping("/empleado/{id}")
	public String actualizarEmpleado(@PathVariable Long id,@ModelAttribute("keyEmpleado")Empleado empleado) {
		
		Empleado empleadoExistente = servicio.obtenerEmpleado(id);
		
		empleadoExistente.setId(id);
		empleadoExistente.setNombre(empleado.getNombre());
		empleadoExistente.setApellido(empleado.getApellido());
		empleadoExistente.setEmail(empleado.getEmail());
		empleadoExistente.setTelefono(empleado.getTelefono());
		
		servicio.guardarEmpleado(empleadoExistente);
		
		return "redirect:/empleado";
	}
	
	
	@GetMapping("empleado/eliminar/{id}")
	public String eliminarEmpleado(@PathVariable Long id) {
		servicio.eliminarEmpleado(id);
		
		return "redirect:/empleado";
	}
	
	

}
