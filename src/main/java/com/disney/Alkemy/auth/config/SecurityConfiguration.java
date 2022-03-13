package com.disney.alkemy.auth.config;

import com.disney.alkemy.auth.filter.JwtRequestFilter;
import com.disney.alkemy.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * ACA CON LA ANOTACION @EnableWebSecurity HABILITAMOS LA SEGURIDAD A TRAVES DE WEB, LOS ENDPOINTS, ETC, Y SOBREESCRIBE TODA LA FUNCIONALIDAD
 *
 * @author ignac
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private JwtRequestFilter jwRequestFilter;

    /**
     * ES PARA INDICAR CUAL ES EL USERDETAIL, QUE NO VAMOS A USAR EL DE DEFAULT SINO EL QUE CREAMOS
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsCustomService);
    }

    /**
     * PARA ENCODEAR LA PASSWORD, LE VAMOS A DECIR QUE NOMPONGA NINGUN ENCODER
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    /**
     * PARA CONFIGURAR COMO SE COMPORTA NUESTRO HTTPSECURITY Y QUE VA A TENER SEGURIDAD Y QUE NO
     * @param httpSecurity
     * @throws Exception 
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/auth/*").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//ESTO ES PARA QUE CADA ENDPOINT, EJECUCION HACIA 
                                                                        //SPRINGSECURITY, NOS HAGA UNA AUTENTICACION NUEVA
        
        httpSecurity.addFilterBefore(jwRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}