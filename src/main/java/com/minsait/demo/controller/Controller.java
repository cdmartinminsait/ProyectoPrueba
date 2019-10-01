package com.minsait.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("${ms.api.version}/")
@Api(value = "/${ms.api.version}", description  = "Controlador de gestion de empleados de arquitectura")
public class Controller {

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

	@PostMapping("/empleados")
	public ResponseEntity<Object> addEmpleados(@RequestBody Empleado e) {

		listaEmpleados.add(new Empleado(e.getId(), e.getName(), e.getPuesto(), e.getSueldo()));

		return new ResponseEntity<>("se ha añadido", HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<Object> deleteEmpleados(@PathVariable(value = "id") int id) {

		for (Empleado e : listaEmpleados) {

			if (e.getId().equals(id)) {
				listaEmpleados.remove(e);
			}
		}

		return new ResponseEntity<>("Empleado eliminado correctamente", HttpStatus.ACCEPTED);
	}

	@PutMapping("/empleados/{puesto}")
	public ResponseEntity<Object> modificarEmpleados(@RequestBody Long multiplicadorSueldo,
			@PathVariable String puesto) {
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

	@DeleteMapping("/empleados")
	public ResponseEntity<Object> deleteEmpleados(@RequestBody String name) {

		for (Empleado e : listaEmpleados) {

			if (e.getName().equals(name)) {
				listaEmpleados.remove(e);
			}
		}

		return new ResponseEntity<>("se ha eliminado", HttpStatus.ACCEPTED);

	}

}
