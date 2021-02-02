package com.projectbank.service;

import java.util.List;

import com.projectbank.model.Movimiento;

public interface MovimientoService {

	public List<Movimiento> buscarTodo();
	
	public List<Movimiento> buscarPorCodigo(String codigo);
	
	public void guardar(Movimiento movimiento);
	
	public long contar();

}
