package com.formacionspringboot.apirest.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "empleados")
public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEmp;
	
	private String dni;
	private String nombre;
	private double salario;
	private int telefono;
	
	private Long idDep;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="departamento_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Departamento departamento;
	
	
	

	public Long getIdEmp() {
		return idEmp;
	}
	public void setIdEmp(Long idEmp) {
		this.idEmp = idEmp;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public Long getIdDep() {
		return idDep;
	}
	public void setIdDep(Long idDep) {
		this.idDep = idDep;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	

}
