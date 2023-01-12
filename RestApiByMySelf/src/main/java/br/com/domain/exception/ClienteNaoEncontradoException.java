package br.com.domain.exception;

public class ClienteNaoEncontradoException extends RuntimeException {

	public ClienteNaoEncontradoException() {
		super("Cliente não encontrado");
	}
}
