package br.com.domain.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.domain.exception.ClienteNaoEncontradoException;
import br.com.domain.model.Cliente;
import br.com.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clientes;

	@GetMapping("/{id}")
	public Cliente findClienteById(@PathVariable("id") Integer id) {
		return clientes.findById(id)
				.orElseThrow(() -> new ClienteNaoEncontradoException());
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente saveCliente(@RequestBody @Valid Cliente cliente) {
		return clientes.save(cliente);
	}

	@GetMapping("/all")
	public List<Cliente> allClientes(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		return clientes.findAll(example);
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCliente(@PathVariable("id") Integer id, @RequestBody @Valid Cliente cliente) {
		  clientes.findById(id).map(clienteEncontrado ->{
			  cliente.setId(clienteEncontrado.getId());
			  clientes.save(cliente);
			  return cliente;
		  }).orElseThrow(() -> new ClienteNaoEncontradoException());
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCliente(@PathVariable("id") Integer id) {
		clientes.findById(id).map( cliente ->{
			clientes.deleteById(id);
			return cliente;
		}).orElseThrow(() ->  new ClienteNaoEncontradoException());
	}

}
