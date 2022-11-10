package br.com.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport		// ESSA ANOTAÇÃO SERVE PARA PEGARMOS AS REQUISIÇÕES WEB - LIBERA O PAGEABLE NA REQUISIÇÃO
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
