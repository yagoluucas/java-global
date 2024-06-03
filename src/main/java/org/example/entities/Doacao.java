package org.example.entities;

public class Doacao extends _BaseEntitie{
    private double valor;

    private Usuario usuario;

    public Doacao(){}

    public Doacao(double valor) {
        this.valor = valor;
    }

    public Doacao(double valor, Usuario usuario) {
        this.valor = valor;
        this.usuario = usuario;
    }

    public Doacao(int id, double valor, Usuario usuario) {
        super(id);
        this.valor = valor;
        this.usuario = usuario;
    }

    public Doacao(int id, double valor) {
        super(id);
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Doacao{" +
                "valor=" + valor +
                ", usuario=" + usuario +
                '}';
    }
}
