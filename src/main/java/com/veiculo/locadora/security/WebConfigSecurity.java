package com.veiculo.locadora.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	@Override //Configura as solicitações de acesso por HTTP
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf()
		.disable() //Desativa as configurações padrão de memória
		.authorizeRequests() //Permitir restringir acessos
		.antMatchers(HttpMethod.GET, "/").permitAll() //Qualquer usuário acessa a página
		.anyRequest().authenticated()
		.and().formLogin().permitAll() //Permite qualquer usuário
		.loginPage("/login") //Inicia a página de login
		.defaultSuccessUrl("/")
		.failureUrl("/login?error=true")
		.and().logout().logoutSuccessUrl("/login") //Mapeia URL de Logout e invalida usuário autenticado
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override //Cria autenticação do usuário com banco de dados ou em memória
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(implementacaoUserDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
		/*
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		.withUser("admin")
		.password("$2a$10$xJNz5/pdIdGLWf0fvhwJ7.vDua7SKWLkdxBYL7yv89vcZb0yuTRLm")
		.roles("ADMIN");
		*/
	}
	
	@Override //Ignora URL específicas
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/materialize/**")
		
		.antMatchers(HttpMethod.GET, "/resources/**", "/static/**", "/**", "/materialize/**", "**/materialize/**");
	}
	
}
