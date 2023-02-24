package com.traveldreams.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
		.csrf().disable()
		  .authorizeHttpRequests()
		    .antMatchers("/admin/**").hasAnyRole("ADMIN")
		    .antMatchers("/register").permitAll()
		    .antMatchers("/css/**").permitAll()
		    .antMatchers("/images/**").permitAll()
		    .anyRequest()
		    //hasAnyRole("USER") // subbing this out with code below to see if fixes registered user as not being set with USER role
		    // it does fix it but I would still like the users to automatically be assigned USER role 
		    .authenticated().and()
		  .formLogin()
		    .loginPage("/login")
//		    .loginPage("/register")
		    .defaultSuccessUrl("/home", true)
		  	.permitAll();
		
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
