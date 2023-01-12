package br.com.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.domain.enums.StatusPedido;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	private Cliente cliente;
	@Column(name = "data_pedido")
	private LocalDate dataPedido;
	private BigDecimal total;
	@Enumerated(EnumType.STRING)
	@Column(name="Status")
	private StatusPedido status;
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itensPedido;
	


}
