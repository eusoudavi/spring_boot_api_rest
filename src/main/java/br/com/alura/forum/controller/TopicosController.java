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
import java.util.Optional;

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
    public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicosRepository.findById(id);
        if (topico.isPresent()){
            return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicosRepository.save(topico);

        URI uri = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualTopicoForm form) {
        Topico topico = form.atualizar(id, topicosRepository);
        if (topico != null) {
            return ResponseEntity.ok(new TopicoDto(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Topico> topico = topicosRepository.findById(id);
        if (topico.isPresent()) {
            topicosRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
