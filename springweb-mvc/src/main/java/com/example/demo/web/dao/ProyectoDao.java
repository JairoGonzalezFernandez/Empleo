package com.example.demo.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.web.entity.Proyectos;

	
	@Repository
	public interface ProyectoDao extends JpaRepository<Proyectos, Long>{

	}


