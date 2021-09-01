package com.restfullProject.restfullProject.security;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.restfullProject.restfullProject.io.repositories.UserRepository;
import com.restfullProject.restfullProject.service.UserService;


@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class WebSecuirty extends WebSecurityConfigurerAdapter {
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserRepository  userRepository ;

	public WebSecuirty(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,UserRepository userRepository) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and().csrf().
		disable().authorizeRequests()
		.antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL)
		.permitAll()
		.antMatchers(HttpMethod.POST,SecurityConstants.REST_PASSWORD)
		.permitAll()
		.antMatchers(SecurityConstants.H2_CONSOLE)
		.permitAll()
		  .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
	        .permitAll()
	       // .antMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN")
		.anyRequest().authenticated().and().addFilter(getAuthonticationFilter())
				.addFilter(new AuthorizationFilter(authenticationManager(),userRepository))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.csrf().disable();
	    http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

	
	public AuthonticationFilter getAuthonticationFilter()  throws Exception{
	final	AuthonticationFilter authonticationFilter=	new AuthonticationFilter(authenticationManager() ) ;
				authonticationFilter.setFilterProcessesUrl("/users/login");
		return authonticationFilter;
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	final	CorsConfiguration configuration=new CorsConfiguration() ;
	   
 	configuration.setAllowedOrigins(Arrays.asList("*"));
 	configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE","OPTIONS"));
 	configuration.setAllowCredentials(true);
 	configuration.setAllowedHeaders(Arrays.asList("*"));
 	
 	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
 	source.registerCorsConfiguration("/**", configuration);
 	
 	return source;
	}
	
	}
