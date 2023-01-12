package br.com.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.domain.model.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
