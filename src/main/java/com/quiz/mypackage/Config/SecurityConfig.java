package com.quiz.mypackage.Config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.quiz.mypackage.Jwt.AuthEntryPointJwt;
import com.quiz.mypackage.Jwt.AuthTokenFilter;

//import Jwt.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // add support for JDBC ... no more hardcoded users :-)

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
//    @Autowired
//    private JwtAuthenticationFilter filter;
//    @Autowired
//    private MyUserDetailsService userDetailsServiceImp;
	
	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		
		// define query to retrieve a user by username
		jdbcUserDetailsManager.setUsersByUsernameQuery(
				"select username, password, enabled from users where username=?");
		
		// define query to retrieve the authorities/roles by username
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
				"select username, authority from authorities where username=?");
		
		return jdbcUserDetailsManager;
	}

	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer

                .requestMatchers("/api/signin").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/user/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/id/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/username/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/quiz/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/quizzes").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/questions/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/user/**").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/quiz/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/question/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/quiz/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/question/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/score/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/quiz/**").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/question/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/score/**").hasRole("ADMIN")
        )
        .sessionManagement(session->session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        http.exceptionHandling(e -> e.authenticationEntryPoint(unauthorizedHandler));
//        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
//        .exceptionHandling(
//                e->e.accessDeniedHandler(
//                                (request, response, accessDeniedException)->response.setStatus(403)
//                        )
//                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
//        .build();

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        http.headers(headers -> headers
        		.frameOptions(frameOptions -> frameOptions
        				.sameOrigin()));
        
        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());
        
        http.addFilterBefore(authenticationJwtTokenFilter(),
        		UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
	
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
    	return new AuthTokenFilter();
    }

}
