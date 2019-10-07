package com.minsait.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minsait.demo.modelo.Empleado;

@Repository
public interface IDBRepository extends JpaRepository<Empleado, Long> {
	
	List<Empleado> findAll();
	

}
