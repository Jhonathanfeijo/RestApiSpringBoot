package br.com.domain.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.domain.DTO.InformacoesItemPedidoDTO;
import br.com.domain.DTO.InformacoesPedidoDTO;
import br.com.domain.DTO.PedidoDTO;
import br.com.domain.model.ItemPedido;
import br.com.domain.model.Pedido;
import br.com.domain.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService service;

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido savePedido(@RequestBody @Valid PedidoDTO dto) {
		return service.savePedido(dto);

	}

	@GetMapping("/{id}")
	public InformacoesPedidoDTO findPedidodById(@PathVariable("id") Integer id) {
		Pedido pedido = service.findPedidoById(id);
		return converterPedidoEmInfPedidoDTO(pedido);

	}

	public InformacoesPedidoDTO converterPedidoEmInfPedidoDTO(Pedido pedido) {
		return InformacoesPedidoDTO.builder().clienteCpf(pedido.getCliente().getCpf())
				.clienteNome(pedido.getCliente().getNome()).codigo(pedido.getId()).total(pedido.getTotal())
				.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.status(pedido.getStatus().name()).itens(converterItemEmInfItemDTO(pedido.getItensPedido())).build();
	}

	public List<InformacoesItemPedidoDTO> converterItemEmInfItemDTO(List<ItemPedido> itens) {
		if (itens.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista vazia");
		}
		return itens.stream().map(item -> {
			return InformacoesItemPedidoDTO.builder().nomeProduto(item.getProduto().getDescricao())
					.quantidade(item.getQuantidade()).valorUnitario(item.getProduto().getValorUnitario()).build();
		}).collect(Collectors.toList());
	}

}
