package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.dao.DepartamentoDao;
import com.formacionspringboot.apirest.entity.Departamento;
import com.formacionspringboot.apirest.entity.Empleado;
import com.formacionspringboot.apirest.entity.Jefe;



public class DepartamentoServiceImpl implements DepartamentoService{
	
	
	@Autowired
	DepartamentoDao departamentoDao;
	
	
	@Override
	@Transactional(readOnly=true) 
	public List<Departamento> findAll() {
		
		return (List<Departamento>) departamentoDao.findAll();
	}

	@Override
	@Transactional(readOnly=true) 
	public Departamento findById(Long id) {
		
		return departamentoDao.findById(id).orElse(null); 
	}

	@Override
	public Departamento save(Departamento departamento) {	
		return departamentoDao.save(departamento);
	}

	@Override
	public void delete(Long id) {
		departamentoDao.deleteById(id);		
	}

	@Override
	@Transactional(readOnly=true) 
	public List<Empleado> findAllEmpleados() {
		
		return departamentoDao.findAllEmpleados();
	}

	@Override
	@Transactional(readOnly=true) 
	public List<Jefe> findAllJefes() {
		
		return departamentoDao.findAllJefes();
	}

}
