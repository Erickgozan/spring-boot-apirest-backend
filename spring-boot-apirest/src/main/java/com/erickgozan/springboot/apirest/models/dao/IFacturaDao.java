package com.erickgozan.springboot.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.erickgozan.springboot.apirest.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

}
