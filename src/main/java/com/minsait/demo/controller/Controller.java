package com.minsait.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.demo.modelo.Empleado;
import com.minsait.demo.repository.IDBRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("${ms.api.version}/")
@Api(value = "/${ms.api.version}")
public class Controller {
	
	@Autowired
	IDBRepository repo;

	List<Empleado> listaEmpleados = new ArrayList<Empleado>();

	@ApiOperation(httpMethod = "GET", value = "Mostrar la lista de empleados", notes = "añadir comentarios")
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "La lista de usuarios se ha devuelto correctamente"),
			@ApiResponse(code = 404, message = "No se encuentran datos de la lista"),
			@ApiResponse(code = 500, message = "Unexpected exception (Internal Server Error)") })
	
	@GetMapping("/empleados")
	public ResponseEntity<Object> showEmpleados() {

		HashMap<String, Object> content = new HashMap<>();
		content.put("Usuarios registrados: ", listaEmpleados);

		return new ResponseEntity<>(content, HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(httpMethod = "POST", value = "Añadir empleados", notes = "añadir comentarios")
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "El usuarios se ha añadido correctamente"),
			@ApiResponse(code = 404, message = "No se encuentran datos de la lista"),
			@ApiResponse(code = 500, message = "Unexpected exception (Internal Server Error)") })

	@PostMapping("/empleados")
	public ResponseEntity<Object> addEmpleados(@RequestBody Empleado e) {

		listaEmpleados.add(new Empleado( e.getName(), e.getPuesto(), e.getSueldo()));
		repo.save(e);

		return new ResponseEntity<>("se ha añadido", HttpStatus.ACCEPTED);

	}
	
	@PostMapping("/update-empleados")
	public ResponseEntity<Object> updateEmpleados(@RequestBody Empleado e) {

		
		if(!repo.findById(e.getId()).isPresent()) {
			return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
		} //else
		repo.save(e);

		return new ResponseEntity<>("Empleado actualizado correctamente", HttpStatus.OK);
	}
	
	@ApiOperation(httpMethod = "DELETE", value = "Eliminar empleados", notes = "añadir comentarios")
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "El usuarios se ha eliminado correctamente"),
			@ApiResponse(code = 404, message = "No se encuentran datos de la lista"),
			@ApiResponse(code = 500, message = "Unexpected exception (Internal Server Error)") })

	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<Object> deleteEmpleados(@PathVariable(value = "id") int id) {

		for (Empleado e : listaEmpleados) {

			if (e.getId()==id) {
				listaEmpleados.remove(e);
			}
		}

		return new ResponseEntity<>("Empleado eliminado correctamente", HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(httpMethod = "PUT", value = "Modificar empleados", notes = "añadir comentarios")
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "El usuarios se ha modificadodo correctamente"),
			@ApiResponse(code = 404, message = "No se encuentran datos de la lista"),
			@ApiResponse(code = 500, message = "Unexpected exception (Internal Server Error)") })
	@PutMapping("/empleados/{puesto}")
	public ResponseEntity<Object> modificarEmpleados(
			@ApiParam(value = "multiplicador para subida de sueldo", required = true) @RequestBody Long multiplicadorSueldo,
			@ApiParam(value = "identificador del puuesto para subida de sueldo", required = true) @PathVariable String puesto) {
		boolean encontrado = false;

		for (Empleado e : listaEmpleados) {

			if (e.getPuesto().equals(puesto)) {
				encontrado = true;
				e.setSueldo(multiplicadorSueldo * e.getSueldo());

			}

		}
		if (encontrado == true) {

			return new ResponseEntity<>("se ha modificado personal con el puesto " + puesto, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("no existe personal con ese puesto", HttpStatus.ACCEPTED);
		}
	}
	
	@ApiOperation(httpMethod = "DELETE", value = "Eliminar empleados por body", notes = "Se hace por requestBody")
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "El usuarios se ha eliminado correctamente"),
			@ApiResponse(code = 404, message = "No se encuentran datos de la lista"),
			@ApiResponse(code = 500, message = "Unexpected exception (Internal Server Error)") })


	@DeleteMapping("/empleados")
	public ResponseEntity<Object> deleteEmpleados(@RequestBody String name) {

		for (Empleado e : listaEmpleados) {

			if (e.getName().equals(name)) {
				listaEmpleados.remove(e);
			}
		}

		return new ResponseEntity<>("se ha eliminado", HttpStatus.ACCEPTED);

	}
	
	///////////////////// Database/////////////////////
	@GetMapping("/insert")
	public String insert() {

		repo.saveAll(Arrays.asList(new Empleado("Nombre1", "Apellido1", null), 
								   new Empleado("Nombre2", "Apellido2", null),
								   new Empleado("Nombre3", "Apellido3", null)));
		
		return "Se han realizado las inserciones en la base de datos";
	}

	@GetMapping("/findall")
	public List<Empleado> findAll() {
		
		return repo.findAll();
	} 
	

}
