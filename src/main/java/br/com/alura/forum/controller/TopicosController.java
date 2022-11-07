package br.com.alura.forum.controller;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class TopicosController {

    @GetMapping("/topicos")
    @ResponseBody // É UTILIZADA UMA BIBLIOTECA CHAMADA JACKSON PARA TRANSFORMAR A RESPOSTA EM UM JSON
    public List<Topico> listar(){
//        TÓPICOS
        String titulo = "Dúvida";
        String mensagem = "Dúvida com Spring Boot";
//        CURSO
        String nome = "Spring Boot";
        String categoria = "Programação";

        Curso curso = new Curso(nome, categoria);
        Topico topico = new Topico(titulo, mensagem, curso);

        return Arrays.asList(topico, topico, topico);
    }

}
