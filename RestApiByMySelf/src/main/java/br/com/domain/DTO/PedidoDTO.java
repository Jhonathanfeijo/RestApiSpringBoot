package br.com.domain.DTO;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.domain.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
	private Integer codigo;
	@NotNull(message="{campo.codigo-cliente.obrigatorio}")
	private Integer cliente;
	@NotEmptyList(message="{campo.itens-pedido.obrigatorio}")
	private List<ItemPedidoDTO> itens;
	@NotNull(message="{campo.valor-total-pedido.obrigatorio}")
	private BigDecimal valorTotal;

}
