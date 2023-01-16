package br.com.domain.config;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.domain.security.jwt.JwtAuthFilter;
import br.com.domain.security.jwt.JwtService;
import br.com.domain.service.impl.UsuarioServiceImpl;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired @Lazy private UsuarioServiceImpl serviceUser;
	
	@Autowired private JwtService jwtService;

	
	@Bean
	public PasswordEncoder passwordEncode () {
		return new BCryptPasswordEncoder();
			
	}
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(serviceUser).passwordEncoder(passwordEncode());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/cliente/**").hasAnyRole("USER","ADMIN")
			.antMatchers("/pedido/**").hasAnyRole("USER","ADMIN")
			.antMatchers("/produto/**").hasAnyRole("ADMIN")
			.antMatchers(HttpMethod.POST,"/usuario/**").permitAll()
			.anyRequest().authenticated()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

	}
	
	
	
	
	
	

}
