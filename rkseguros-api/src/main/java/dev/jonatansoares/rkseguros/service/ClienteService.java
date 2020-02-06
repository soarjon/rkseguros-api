package dev.jonatansoares.rkseguros.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import dev.jonatansoares.rkseguros.domain.Cliente;
import dev.jonatansoares.rkseguros.domain.exceptions.EntidadeNaoEncontradaException;
import dev.jonatansoares.rkseguros.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private static final String MSG_ENTIDADE_INEXISTENTE = "Entidade de ID %s não encontrada.";
	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> getClientes() {
		return this.clienteRepository.findAll();
	}

	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente getClienteById(Long clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(
						String.format(MSG_ENTIDADE_INEXISTENTE, clienteId)));
	}

	public void deleteCliente(Long clienteId) {
		try {
			this.clienteRepository.deleteById(clienteId);
		} catch (EmptyResultDataAccessException ex) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_ENTIDADE_INEXISTENTE, clienteId));
		}
	}

	public Cliente updateCliente(Cliente cliente, Long clienteId) {
		Cliente clienteSalvo = getClienteById(clienteId);
		
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		
		return this.clienteRepository.save(clienteSalvo);
	}
}
