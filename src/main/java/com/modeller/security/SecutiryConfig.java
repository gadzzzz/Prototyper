package com.modeller.security;

import com.modeller.logic.services.SimpleSocialUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created by Gadzzzz on 10.02.2016.
 */
@Configuration
@EnableWebMvcSecurity
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login/authenticate")
			.failureUrl("/login?error=bad_credentials")
		.and()
			.logout()
			.deleteCookies("JSESSIONID")
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
		.and()
			.authorizeRequests()
			.antMatchers(
				"/auth/**",
				"/login",
				"/signup/**",
				"/user/register/**"
			).permitAll()
			.antMatchers("/**").hasRole("USER")
		.and()
			.apply(new SpringSocialConfigurer());
	}

	@Bean
	public SocialUserDetailsService socialUserDetailsService() {
		return new SimpleSocialUserDetailsService(userDetailsService());
	}
}
