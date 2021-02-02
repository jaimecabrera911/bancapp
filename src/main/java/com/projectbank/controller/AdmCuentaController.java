package com.projectbank.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projectbank.model.AdmCuenta;
import com.projectbank.model.Cliente;
import com.projectbank.service.AdmCuentaService;
import com.projectbank.service.CuentaService;

@Controller
@RequestMapping("/admcuentas")
public class AdmCuentaController {

	@Autowired
	AdmCuentaService admCuentaService;
	
	@Autowired
	CuentaService cuentaService;	

	
	@GetMapping
	public String listarCuentasPorCedula(HttpSession session, Model model) {

		Cliente usuario = (Cliente) session.getAttribute("usuario");
		List<AdmCuenta> listaCuentas = admCuentaService.listaPorCedula(usuario);
		model.addAttribute("cuentas", listaCuentas);
		return "views/admcuentas/list";
	}

}
