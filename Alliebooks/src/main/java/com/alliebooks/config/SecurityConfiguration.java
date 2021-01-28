package com.alliebooks.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private ABUserDetailsService userDetailsService;
	
	@Autowired
    private WebApplicationContext applicationContext;
    
    @Autowired
    private DataSource dataSource;
	
	@PostConstruct
    public void completeSetup() {
        userDetailsService = applicationContext.getBean(ABUserDetailsService.class);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        	.antMatchers("/", "/index", "/resources/**", "/css/**", "/js/**", "/images/**", "/icons/**", "/webjars/**", "/**/favicon.ico", "/user/**").permitAll()
        	.antMatchers("/h2_console/**").hasRole("ADMIN")
	      	.anyRequest().authenticated()
      			.and()
			.formLogin()
				.defaultSuccessUrl("/", true)
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.and()
			.httpBasic();
		
	    http.exceptionHandling().accessDeniedPage("/403");
	    http.csrf().disable();
	    http.headers().frameOptions().disable();
	}
	
	@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
            .and()
            .authenticationProvider(authenticationProvider())
            .jdbcAuthentication()
            .dataSource(dataSource);
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
