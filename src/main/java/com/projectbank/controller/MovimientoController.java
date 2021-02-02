package com.projectbank.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projectbank.model.AdmCuenta;
import com.projectbank.model.Cliente;
import com.projectbank.model.Movimiento;
import com.projectbank.model.MovimientoPK;
import com.projectbank.service.AdmCuentaService;
import com.projectbank.service.MovimientoService;

@Controller
@RequestMapping("/movimientos")
public class MovimientoController {

	@Autowired
	MovimientoService movimientoService;

	@Autowired
	AdmCuentaService admCuentaService;

	@Autowired
	HttpSession httpSession;

	@GetMapping("")
	public String listarMovimientos(Model model) {
		model.addAttribute("movimientos", movimientoService.buscarTodo());
		return "views/movimientos/list";
	}

	@GetMapping("/{codigo}")
	public String listarMovimientoPorCodigo(@PathVariable String codigo, Model model) {
		Double acumulado = 0.0;
		List<Movimiento> movimientos = movimientoService.buscarPorCodigo(codigo);
		model.addAttribute("movimientos", movimientos);
		System.out.println(model.getAttribute("movimientos"));
		for (Movimiento movimiento : movimientos) {
			if (movimiento.getTipoMovimiento().equalsIgnoreCase("ingreso")) {
				acumulado += movimiento.getSaldo();
			} else if (movimiento.getTipoMovimiento().equalsIgnoreCase("egreso")) {
				acumulado -= movimiento.getSaldo();
			}
		}
		
		model.addAttribute("saldo", acumulado);
		
		return "views/movimientos/list";
	}

	@GetMapping("/form")
	public String form(Movimiento movimiento, Model model, HttpSession session) {
		Cliente usuario = (Cliente) session.getAttribute("usuario");
		List<AdmCuenta> cuentas = admCuentaService.listaPorCedula(usuario);
		model.addAttribute("cuentas", cuentas);
		return "/views/movimientos/form";

	}

	@PostMapping("/guardar")
	public String guardar(Movimiento movimiento, Model model, HttpSession session, RedirectAttributes attribute) {
		MovimientoPK movimientoPK = new MovimientoPK();
		Cliente usuario = (Cliente) session.getAttribute("usuario");
		movimientoPK.setId((int) movimientoService.contar()+1);
		movimientoPK.setCedulaCli(usuario.getCedula());
		movimientoPK.setCodigoCta(movimiento.getCuenta().getCodigo());
		movimiento.setMovimientoPK(movimientoPK);
		movimiento.setFechaMov(new Date());
		System.out.println(movimiento);
		movimientoService.guardar(movimiento);
		attribute.addFlashAttribute("guardado", "Transaccion exitosa");
		return "redirect:/movimientos/"+movimiento.getCuenta().getCodigo();

	}

}
