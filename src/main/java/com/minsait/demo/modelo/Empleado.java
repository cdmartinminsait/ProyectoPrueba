package com.minsait.demo.modelo;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
@ApiModel(value="Empleado", description="Se suministran los datos del usuario bla bla bla") 
@Data
@NoArgsConstructor
@Entity
@Table(name = "postgres")
public class Empleado implements Serializable{
	
	private static final long serialVersionUID = -2343243243242432341L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name") 
	private String name;
	@Column(name = "puesto") 
	private String puesto;
	@Column(name = "sueldo") 
	private Long sueldo;
	public Empleado(Long id, String name, String puesto, Long sueldo) {

		this.id = id;
		this.name = name;
		this.puesto = puesto;
		this.sueldo = sueldo;
	}
	public Empleado() {

	}
	
	public Empleado( String name, String puesto, Long sueldo) {


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
