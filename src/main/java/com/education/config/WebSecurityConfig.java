package com.education.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.education.jwt.AuthEntryPointJwt;
import com.education.jwt.AuthTokenFilter;
import com.education.security.service.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity
// (securedEnabled = true,
// jsr250Enabled = true,
// prePostEnabled = true) // by default
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

//  @Override
//  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//  }
  
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
  }

//  @Bean
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//    return super.authenticationManagerBean();
//  }
  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.cors().and().csrf().disable()
//      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//      .authorizeRequests().antMatchers("/api/auth/**").permitAll()
//      .antMatchers("/api/test/**").permitAll()
//      .anyRequest().authenticated();
//
//    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//  }
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	  
	    http.csrf().disable();
	    http.headers().frameOptions().disable();
    http.csrf(csrf -> csrf.disable())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> 
          auth
          //.requestMatchers("/api/auth/**").permitAll()
          .requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
          .requestMatchers(AntPathRequestMatcher.antMatcher("/api/auth/**")).permitAll()
          
         //    .requestMatchers("/api/student/**").permitAll()
            //  .anyRequest().authenticated()
              .anyRequest().permitAll()
        );
     
    http.authenticationProvider(authenticationProvider());

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    
    return http.build();
  }
  
  /*
  @SuppressWarnings("removal")
@Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	  http.cors().and().cors().disable();
      return http.authorizeHttpRequests(requests -> {
                    //   requests.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();
                   //   requests.requestMatchers(new AntPathRequestMatcher("/secure/**")).hasAuthority("/api/student/**");
                      requests.anyRequest().permitAll();
                    //  requests.anyRequest().authenticated();
                     // requests.antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();
                    //  requests.requestMatchers("/api/auth/**").permitAll();
                     // requests.requestMatchers("/api/student/**").permitAll();
              }).build();
  }
  */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
	  CorsConfiguration corsConfiguration=new CorsConfiguration();
	  corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
	  corsConfiguration.setAllowedMethods(Arrays.asList("*"));
	  corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
	  corsConfiguration.setAllowCredentials(true);
	  UrlBasedCorsConfigurationSource sourse=new UrlBasedCorsConfigurationSource();
	  sourse.registerCorsConfiguration("/**", corsConfiguration);
	return sourse;
	  
  }
}
