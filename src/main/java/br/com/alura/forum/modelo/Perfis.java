package br.com.alura.forum.modelo;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "perfis")
public class Perfis implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoDePerfil;

    @ManyToMany
    @JoinColumn(name = "usuario_id")
    private List<Usuario> usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoDePerfil() {
        return tipoDePerfil;
    }

    public void setTipoDePerfil(String tipoDePerfil) {
        this.tipoDePerfil = tipoDePerfil;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    @Override
    public String getAuthority() {
        return this.tipoDePerfil;
    }
}