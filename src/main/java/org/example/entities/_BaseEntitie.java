package org.example.entities;

public class _BaseEntitie {
    private int id;

    public _BaseEntitie(){}

    public _BaseEntitie(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "_baseEntitie{" +
                "id=" + id +
                '}';
    }
}
