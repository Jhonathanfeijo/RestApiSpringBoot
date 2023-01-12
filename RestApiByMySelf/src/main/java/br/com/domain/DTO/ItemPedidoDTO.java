package br.com.domain.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {
	private Integer produto;
	private BigDecimal valorUnitario;
	private Integer quantidade;
}
