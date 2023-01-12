package br.com.domain.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
	
	public ProdutoNaoEncontradoException() {
		super("Produto não encontrado");
	}

}
