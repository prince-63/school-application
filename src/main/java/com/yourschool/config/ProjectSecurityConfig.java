package com.yourschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {

		http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveMsg").ignoringRequestMatchers("/public/**").ignoringRequestMatchers("/api/**").ignoringRequestMatchers("/data-api/**").ignoringRequestMatchers("/yourschool/actuator/**"))
				.authorizeHttpRequests(
						(requests) -> requests.requestMatchers("/dashboard").authenticated()
								.requestMatchers("/displayProfile").authenticated()
								.requestMatchers("/updateProfile").authenticated()
								.requestMatchers("/api/**").authenticated()
								.requestMatchers("/data-api/**").authenticated()
								.requestMatchers("/displayMessages/**").hasRole("ADMIN")
								.requestMatchers("/closeMsg/**").hasRole("ADMIN")
								.requestMatchers("/admin/**").hasRole("ADMIN")
								.requestMatchers("/yourschool/actuator/**").hasRole("ADMIN")
								.requestMatchers("/student/**").hasRole("STUDENT")
								.requestMatchers("/").permitAll()
								.requestMatchers("/home").permitAll()
								.requestMatchers("/holidays/**").permitAll()
								.requestMatchers("/contact").permitAll()
								.requestMatchers("/saveMsg").permitAll()
								.requestMatchers("/courses").permitAll()
								.requestMatchers("/about").permitAll()
								.requestMatchers("/assets/**").permitAll()
								.requestMatchers("/login").permitAll()
								.requestMatchers("/public/**").permitAll()
								.requestMatchers("/logout").permitAll())
				.formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
						.defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
				.logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
						.invalidateHttpSession(true).permitAll())
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
