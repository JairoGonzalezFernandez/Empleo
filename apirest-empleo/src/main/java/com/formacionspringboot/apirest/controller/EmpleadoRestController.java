package com.formacionspringboot.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspringboot.apirest.entity.Empleado;
import com.formacionspringboot.apirest.service.EmpleadoService;



@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping({"/empleados", "/todos"})
	public List<Empleado> index(){
		return empleadoService.findAll();
	}
	
//	@GetMapping("clientes/{id}")
//	public Cliente findById(@PathVariable Long id){
//		return clienteService.findById(id);
//	}
	
	@GetMapping("empleados/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Empleado empleado = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			empleado = empleadoService.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al reallizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		if(empleado == null) {
			response.put("mensaje", "El empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}

		
		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
		
	}
	
//	@PostMapping("/cliente")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Cliente saveCliente(@RequestBody Cliente cliente) {
//		return clienteService.save(cliente);
//	}
	
	@PostMapping("/empleado")
	public ResponseEntity<?> saveEmpleado(@RequestBody Empleado empleado){
		Empleado empleadoNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleadoNuevo = empleadoService.save(empleado);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El empleado ha sido creado con éxito");
		response.put("empleado", empleadoNuevo);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
//	@PutMapping("/cliente/{id}")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Cliente updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
//		Cliente clienteUpdate = clienteService.findById(id);
//		clienteUpdate.setApellido(cliente.getApellido());
//		clienteUpdate.setNombre(cliente.getNombre());
//		clienteUpdate.setEmail(cliente.getEmail());
//		clienteUpdate.setTelefono(cliente.getTelefono());
//		clienteUpdate.setCreatedAt(cliente.getCreatedAt());
//		
//		return clienteService.save(clienteUpdate);	
//	}
	
	@PutMapping("/empleado/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateEmpleado(@RequestBody Empleado empleado, @PathVariable Long id) {
		Empleado empleadoActual = empleadoService.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		if (empleadoActual == null) {
			response.put("mensaje", "El empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try{
			empleadoActual.setDni(empleado.getDni());
			empleadoActual.setNombre(empleado.getNombre());
			empleadoActual.setSalario(empleado.getSalario());
			empleadoActual.setTelefono(empleado.getTelefono());
			
		
			
			empleadoService.save(empleadoActual);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El empleado ha sido actualizado con éxito");
		response.put("empleado", empleadoActual);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);	
	}
	
//	@DeleteMapping("/cliente/{id}")
//	@ResponseStatus(HttpStatus.OK)
//	public Cliente deleteCliente(@PathVariable Long id) {
//		Cliente clienteEliminado = findById(id);
//		clienteService.delete(id);
//		
//		return clienteEliminado;
//	}
	
	@DeleteMapping("/empleado/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteEmpleado(@PathVariable Long id){
		
		Empleado empleadoEliminado = empleadoService.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		if (empleadoEliminado == null) {
			response.put("mensaje", "El empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			empleadoService.delete(id);
			
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el empleado");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El empleado ha sido eliminado con éxito");
		response.put("articulo", empleadoEliminado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
