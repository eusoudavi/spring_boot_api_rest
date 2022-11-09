package br.com.alura.forum.controller.form;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicosRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AtualTopicoForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String titulo;

    @NotNull @NotEmpty @Length(max = 500)
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Long id, TopicosRepository topicosRepository) {
        Optional<Topico> topico = topicosRepository.findById(id);
        if (topico.isPresent()){
            topico.get().setTitulo(this.titulo);
            topico.get().setMensagem(this.mensagem);
            return topico.get();
        }
        return null;
    }
}
