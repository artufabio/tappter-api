package com.tappter.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig {
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public AppSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/login").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
		
		http.headers().frameOptions().disable();	//allow displaying H2 Console
		
		return http.build();
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/index/**", "/css/**", "/js/**");
    }
	
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails adminUser = User.builder()
				.username("admin")
				.password(passwordEncoder.encode("admin-password"))
				.roles("ADMIN")		//ROLE_ADMIN
				.build();
		
		UserDetails guestUser = User.builder()
				.username("guest")
				.password(passwordEncoder.encode("guest-password"))
				.roles("GUEST")		//ROLE_GUEST
				.build();
		
		return new InMemoryUserDetailsManager(
					adminUser,
					guestUser
				);
		
	}

}
