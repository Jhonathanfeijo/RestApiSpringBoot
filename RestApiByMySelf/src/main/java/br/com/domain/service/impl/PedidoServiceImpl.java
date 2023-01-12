package br.com.domain.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domain.DTO.ItemPedidoDTO;
import br.com.domain.DTO.PedidoDTO;
import br.com.domain.enums.StatusPedido;
import br.com.domain.exception.ClienteNaoEncontradoException;
import br.com.domain.exception.PedidoNaoEncontradoException;
import br.com.domain.exception.ProdutoNaoEncontradoException;
import br.com.domain.model.Cliente;
import br.com.domain.model.ItemPedido;
import br.com.domain.model.Pedido;
import br.com.domain.model.Produto;
import br.com.domain.repository.ClienteRepository;
import br.com.domain.repository.ItemPedidoRepository;
import br.com.domain.repository.PedidoRepository;
import br.com.domain.repository.ProdutoRepository;
import br.com.domain.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	PedidoRepository pedidos;
	@Autowired
	ClienteRepository clientes;
	@Autowired
	ItemPedidoRepository itens;
	@Autowired
	ProdutoRepository produtos;
	
	@Transactional
	@Override
	public Pedido savePedido(PedidoDTO pedidoDto) {
		
		Cliente cliente = clientes.findById(pedidoDto.getCliente())
				.orElseThrow(() -> new ClienteNaoEncontradoException());
		Pedido pedido = new Pedido ();
		pedido.setCliente(cliente);
		pedido.setDataPedido(LocalDate.now());
		pedido.setStatus(StatusPedido.REALIZADO);
		pedido.setTotal(pedidoDto.getValorTotal());
		pedidos.save(pedido);
		List<ItemPedido> itensConvertido = converterItens(pedido,pedidoDto.getItens());
		itens.saveAll(itensConvertido);
		return pedido;
	
	
	}

	public List<ItemPedido> converterItens(Pedido pedido,List<ItemPedidoDTO> itens){
		return itens.stream().map(item ->{
			ItemPedido itemPedido = new ItemPedido();
			Produto produto = produtos.findById(item.getProduto()).orElseThrow(() -> new ProdutoNaoEncontradoException());
			itemPedido.setProduto(produto);
			itemPedido.setPedido(pedido);
			itemPedido.setQuantidade(item.getQuantidade());
			return itemPedido;
			}).collect(Collectors.toList());	
		
	}

	@Override
	public Pedido findPedidoById(Integer id) {
		return pedidos.findByIdFetchItensPedido(id).orElseThrow(() -> new PedidoNaoEncontradoException());
	}


}
