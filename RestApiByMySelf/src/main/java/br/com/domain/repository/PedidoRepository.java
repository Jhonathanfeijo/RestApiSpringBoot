package br.com.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.domain.model.Pedido;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Query("select p from Pedido p left join fetch p.itensPedido  where p.id =:id")
	public Optional<Pedido> findByIdFetchItensPedido(@Param("id") Integer id);

}
