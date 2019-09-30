package com.minsait.ProyectoPrueba;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
	private Long id;
	private String name;
	private String puesto;
	private Long sueldo;
}
