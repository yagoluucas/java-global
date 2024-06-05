package org.example.repository;

import org.example.entities._BaseEntitie;

import java.sql.SQLException;
import java.util.List;

public interface _BaseRepository<T extends _BaseEntitie> {
    public void Create(T entity);
    public List<T> ReadAll();
    public T Read(int id);
    public void Update(T entity);
    public void Delete(int id);
}
