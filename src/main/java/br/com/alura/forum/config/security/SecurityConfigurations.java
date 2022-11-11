package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacoaService;

//    CONFIGURAR A PARTE DE AUTENTICAÇÃO
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacoaService);
//        ACIMA PASSAMOS A SERVICE QUE VAI PASSAR O USUÁRIO CADASTRADOS
    }

//    CONFIGURAR A PARTE DE AUTORIZAÇÃO
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

//    CONFIGURAR RECURSOS ESTÁTICOS - JS, CSS, IMG...
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
