package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {

    @GetMapping("/topicos")
    public List<TopicoDto> listar(){
//        TÓPICOS
        String titulo = "Dúvida";
        String mensagem = "Dúvida com Spring Boot";
//        CURSO
        String nome = "Spring Boot";
        String categoria = "Programação";

        Curso curso = new Curso(nome, categoria);
        Topico topico = new Topico(titulo, mensagem, curso);

        return TopicoDto.converter(Arrays.asList(topico, topico, topico, topico));
    }

}
