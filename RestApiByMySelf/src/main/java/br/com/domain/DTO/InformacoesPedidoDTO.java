package br.com.domain.DTO;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacoesPedidoDTO {
	private Integer codigo;
	private String clienteNome;
	private String clienteCpf;
	private BigDecimal total;
	private String dataPedido;
	private String status;
	private List<InformacoesItemPedidoDTO> itens;

}
