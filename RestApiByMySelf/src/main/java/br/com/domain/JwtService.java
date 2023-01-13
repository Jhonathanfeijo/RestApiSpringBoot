package br.com.domain;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import br.com.domain.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm; 

@Service
public class JwtService {
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	@Value("${security.jwt.chaveDeAssinatura}")
	private String chaveDeAssinatura;
	
	public String gerarToken(Usuario usuario) {
		long expString= Long.valueOf(expiracao);
		LocalDateTime dataString = LocalDateTime.now().plusMinutes(expString);
		Instant instant = dataString.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		return Jwts.builder().setSubject(usuario.getUsuario())
				.setExpiration(data).signWith(SignatureAlgorithm.HS512, chaveDeAssinatura).compact();
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext contexto = SpringApplication.run(RestApiByMySelfApplication.class);
		JwtService service = contexto.getBean(JwtService.class);
		Usuario usuario = Usuario.builder().usuario("fulano").senha("123").build();
		String token = service.gerarToken(usuario);
		System.out.println(token);
	}
}
