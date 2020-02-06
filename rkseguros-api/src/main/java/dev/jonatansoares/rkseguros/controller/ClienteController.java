package dev.jonatansoares.rkseguros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.jonatansoares.rkseguros.domain.Cliente;
import dev.jonatansoares.rkseguros.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> get() {
		return this.clienteService.getClientes();
	}
	
	@GetMapping("/{clienteId}")
	public Cliente getById(@PathVariable Long clienteId) {
		return this.clienteService.getClienteById(clienteId);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = this.clienteService.saveCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	
	@PutMapping("/{clienteId}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long clienteId) {
		return this.clienteService.updateCliente(cliente, clienteId);
	}
	
	@DeleteMapping("/{clienteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long clienteId) {
		this.clienteService.deleteCliente(clienteId);
	}
}
