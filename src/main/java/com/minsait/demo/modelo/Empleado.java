package com.minsait.demo.modelo;



import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@ApiModel(value="Empleado", description="Se suministran los datos del usuario bla bla bla") 
@Data
@NoArgsConstructor
public class Empleado {
	
	private Long id;
	private String name;
	private String puesto;
	private Long sueldo;
	
	
	public Empleado(Long id, String name, String puesto, Long sueldo) {
		super();
		this.id = id;
		this.name = name;
		this.puesto = puesto;
		this.sueldo = sueldo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public Long getSueldo() {
		return sueldo;
	}
	public void setSueldo(Long sueldo) {
		this.sueldo = sueldo;
	}
	
	
	

}
