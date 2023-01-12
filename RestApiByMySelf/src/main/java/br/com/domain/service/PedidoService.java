package br.com.domain.service;

import br.com.domain.DTO.PedidoDTO;
import br.com.domain.model.Pedido;

public interface PedidoService {
	
	public Pedido savePedido(PedidoDTO pedidoDto);
	
	public Pedido findPedidoById (Integer id);
	

	
	
}
