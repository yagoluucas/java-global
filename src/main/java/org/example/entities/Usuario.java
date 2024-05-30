package org.example.entities;

import java.util.List;

public class Usuario extends _BaseEntitie{
    private String nome;
    private String senha;
    private List<Denuncia> denuncias;

    public Usuario(){}

    public Usuario(String nome, String senha, List<Denuncia> denuncias) {
        this.nome = nome;
        this.senha = senha;
        this.denuncias = denuncias;
    }

    public Usuario(int id, String nome, String senha, List<Denuncia> denuncias) {
        super(id);
        this.nome = nome;
        this.senha = senha;
        this.denuncias = denuncias;
    }
}
