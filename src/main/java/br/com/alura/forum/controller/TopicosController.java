package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
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

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){
        Topico topico = form.converter(cursoRepository);
        topicosRepository.save(topico);

        URI uri = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
//        QUANDO CRIAMOS UM POST E TEMOS A RESPOSTA, COMO BOA PRATICA, TEMOS QUE DEVOLVER AO CLIENTE A RESPOSTA 201
//        ESSA RESPOSTA INDICA QUE O PROCESSO CRIOU UM NOVO OBJETO NO SISTEMA (HTTP - 201)
//        PARA ISSO, DEVEMOS RESPONDER AO CLIENTE A URI DESSE OBJETO E COLOCAR NO CORPO DA RESPOSTA O OBJETO CRIADO
//        uriBuilder -> OBJETO DO PRÓPRIO SPRING PARA CRIAÇÃO DE ENDEREÇOS URI
    }

}
