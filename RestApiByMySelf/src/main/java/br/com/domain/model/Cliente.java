package br.com.domain.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name="cliente")
@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="nome", length=100)
	@NotEmpty(message="{campo.nome-cliente.obrigatorio}")
	private String nome;
	@Column(name="cpf",length=11)
	@NotEmpty(message="{campo.cpf-cliente.obrigatorio}")
	@CPF(message="{campo.cpf-cliente.invalido}")
	private String cpf;
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY)
	private Set	<Pedido> pedidos;

}
