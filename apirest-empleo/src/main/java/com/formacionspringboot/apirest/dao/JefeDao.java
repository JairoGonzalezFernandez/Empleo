package com.formacionspringboot.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.entity.Jefe;


@Repository
public interface JefeDao extends CrudRepository<Jefe,Long>{

}
