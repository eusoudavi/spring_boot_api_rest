package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping("/{id}")
    public DetalhesTopicoDto detalhar(@PathVariable Long id) {
        Topico topico = topicosRepository.getReferenceById(id);
        return new DetalhesTopicoDto(topico);
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicosRepository.save(topico);

        URI uri = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    //    @PatchMapping     A ANOTAÇÃO DO TIPO PATCH NORMALMENTE SERVE PARA PEQUENAS ATUALIZAÇÕES
    @PutMapping("/{id}")     // ENQUANTO QUE A ATUALIZAÇÃO DO TIPO PUT NORMALMENTE SERVE PARA SOBRESCREVER TODO O TÓPICO
    @Transactional              // SEM ESSA ANOTAÇÃO, O SPRING NÃO COMMITA AS ATUALIZAÇÕES NO BANCO DE DADOS
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualTopicoForm form) {
        Topico topico = form.atualizar(id, topicosRepository);
//        QUANDO O MÉTODO ACIMA TERMINAR DE EXECUTAR, O SPRING DATA JÁ VAI ATUALIZAR O BANCO DE DADOS SEM A NECESSIDADE DE MANDAR O SISTEMA FAZER O UPDATE
        return ResponseEntity.ok(new TopicoDto(topico));
    }

}
