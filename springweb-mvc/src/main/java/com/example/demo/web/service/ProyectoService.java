package com.example.demo.web.service;

import java.util.List;

import com.example.demo.web.entity.Proyectos;

public interface ProyectoService {
	
	public List<Proyectos> listarTodosLosProyectos();
	
	public Proyectos guardarProyecto(Proyectos proyecto);
	
	public Proyectos obtenerProyecto(Long id);
	
	public void eliminarProyecto(Long id);

}
