package br.com.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	@Query("select u from Usuario u where u.usuario =:usuario")
	Optional<Usuario> findByusuario (@Param("usuario")String login);

}
