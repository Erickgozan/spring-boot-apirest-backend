package com.erickgozan.springboot.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erickgozan.springboot.apirest.models.entity.Cliente;
import com.erickgozan.springboot.apirest.models.entity.Region;

public interface IClienteDao extends JpaRepository<Cliente, Long>{
		
	@Query("FROM Region")
	public List<Region> findAllRegiones();
}
