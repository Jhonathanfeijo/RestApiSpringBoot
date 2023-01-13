package br.com.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="usuario")
	@NotEmpty(message="{campo.login.usuario.obrigatorio}")
	private String usuario;
	@Column(name="senha")
	@NotEmpty(message="{campo.senha.usuario.obrigatorio}")
	private String senha;
	@Column(name="admin")
	private boolean admin;

}
