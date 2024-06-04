package org.example.entities;

import java.time.LocalDateTime;

public class AtividadeNoSite extends _BaseEntitie{
    private String arrecadacao;
    private Denuncia denuncia;
    private Doacao doacao;
    private LocalDateTime dataAtividade;

    public AtividadeNoSite(){}

    public AtividadeNoSite(String arrecadacao, Denuncia denuncia, Doacao doacao, LocalDateTime dataAtividade) {
        this.arrecadacao = arrecadacao;
        this.denuncia = denuncia;
        this.doacao = doacao;
        this.dataAtividade = dataAtividade;
    }

    public AtividadeNoSite(String arrecadacao, LocalDateTime dataAtividade) {
        this.arrecadacao = arrecadacao;
        this.doacao = doacao;
        this.dataAtividade = dataAtividade;
    }

    public AtividadeNoSite(int id, String arrecadacao, Denuncia denuncia, Doacao doacao, LocalDateTime dataAtividade) {
        super(id);
        this.arrecadacao = arrecadacao;
        this.denuncia = denuncia;
        this.doacao = doacao;
        this.dataAtividade = dataAtividade;
    }

    public String getArrecadacao() {
        return arrecadacao;
    }

    public void setArrecadacao(String arrecadacao) {
        this.arrecadacao = arrecadacao;
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
                "elucro='" + arrecadacao + '\'' +
                ", denuncia=" + denuncia +
                ", doacao=" + doacao +
                ", dataAtividade=" + dataAtividade +
                "} " + super.toString();
    }
}
