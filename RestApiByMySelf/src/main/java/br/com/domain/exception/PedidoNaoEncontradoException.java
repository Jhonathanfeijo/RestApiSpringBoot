package br.com.domain.exception;

public class PedidoNaoEncontradoException extends RuntimeException{
	
	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}

}
