package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicosController {

    @Autowired
    private TopicosRepository topicosRepository;

    @GetMapping("/topicos")
    public List<TopicoDto> listar(String nomeCurso, String titulo) {
        if (nomeCurso != null && titulo == null) {
            System.out.println(nomeCurso);
            List<Topico> topicos = topicosRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);
        } else if (nomeCurso == null && titulo != null) {
            System.out.println(titulo);
            List<Topico> topicos = topicosRepository.findByTitulo(titulo);
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = topicosRepository.findAll();
            return TopicoDto.converter(topicos);
        }

    }

}
