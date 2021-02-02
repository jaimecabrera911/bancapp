package com.projectbank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projectbank.model.Cliente;
import com.projectbank.service.CiudadService;
import com.projectbank.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@Autowired
	CiudadService ciudadService;

	@GetMapping("/")
	public String listarClientes(Model model) {
		model.addAttribute("clientes", clienteService.listarTodo());
		return "views/clientes/lista";
	}

	@GetMapping("/form")
	public String formulario(Cliente cliente, Model model) {
		model.addAttribute("ciudades", ciudadService.buscarTodos());
		return "views/clientes/form";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Cliente cliente, Model model, RedirectAttributes attributes) {
		clienteService.guardar(cliente);
		attributes.addFlashAttribute("guardado", "Cliente guardado con éxito!");
		return "redirect:/clientes/";
	}

}
