package com.projectbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectbank.model.Cuenta;
import com.projectbank.model.Movimiento;
import com.projectbank.repository.MovimientoRepository;

@Service
public class MovimientoServiceImp implements MovimientoService{
	
	@Autowired
	MovimientoRepository movimientoRepository;
	

	@Override
	public List<Movimiento> buscarTodo() {
		// TODO Auto-generated method stub
		return movimientoRepository.findAll();
	}

	@Override
	public List<Movimiento> buscarPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		
		Cuenta cuenta=new Cuenta();
		
		cuenta.setCodigo(codigo);
		
		return movimientoRepository.findByCuenta(cuenta);
	}

	@Override
	public void guardar(Movimiento movimiento) {
		movimientoRepository.save(movimiento);
	}

	@Override
	public long contar() {
		
		return movimientoRepository.count();
	}

	

}
