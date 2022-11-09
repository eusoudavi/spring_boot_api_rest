package br.com.alura.forum.config.validation;

public class ErroFormularioDto {

    private String campo;
    private String mensagemErro;

    public ErroFormularioDto(String campo, String mensagemErro) {
        this.campo = campo;
        this.mensagemErro = mensagemErro;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
}
