package com.erickgozan.springboot.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.erickgozan.springboot.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
	
	
}
