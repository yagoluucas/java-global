package org.example.entities;

public class Doacao extends _BaseEntitie{
    private double valor;
    private String nome;
    private String telefone;
    private String email;
    public Doacao(){}

    public Doacao(double valor, String nome, String telefone, String email) {
        this.valor = valor;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Doacao(int id, double valor, String nome, String telefone, String email) {
        super(id);
        this.valor = valor;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Doacao{" +
                "valor=" + valor +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                "} " + super.toString();
    }
}
