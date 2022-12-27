package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.jwt.AuthEntryPoint;
import com.app.jwt.AuthTokenFilter;
import com.app.services.MyUserServiceImpl;


@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private MyUserServiceImpl myUserServiceImpl ; 
	
	@Autowired
	private  AuthEntryPoint unAuthorizedHandler ; 
	
	@Autowired
	private AuthTokenFilter authTokenFilter ; 
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		
		authenticationProvider.setUserDetailsService(myUserServiceImpl);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		
		return authenticationProvider ; 
		
		
	}
	
	 @Bean
	  public AuthTokenFilter authenticationJwtTokenFilter() {
	    return new AuthTokenFilter();
	  }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
//		 http.csrf().disable();
//	        http.authorizeHttpRequests(auth -> auth.requestMatchers("api/auth/**" , "fuck" ).permitAll()
//	                        .anyRequest().authenticated());
//	        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	        http.exceptionHandling().authenticationEntryPoint(unAuthorizedHandler);
//	        http.httpBasic();
//
//	        http.addFilterBefore( authTokenFilter , UsernamePasswordAuthenticationFilter.class);

//		
	    http.cors().and().csrf().disable()
	        .exceptionHandling().authenticationEntryPoint(unAuthorizedHandler).and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	        .authorizeHttpRequests().requestMatchers("/api/auth/**").permitAll()
	        .requestMatchers("/api/test/**").permitAll()
	        .anyRequest().authenticated();
	    
	    http.authenticationProvider(daoAuthenticationProvider());

	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	  }
	
}
