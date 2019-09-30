package com.minsait.ProyectoPrueba;

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

@RestController
@RequestMapping("${ms.api.version}/")
public class Controller {
	public List<Empleado> listaEmpleados = new ArrayList<Empleado>();
	
	@GetMapping("/empleados")
	public ResponseEntity<Object> showEmpleados() {
		
		HashMap<String, Object> content = new HashMap<>();
		content.put("Usuarios registrados: ",listaEmpleados);
		
		return new ResponseEntity<>(content, HttpStatus.ACCEPTED);
	}
	
	// Esta función añade empleados a través de URL
	@PostMapping("/empleados")
	public ResponseEntity<Object> insertEmpleados(@RequestBody Empleado e) {
		// Con esto hemos especificado simplemente el formato
		
		// Aquí estamos creando al empleado
		listaEmpleados.add(new Empleado(e.getId(), e.getName(), e.getPuesto(), e.getSueldo())); 

		return new ResponseEntity<>("empleado añadido", HttpStatus.ACCEPTED);
	}
	
	// Eliminar empleados 
	// --> Mapear: hacer el "http"
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<Object> deleteEmpleados(@PathVariable(value="id") int id) {
		
		for (Empleado e : listaEmpleados) {
			
			if (e.getId() == id) {
				listaEmpleados.remove(e);
				return new ResponseEntity<>("Empleado eliminado correctamente", HttpStatus.ACCEPTED);
			}
		}

		return new ResponseEntity<>("No encontrado", HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/empleados_delete_por_body")
	public ResponseEntity<Object> deleteEmpleadosBody(@RequestBody String name)  {
		
		for (Empleado e : listaEmpleados) {
			
			if (e.getName().equals(name)) {
				listaEmpleados.remove(e);
				return new ResponseEntity<>("Empleado eliminado correctamente", HttpStatus.ACCEPTED);
			}
		}

		return new ResponseEntity<>("No encontrado", HttpStatus.NOT_FOUND);
	}
	@PutMapping("/empleado/{id}/{subida}")
	public ResponseEntity<Object> aumentarSueldo(@PathVariable(value="id") Long id, @PathVariable(value="subida") int subida) {
		for (Empleado e : listaEmpleados) {
			
			if (e.getId() == id) {
				Long nuevoSueldo = e.getSueldo()*subida;
				e.setSueldo(nuevoSueldo);
				return new ResponseEntity<>("Sueldo subido correctamente a "+ e.getName() +".", HttpStatus.ACCEPTED);
			}
		}
		
		return new ResponseEntity<>("Empleado eliminado correctamente", HttpStatus.ACCEPTED);
	}
}
