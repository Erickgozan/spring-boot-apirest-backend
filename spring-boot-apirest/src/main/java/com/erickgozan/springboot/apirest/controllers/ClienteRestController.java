package com.erickgozan.springboot.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;

import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.erickgozan.springboot.apirest.models.entity.Cliente;
import com.erickgozan.springboot.apirest.models.entity.Region;
import com.erickgozan.springboot.apirest.models.services.IClienteService;
import com.erickgozan.springboot.apirest.models.services.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	//private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IUploadFileService uploadFileService;

	// Listar los clientes
	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}

	// Lista paginada
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 5);
		return clienteService.findAll(pageable);
	}

	// Crear un cliente
	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {

		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<String, Object>();
		// Evalua si hay errores de validación
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		// Intenta agregar al cliente, si hay un error lanza una excepción
		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a la base de datos");
			response.put("error", e.getMostSpecificCause().getLocalizedMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// Se agrega el mensaje a la respuesta
		response.put("mensaje", "El cliente ha sido creado con éxito!");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// Buscar cliente por su id
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Cliente cliente = null;
		Map<String, Object> response = new HashMap<String, Object>();
		// Intenta buscar al cliente, si no lanza una excepción
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// Evalua si el cliente no existe
		if (cliente == null) {
			response.put("mensaje", "No se encontro el cliente con el id: ".concat(id.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		// Retorna el cliente en la respuesta
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	// Actualizar cliente
	@Secured("ROLE_ADMIN")
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {

		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteUpdate = null;
		Map<String, Object> response = new HashMap<String, Object>();
		// Evalúa si hay errores de validación
		if (result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError error : result.getFieldErrors()) {
				errors.add("El campo '" + error.getField() + "' " + error.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		// Evalúa si el cliente no existe
		if (clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar el cliente con ID: ".concat(id.toString())
					.concat(", no existe en la base de datos!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		// Intenta actualizar al cliente, si no laza un excepción
		try {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteActual.setRegion(cliente.getRegion());
			// Actualizar cliente
			clienteUpdate = clienteService.save(clienteActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// Se agrega el mensaje a la respuesta
		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("cliente", clienteUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Eliminar cliente
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Cliente cliente = clienteService.findById(id);
			String nombreArchivoAnterior = cliente.getFoto();
			uploadFileService.isExistFile(nombreArchivoAnterior);

			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente se ha eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// Subir imagen-documento
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam Long id, @RequestParam MultipartFile archivo) {

		Map<String, Object> response = new HashMap<>();

		Cliente cliente = clienteService.findById(id);
		String nombreFoto = null;
		try {
			nombreFoto = uploadFileService.uploadFile(archivo);
		} catch (IOException e) {
			response.put("mensaje", "Error al guardar la imagen");
			response.put("error", e.getMessage().concat(": ").concat(e.getCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// Eliminar imagen si esta repetida.
		String nombreArchivoAnterior = cliente.getFoto();
		uploadFileService.isExistFile(nombreArchivoAnterior);
			
		cliente.setFoto(nombreFoto);
		clienteService.save(cliente);
		response.put("cliente", cliente);
		response.put("mensaje", "Se ha subido correctamente la imagen: " + nombreFoto);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

	// Visualizar la imagen
	@GetMapping("/uploads/img/{nombreArchivo:.+}")
	public ResponseEntity<Resource> viewFoto(@PathVariable String nombreArchivo) {

		Resource resource = null;
		try {
			resource = uploadFileService.viewResource(nombreArchivo);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

		return new ResponseEntity<Resource>(resource, cabecera, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")	
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		return clienteService.findAllRegiones();
	}
	
	/*
	 * @ResponseStatus(code = HttpStatus.NO_CONTENT)
	 */
}
