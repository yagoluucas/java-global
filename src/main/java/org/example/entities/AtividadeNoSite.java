package org.example.entities;

import java.time.LocalDateTime;

public class AtividadeNoSite extends _BaseEntitie{
    private String elucro;
    private Denuncia denuncia;
    private Doacao doacao;
    private LocalDateTime dataAtividade;

    public AtividadeNoSite(){}

    public AtividadeNoSite(String elucro, Denuncia denuncia, Doacao doacao, LocalDateTime dataAtividade) {
        this.elucro = elucro;
        this.denuncia = denuncia;
        this.doacao = doacao;
        this.dataAtividade = dataAtividade;
    }

    public AtividadeNoSite(int id, String elucro, Denuncia denuncia, Doacao doacao, LocalDateTime dataAtividade) {
        super(id);
        this.elucro = elucro;
        this.denuncia = denuncia;
        this.doacao = doacao;
        this.dataAtividade = dataAtividade;
    }

    public String getElucro() {
        return elucro;
    }

    public void setElucro(String elucro) {
        this.elucro = elucro;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }

    public Doacao getDoacao() {
        return doacao;
    }

    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }

    public LocalDateTime getDataAtividade() {
        return dataAtividade;
    }

    public void setDataAtividade(LocalDateTime dataAtividade) {
        this.dataAtividade = dataAtividade;
    }

    @Override
    public String toString() {
        return "AtividadeNoSite{" +
                "elucro='" + elucro + '\'' +
                ", denuncia=" + denuncia +
                ", doacao=" + doacao +
                ", dataAtividade=" + dataAtividade +
                "} " + super.toString();
    }
}
