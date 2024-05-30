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

    public Denuncia(int id, String tituloDenuncia, String descricaoDenuncia, String localDenuncia, Usuario usuario) {
        super(id);
        this.tituloDenuncia = tituloDenuncia;
        this.descricaoDenuncia = descricaoDenuncia;
        this.localDenuncia = localDenuncia;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Denuncia{" +
                "tituloDenuncia='" + tituloDenuncia + '\'' +
                ", descricaoDenuncia='" + descricaoDenuncia + '\'' +
                ", localDenuncia='" + localDenuncia + '\'' +
                ", idUsuário=" + usuario.getId() +
                "} " + super.toString();
    }
}
