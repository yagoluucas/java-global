package org.example.entities;

public class Denuncia extends _BaseEntitie{
    private String tituloDenuncia;
    private String descricaoDenuncia;
    private String localDenuncia;
    private Usuario usuario;

    public Denuncia(){}

    public Denuncia(String tituloDenuncia, String descricaoDenuncia, String localDenuncia, Usuario usuario) {
        this.tituloDenuncia = tituloDenuncia;
        this.descricaoDenuncia = descricaoDenuncia;
        this.localDenuncia = localDenuncia;
        this.usuario = usuario;
    }

    public Denuncia(int id, String tituloDenuncia, String descricaoDenuncia, String localDenuncia) {
        this.tituloDenuncia = tituloDenuncia;
        this.descricaoDenuncia = descricaoDenuncia;
        this.localDenuncia = localDenuncia;
    }

    public Denuncia(String tituloDenuncia, String descricaoDenuncia, String localDenuncia) {
        this.tituloDenuncia = tituloDenuncia;
        this.descricaoDenuncia = descricaoDenuncia;
        this.localDenuncia = localDenuncia;
    }

    public Denuncia(int id, String tituloDenuncia, String descricaoDenuncia, String localDenuncia, Usuario usuario) {
        super(id);
        this.tituloDenuncia = tituloDenuncia;
        this.descricaoDenuncia = descricaoDenuncia;
        this.localDenuncia = localDenuncia;
        this.usuario = usuario;
        this.usuario.getDenuncias().add(this);
    }

    public String getTituloDenuncia() {
        return tituloDenuncia;
    }

    public void setTituloDenuncia(String tituloDenuncia) {
        this.tituloDenuncia = tituloDenuncia;
    }

    public String getDescricaoDenuncia() {
        return descricaoDenuncia;
    }

    public void setDescricaoDenuncia(String descricaoDenuncia) {
        this.descricaoDenuncia = descricaoDenuncia;
    }

    public String getLocalDenuncia() {
        return localDenuncia;
    }

    public void setLocalDenuncia(String localDenuncia) {
        this.localDenuncia = localDenuncia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.usuario.getDenuncias().add(this);
    }

    @Override
    public String toString() {
        return "Denuncia{" +
                "tituloDenuncia='" + tituloDenuncia + '\'' +
                ", descricaoDenuncia='" + descricaoDenuncia + '\'' +
                ", localDenuncia='" + localDenuncia + '\'' +
                "} " + super.toString();
    }
}
