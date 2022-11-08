package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicosRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByTitulo(String titulo);

    //    O JPA FAZ UMA PESQUISA NA ENTIDADE CURSO E PROCURA PELO NOME
    //    -> PARA EVITAR AMBIGUIDADES ENTRE ATRIBUTOS E RELACIONAMENTOS, PODEMOS ESCREVER A FUNÇÃO ABAIXO COMO findByCurso_Nome
    List<Topico> findByCursoNome(String nomeCurso);
}
