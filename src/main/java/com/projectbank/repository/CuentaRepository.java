package com.projectbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectbank.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    
	List<Cuenta> findByCodigo(String codigo);

}
