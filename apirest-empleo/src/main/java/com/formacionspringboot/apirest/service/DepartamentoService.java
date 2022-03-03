package com.formacionspringboot.apirest.service;

import java.util.List;

import com.formacionspringboot.apirest.entity.Departamento;
import com.formacionspringboot.apirest.entity.Empleado;
import com.formacionspringboot.apirest.entity.Jefe;



public interface DepartamentoService {
	
	public List<Departamento> findAll();
	public Departamento findById(Long id);
	public Departamento save(Departamento departamento);
	public void delete(Long id);
	
	public List<Empleado> findAllEmpleados();
	public List<Jefe> findAllJefes();
	
	

}
