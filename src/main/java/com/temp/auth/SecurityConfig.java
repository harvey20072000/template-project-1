package com.temp.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.temp.auth.filter.JwtAuthEntryPoint;
import com.temp.auth.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    @Autowired
    private JwtAuthEntryPoint authEntryPoint;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		UserDetails user1 = User.withUsername("user1").password("{noop}111")
				.authorities("STUDENT").build();
		UserDetails user2 = User.withUsername("user2").password("{noop}222")
				.authorities("TEACHER").build();
		UserDetails user3 = User.withUsername("user3").password("{noop}333")
				.authorities("ADMIN", "TEACHER").build();
		
		return new InMemoryUserDetailsManager(List.of(user1, user2, user3));
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		return httpSecurity
//				.authorizeHttpRequests((requests) -> requests
//					.requestMatchers("/", "/index").hasAnyAuthority("ADMIN", "TEACHER")
//					.requestMatchers("/api/**").authenticated()
//					.anyRequest().authenticated()
//				)
//				.formLogin((form) -> form
//					.loginPage("/login").permitAll()
//				)
//				.logout((logout) -> logout.permitAll())
//				.build();
		httpSecurity.csrf(AbstractHttpConfigurer::disable)
        .authorizeRequests()
        .requestMatchers("/account/create", "/auth/login").permitAll()
        .requestMatchers("/account/**").authenticated()
        .anyRequest().authenticated()
        .and().exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	    httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	    return httpSecurity.build();
	}
}
