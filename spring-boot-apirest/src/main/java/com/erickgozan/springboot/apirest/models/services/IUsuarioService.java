package com.erickgozan.springboot.apirest.models.services;

import com.erickgozan.springboot.apirest.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario FindByUsername(String username);
	
}
