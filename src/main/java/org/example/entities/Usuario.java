package org.example.entities;

import jakarta.json.bind.annotation.JsonbTransient;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends _BaseEntitie{
    private String nome;
    private String email;
    private String senha;

    @JsonbTransient
    private List<Denuncia> denuncias = new ArrayList<>();

    @JsonbTransient
    private List<Doacao> doacoes = new ArrayList<>();

    public Usuario(){}

    public Usuario(String nome, String senha, String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    public Usuario(int id, String nome, String senha, String email) {
        super(id);
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    public Usuario(int id, String nome, String senha) {
        super(id);
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Denuncia> getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(List<Denuncia> denuncias) {
        this.denuncias = denuncias;
    }

    public List<Doacao> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(List<Doacao> doacoes) {
        this.doacoes = doacoes;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                "} " + super.toString();
    }
}
