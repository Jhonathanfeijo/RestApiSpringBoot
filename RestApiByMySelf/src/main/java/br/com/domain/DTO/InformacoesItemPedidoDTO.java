package br.com.domain.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacoesItemPedidoDTO {
	private String nomeProduto;
	private Integer quantidade;
	private BigDecimal valorUnitario;
}
