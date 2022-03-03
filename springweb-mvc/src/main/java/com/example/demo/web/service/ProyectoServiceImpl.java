package com.example.demo.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.web.dao.ProyectoDao;
import com.example.demo.web.entity.Empleado;
import com.example.demo.web.entity.Proyectos;

@Service
public class ProyectoServiceImpl implements ProyectoService{
	
	@Autowired
	private ProyectoDao repositorio;
	
	public List<Proyectos> listarTodosLosEmpleados(){
		return repositorio.findAll();
	}
	
	

	@Override
	public List<Proyectos> listarTodosLosProyectos() {
		return repositorio.findAll();
	}

	@Override
	public Proyectos guardarProyecto(Proyectos proyecto) {
	
		return repositorio.save(proyecto);
	}

	@Override
	public Proyectos obtenerProyecto(Long id) {
		
		return repositorio.findById(id).get();
	}

	@Override
	public void eliminarProyecto(Long id) {
		
		repositorio.deleteById(id);
		
	}
	
	

}
