package com.projectbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projectbank.model.Cuenta;
import com.projectbank.service.CuentaService;

@Controller
@RequestMapping("/cuentas")

public class CuentaController {

	@Autowired
	CuentaService cuentaService;

	@GetMapping("/")
	public String listarCuentas(Model model) {
		model.addAttribute("listaCuentas", cuentaService.listarTodo());
		return "views/cuentas/list";
	}

	@GetMapping("/form")
	public String form(Cuenta cuenta, Model model) {
		return "views/cuentas/form";
	}

	@PostMapping("/guardar")
	public String guardar(Cuenta cuenta, RedirectAttributes attributes) {
		cuentaService.guardar(cuenta);
		attributes.addFlashAttribute("guardado","Cuenta creada exitosamente");
		return "redirect:/cuentas/";
	}
}
