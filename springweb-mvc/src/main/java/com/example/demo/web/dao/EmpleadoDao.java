package com.example.demo.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.web.entity.Empleado;

@Repository
public interface EmpleadoDao extends JpaRepository<Empleado, Long>{

}
