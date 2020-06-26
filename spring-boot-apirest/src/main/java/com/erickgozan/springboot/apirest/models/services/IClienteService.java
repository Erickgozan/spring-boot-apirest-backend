package com.erickgozan.springboot.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erickgozan.springboot.apirest.models.entity.Cliente;
import com.erickgozan.springboot.apirest.models.entity.Factura;
import com.erickgozan.springboot.apirest.models.entity.Producto;
import com.erickgozan.springboot.apirest.models.entity.Region;

public interface IClienteService {

	//Enlista todos los clientes
	public List<Cliente> findAll();
	//Pagina los clines
	public Page<Cliente> findAll(Pageable pagina);
	//Retorna un cliente por su id
	public Cliente findById(Long id);
	//Guarda el cliente
	public Cliente save(Cliente cliente);
	//Elimina a un cliente
	public void delete(Long id);
	//Enlista las regiones
	public List<Region> findAllRegiones();
	//Retorna la factura por su id
	public Factura findFacturaById(Long id);
	//Gurada la factura
	public Factura saveFactura(Factura factura);
	//Elimina la factura
	public void deleteFacturaById(Long id);
	//Buscar producto por su nombre
	public List<Producto> findProductoByNombre(String term);
	

}
