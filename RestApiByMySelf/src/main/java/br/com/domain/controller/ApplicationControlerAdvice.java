package br.com.domain.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.domain.exception.ClienteNaoEncontradoException;
import br.com.domain.exception.PedidoNaoEncontradoException;
import br.com.domain.exception.ProdutoNaoEncontradoException;

@RestControllerAdvice
public class ApplicationControlerAdvice {
	
	
	@ExceptionHandler(ResponseStatusException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleResponseStatusException (ResponseStatusException ex) {
		return ex.getMessage();
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<String> handleMethodNotValiedException( MethodArgumentNotValidException ex) {
		List<String> erros = ex.getBindingResult().getAllErrors().stream().map(erro-> erro.getDefaultMessage()).collect(Collectors.toList());
		return erros;
	}
	
	@ExceptionHandler(ProdutoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handlePedidoNaoEncontradoException(PedidoNaoEncontradoException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(ClienteNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleClienteNaoEncontradoException(ClienteNaoEncontradoException ex) {
		return ex.getMessage();
	}
}
