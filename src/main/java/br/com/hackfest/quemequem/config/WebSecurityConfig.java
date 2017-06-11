package br.com.hackfest.quemequem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
    protected void configure(HttpSecurity http) throws Exception {
        
		http
	     .authorizeRequests()
	    	.antMatchers("/resources/**", "/home", "/folha_pessoal").permitAll()
	    .and()
	    .csrf().disable();
    }

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { auth .inMemoryAuthentication()
	 * .withUser("user").password("password").roles("USER"); }
	 */

}
