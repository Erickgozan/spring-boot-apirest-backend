package com.erickgozan.springboot.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "regiones")
public class Region implements Serializable {

	private static final long serialVersionUID = 7462721694985010453L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
